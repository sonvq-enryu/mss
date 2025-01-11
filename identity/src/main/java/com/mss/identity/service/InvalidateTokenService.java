package com.mss.identity.service;

import io.jsonwebtoken.Claims;

import java.util.UUID;

public interface InvalidateTokenService {
    boolean isBlackList(UUID jti);
    boolean isBlackList(String jti);
    void blackList(Claims claims);
}
