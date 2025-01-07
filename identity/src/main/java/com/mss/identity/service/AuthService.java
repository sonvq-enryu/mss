package com.mss.identity.service;

import com.mss.core.exception.ApiException;
import com.mss.identity.dto.auth.AuthDto;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;

public interface AuthService {
    AuthDto.SignUpResponse userSignUp(AuthDto.SignUpRequest request) throws NoSuchAlgorithmException, InvalidKeySpecException;
    AuthDto.SignInResponse signIn(AuthDto.SignInRequest request) throws ApiException;
}
