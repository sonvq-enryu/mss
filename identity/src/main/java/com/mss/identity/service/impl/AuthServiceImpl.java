package com.mss.identity.service.impl;

import com.mss.core.constant.AppConstants;
import com.mss.core.exception.ApiException;
import com.mss.core.model.TokenProps;
import com.mss.identity.config.security.JwtConfiguration;
import com.mss.identity.dto.auth.AuthDto;
import com.mss.identity.model.postgres.UserEntity;
import com.mss.identity.service.*;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.Map;
import java.util.StringJoiner;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class AuthServiceImpl implements AuthService {
    UserService userService;
    JwtEncoder jwtEncoder;
    JwtDecoder jwtDecoder;
    InvalidateTokenService invalidateTokenService;
    PasswordEncoder passwordEncoder;
    JwtConfiguration jwtConfiguration;

    @Override
    public AuthDto.SignUpResponse userSignUp(AuthDto.SignUpRequest request) {
        var user = userService.createUserIncludeProfile(request);
        var ip = getIp();

        var accessToken = jwtEncoder.generate(accessTokenProps(user, ip));
        var refreshToken = jwtEncoder.generate(refreshTokenProps(user, ip));

        return AuthDto.SignUpResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    @Override
    public AuthDto.SignInResponse signIn(AuthDto.SignInRequest request) throws ApiException {
        var user = userService.findUserByUsername(request.getUsername())
                .orElseThrow(() -> new ApiException(AppConstants.StatusCode.UNAUTHORIZED, "Username not found"));

        var isAuthenticated = passwordEncoder.matches(request.getPassword(), user.getHashPassword());
        if (!isAuthenticated) {
            throw new ApiException(AppConstants.StatusCode.UNAUTHORIZED, "Wrong password");
        }

        return AuthDto.SignInResponse.builder()
                .build();
    }

    @Override
    public AuthDto.SignInResponse refreshToken(AuthDto.RefreshTokenRequest request) throws ApiException {
        var claims = jwtDecoder.toClaims(request.getToken());

        var scope = claims.get(AppConstants.CustomClaim.SCOPE.val(), String.class);
        if (!AppConstants.CustomJwtScope.REFRESH_TOKEN.val().equals(scope)) {
            throw new ApiException(AppConstants.StatusCode.UNAUTHORIZED, "Invalid token");
        }

        if (invalidateTokenService.isBlackList(claims.getId())) {
            throw new ApiException(AppConstants.StatusCode.UNAUTHORIZED, "Invalid token");
        }
        invalidateTokenService.blackList(claims);

        var ip = getIp();
        var user = userService.findUserByUsername(claims.getSubject()).orElseThrow(() -> new  ApiException(AppConstants.StatusCode.UNAUTHORIZED, "User not found"));
        var accessToken = jwtEncoder.generate(accessTokenProps(user, ip));
        var refreshToken = jwtEncoder.generate(refreshTokenProps(user, ip));

        return AuthDto.SignInResponse.builder()
                .accessToken(accessToken)
                .refreshToken(refreshToken)
                .build();
    }

    private TokenProps accessTokenProps(UserEntity user, String ip) {
        Map<String, Object> accessTokenClaims = Map.of(
                AppConstants.CustomClaim.UID.val(), user.getId(),
                AppConstants.CustomClaim.IP.val(), ip,
                AppConstants.CustomClaim.ROLE.val(), user.getRole(),
                AppConstants.CustomClaim.SCOPE.val(), toScopes(AppConstants.CustomJwtScope.PORTAL.val())
        );

        return TokenProps.builder()
                .sub(user.getUsername())
                .customClaims(accessTokenClaims)
                .durationInMinutes(jwtConfiguration.getAccessDuration())
                .build();
    }

    private TokenProps refreshTokenProps(UserEntity user, String ip) {
        Map<String, Object> claims = Map.of(
                AppConstants.CustomClaim.UID.val(), user.getId(),
                AppConstants.CustomClaim.IP.val(), ip,
                AppConstants.CustomClaim.SCOPE.val(), toScopes(AppConstants.CustomJwtScope.REFRESH_TOKEN.val())
        );

        return TokenProps.builder()
                .sub(user.getUsername())
                .durationInMinutes(jwtConfiguration.getRefreshDuration())
                .customClaims(claims)
                .build();
    }

    private String getIp() {
        return ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getRemoteAddr();
    }

    private String toScopes(String... scopeNames) {
        var joiner = new StringJoiner(" ");
        for (var scopeName: scopeNames) {
            joiner.add(scopeName);
        }
        return joiner.toString();
    }
}
