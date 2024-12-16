package com.mss.identity.service.impl;

import com.mss.identity.service.security.UserInformationDomain;
import com.mss.identity.utils.DateUtils;
import com.mss.identity.utils.TimeUtils;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Slf4j
@Service
@RequiredArgsConstructor
public class JwtService {
    private static final long TTL = 60 * 1000L * 60 * 24;

    @Value("${jwt.secret}")
    private String secret;

    public String generate(Map<String, Object> extraClaims, String username) {
        extraClaims = new HashMap<>();
        var now = TimeUtils.currentTimeMillis();
        var expiration = now + TTL;

        return Jwts.builder()
                .claims(extraClaims)
                .subject(username)
                .issuedAt(DateUtils.toDate(now))
                .expiration(DateUtils.toDate(expiration))
                .signWith(getSigningKey(), Jwts.SIG.HS256)
                .compact();
    }

    public boolean isValidToken(String token, UserInformationDomain userDetails) {
        var claims = extractClaims(token);
        var current = new Date();
        return claims.getSubject().equals(userDetails.getUsername()) && claims.getExpiration().after(current);
    }

    public boolean isValidToken(String token) {
        var claims = extractClaims(token);
        return claims.getExpiration().after(DateUtils.now());
    }

    public String extractUsername(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
        final var claims = extractClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractClaims(String token) {
        return Jwts.parser()
                .verifyWith(getSigningKey())
                .build()
                .parseSignedClaims(token)
                .getPayload();
    }

    private SecretKey getSigningKey() {
        return Keys.hmacShaKeyFor(Decoders.BASE64.decode(secret));
    }
}
