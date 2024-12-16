package com.mss.identity.service.impl;

import com.mss.identity.dto.auth.AuthDto;
import com.mss.identity.mapper.UserMapper;
import com.mss.identity.mapper.UserProfileMapper;
import com.mss.identity.model.enumeration.UserRole;
import com.mss.identity.service.AuthService;
import com.mss.identity.service.UserProfileService;
import com.mss.identity.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Slf4j
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements AuthService {
    private final UserService userService;
    private final UserProfileService userProfileService;
    private final PasswordEncoder passwordEncoder;
    private final JwtService jwtService;
    private final UserProfileMapper userProfileMapper;
    private final UserMapper userMapper;
    private final AuthenticationManager authenticationManager;

    @Override
    @Transactional
    public AuthDto.SignUpResponse userSignUp(AuthDto.SignUpRequest request) {
        var userEntity = userMapper.toEntity(request, passwordEncoder.encode(request.getPassword()), UserRole.USER);
        userEntity = userService.createUser(userEntity);
        var userProfileEntity = userProfileMapper.toEntity(request);
        userProfileEntity.setUser(userEntity);
        userProfileEntity = userProfileService.createUserProfile(userProfileEntity);
        var accessToken = jwtService.generate(null, request.getUsername());
        return new AuthDto.SignUpResponse(accessToken, null, 0, userProfileMapper.toUserInformation(userProfileEntity));
    }

    @Override
    @Transactional
    public AuthDto.SignInResponse signIn(AuthDto.SignInRequest request) {
        var userEntity = userService.findUserByUsername(request.getUsername()).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(userEntity.getUsername(), request.getPassword()));
        var accessToken = jwtService.generate(null, request.getUsername());
        return AuthDto.SignInResponse.builder()
                .accessToken(accessToken)
                .refreshToken(null)
                .expiredAt(0)
                .build();
    }
}
