package com.mss.identity.service.impl;

import com.mss.identity.dto.auth.AuthDto;
import com.mss.identity.mapper.UserMapper;
import com.mss.identity.mapper.UserProfileMapper;
import com.mss.identity.model.enumeration.UserRole;
import com.mss.identity.model.postgres.UserEntity;
import com.mss.identity.repository.postgres.UserRepository;
import com.mss.identity.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserMapper userMapper;
    private final UserProfileMapper userProfileMapper;
    private final PasswordEncoder passwordEncoder;

    @Override
    public Optional<UserEntity> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public UserEntity createUser(UserEntity user) {
        return userRepository.save(user);
    }

    @Override
    @Transactional
    public UserEntity createUserIncludeProfile(AuthDto.SignUpRequest request) {
        var userProfile = userProfileMapper.toEntity(request);
        var user = userMapper.toEntity(request, passwordEncoder.encode(request.getPassword()), userProfile);
        user.setRole(UserRole.USER);

        return userRepository.save(user);
    }
}
