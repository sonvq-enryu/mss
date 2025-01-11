package com.mss.identity.service.impl;

import com.mss.identity.model.postgres.InvalidToken;
import com.mss.identity.repository.postgres.InvalidTokenRepository;
import com.mss.identity.service.InvalidateTokenService;
import io.jsonwebtoken.Claims;
import lombok.AccessLevel;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)
public class InvalidateTokenServiceImpl implements InvalidateTokenService {
    InvalidTokenRepository invalidTokenRepository;

    @Override
    public boolean isBlackList(UUID jti) {
        return invalidTokenRepository.findById(jti).isPresent();
    }

    @Override
    public boolean isBlackList(String jti) {
        return isBlackList(UUID.fromString(jti));
    }

    @Override
    @Transactional
    public void blackList(Claims claims) {
        var blackListToken = InvalidToken.builder()
                .id(UUID.fromString(claims.getId()))
                .expiredAt(claims.getExpiration().toInstant().toEpochMilli())
                .build();

        invalidTokenRepository.save(blackListToken);
    }
}
