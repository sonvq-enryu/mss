package com.mss.identity.filter;

import com.mss.core.utils.DateUtils;
import com.mss.identity.service.InvalidateTokenService;
import com.mss.identity.service.JwtDecoder;
import io.jsonwebtoken.Claims;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;
import java.util.Collections;
import java.util.UUID;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtDecoder jwtDecoder;
    private final InvalidateTokenService invalidateTokenService;

    private static final String PREFIX_TOKEN = "Bearer ";

    @Override
    protected void doFilterInternal(@NonNull HttpServletRequest request, @NonNull HttpServletResponse response,
                                    @NonNull FilterChain filterChain) throws ServletException, IOException {

        final var authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);

        if (authHeader == null || !authHeader.startsWith(PREFIX_TOKEN)) {
            filterChain.doFilter(request, response);
            return ;
        }

        final var token = authHeader.split(" ")[1].trim();
        var claims = jwtDecoder.parseToken(token);
        var authToken = new UsernamePasswordAuthenticationToken(claims, null, Collections.emptyList());
        SecurityContextHolder.getContext().setAuthentication(authToken);
        filterChain.doFilter(request, response);
    }

    private boolean isAuth(Claims claims) {
        if (claims.getExpiration().after(DateUtils.now())) {
            return false;
        }

        // token is black list
        if (invalidateTokenService.isBlackList(UUID.fromString(claims.getId()))) {
            return false;
        }
        return true;
    }
}
