package com.mss.identity.service;

import com.mss.core.model.TokenProps;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Map;

public interface JwtEncoder {
    String generate(String sub, Map<String, Object> customClaims) throws NoSuchAlgorithmException, InvalidKeySpecException;
    String generate(TokenProps tokenProps);
}
