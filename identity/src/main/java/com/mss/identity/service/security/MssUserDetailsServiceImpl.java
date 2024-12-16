package com.mss.identity.service.security;

import com.mss.identity.service.UserService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class MssUserDetailsServiceImpl implements UserDetailsService {
    private final UserService userService;

    @Override
    public UserInformationDomain loadUserByUsername(String username) throws UsernameNotFoundException {
        var user = userService.findUserByUsername(username).orElseThrow(() -> new UsernameNotFoundException("User not found"));
        return UserInformationDomain.builder()
                .username(user.getUsername())
                .hashPassword(user.getHashPassword())
                .role(user.getRole())
                .build();
    }
}
