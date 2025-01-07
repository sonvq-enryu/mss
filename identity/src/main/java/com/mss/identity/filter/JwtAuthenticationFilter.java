package com.mss.identity.filter;

import com.mss.identity.service.JwtDecoder;
import com.mss.identity.service.impl.JwtService;
import com.mss.identity.service.security.MssUserDetailsServiceImpl;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpHeaders;
import org.springframework.lang.NonNull;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

@Slf4j
@Component
@RequiredArgsConstructor
public class JwtAuthenticationFilter extends OncePerRequestFilter {
    private final JwtService jwtService;
    private final MssUserDetailsServiceImpl userDetailsService;
    private final JwtDecoder jwtDecoder;

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
        var claims =

        filterChain.doFilter(request, response);

//        final var header = request.getHeader(HttpHeaders.AUTHORIZATION);
//        if (header != null && header.startsWith("Bearer ")) {
//            final var token = header.split(" ")[1].trim();
//            String username = null;
//            if (jwtService.isValidToken(token)) {
//                username = jwtService.extractUsername(token);
//            }
//            if (username != null && SecurityContextHolder.getContext().getAuthentication() == null) {
//                var userDetails = userDetailsService.loadUserByUsername(username);
//                var authentication = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
//                SecurityContextHolder.getContext().setAuthentication(authentication);
//            }
//        }
//        filterChain.doFilter(request, response);
    }

    private boolean validate() {

    }
}
