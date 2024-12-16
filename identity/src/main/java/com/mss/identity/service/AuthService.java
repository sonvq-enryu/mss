package com.mss.identity.service;

import com.mss.identity.dto.auth.AuthDto;

public interface AuthService {
    AuthDto.SignUpResponse userSignUp(AuthDto.SignUpRequest request);
    AuthDto.SignInResponse signIn(AuthDto.SignInRequest request);
}
