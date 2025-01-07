package com.mss.identity.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;

public interface JwtDecoder {
    Claims toClaims(String token);
    boolean verify(String token);
    Jws<Claims> parseToken(String token);
}
