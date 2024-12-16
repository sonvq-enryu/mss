package com.mss.identity.service.impl;

import com.mss.identity.model.postgres.UserEntity;
import com.mss.identity.repository.postgres.UserRepository;
import com.mss.identity.service.UserProfileService;
import com.mss.identity.service.UserService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {
    private final UserRepository userRepository;
    private final UserProfileService userProfileService;

    @Override
    public Optional<UserEntity> findUserByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    @Transactional
    public UserEntity createUser(UserEntity user) {
        return userRepository.save(user);
    }
}
