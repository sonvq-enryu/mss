package com.mss.identity.service.impl;

import com.mss.core.constant.AppConstants;
import com.mss.core.exception.ApiException;
import com.mss.identity.dto.auth.AuthDto;
import com.mss.identity.service.AuthService;
import com.mss.identity.service.JwtEncoder;
import com.mss.identity.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;
import java.util.StringJoiner;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserService userService;
    private final JwtEncoder jwtEncoder;
    private final PasswordEncoder passwordEncoder;

    @Override
    @Transactional
    public AuthDto.SignUpResponse userSignUp(AuthDto.SignUpRequest request) throws NoSuchAlgorithmException, InvalidKeySpecException {
        var user = userService.createUserIncludeProfile(request);
        var ip =  ((ServletRequestAttributes) RequestContextHolder.currentRequestAttributes()).getRequest().getRemoteAddr();

        Map<String, Object> claims = Map.of(
                AppConstants.CustomClaim.UID.name(), user.getId(),
                AppConstants.CustomClaim.IP.name(), ip,
                AppConstants.CustomClaim.ROLE.name(), user.getRole(),
                AppConstants.CustomClaim.SCOPE.name(), toScopes("Portal")
        );

        var accessToken = jwtEncoder.generate(user.getUsername(), claims);


        return new AuthDto.SignUpResponse();
    }

    @Override
    @Transactional
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

    private String toScopes(String... scopeNames) {
        var joiner = new StringJoiner(" ");
        for (var scopeName: scopeNames) {
            joiner.add(scopeName);
        }
        return joiner.toString();
    }
}
