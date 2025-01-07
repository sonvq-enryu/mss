package com.mss.identity.service.impl;

import com.mss.core.cipher.RSACipherHandler;
import com.mss.core.model.TokenProps;
import com.mss.core.utils.DateUtils;
import com.mss.identity.config.security.JwtConfiguration;
import com.mss.identity.service.JwtDecoder;
import com.mss.identity.service.JwtEncoder;
import io.jsonwebtoken.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.security.NoSuchAlgorithmException;
import java.security.spec.InvalidKeySpecException;
import java.util.Date;
import java.util.Map;
import java.util.UUID;

@Slf4j
@Service
public class JwtHandler implements JwtEncoder, JwtDecoder {
    private final JwtConfiguration jwtConfiguration;
    private final RSACipherHandler rsaCipherHandler;
    private final JwtParser jwtParser;

    public JwtHandler(JwtConfiguration jwtConfiguration) throws NoSuchAlgorithmException {
        this.jwtConfiguration = jwtConfiguration;

        rsaCipherHandler = new RSACipherHandler(jwtConfiguration.getSecret());
        jwtParser = Jwts.parser()
                .verifyWith(rsaCipherHandler.getPublicKey())
                .requireIssuer(jwtConfiguration.getIssuer())
                .build();
    }


    @Override
    public Claims toClaims(String token) {
        var signedClaims = parseToken(token);
        return signedClaims.getPayload();
    }

    @Override
    public Jws<Claims> parseToken(String token) throws JwtException, IllegalArgumentException {
        return jwtParser.parseSignedClaims(token);
    }


    @Override
    public boolean verify(String token) {
        var claims = toClaims(token);
        return claims.getExpiration().before(DateUtils.now());
    }

    @Override
    public String generate(String sub, Map<String, Object> customClaims) throws NoSuchAlgorithmException, InvalidKeySpecException {
        var now = new Date();
        var exp = DateUtils.plusMinutes(now, jwtConfiguration.getAccessDuration());
        return Jwts.builder()
                .subject(sub)
                .issuer(jwtConfiguration.getIssuer())
                .claims(customClaims)
                .expiration(exp)
                .issuedAt(now)
                .id(UUID.randomUUID().toString())
                .signWith(rsaCipherHandler.getPrivateKey())
                .compact();
    }

    @Override
    public String generate(TokenProps tokenProps) {
        var now = new Date();
        var exp = DateUtils.plusMinutes(now, tokenProps.getDurationInMinutes());
        return Jwts.builder()
                .subject(tokenProps.getSub())
                .issuer(jwtConfiguration.getIssuer())
                .claims(tokenProps.getCustomClaims())
                .issuedAt(now)
                .expiration(exp)
                .id(UUID.randomUUID().toString())
                .signWith(rsaCipherHandler.getPrivateKey())
                .compact();
    }

}
