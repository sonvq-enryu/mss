package com.mss.identity.service.impl;

import com.mss.identity.model.postgres.UserProfileEntity;
import com.mss.identity.repository.postgres.UserProfileRepository;
import com.mss.identity.service.UserProfileService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Slf4j
@Service
@RequiredArgsConstructor
public class UserProfileServiceImpl implements UserProfileService {
    private final UserProfileRepository userProfileRepository;

    @Override
    @Transactional
    public UserProfileEntity createUserProfile(UserProfileEntity userProfileEntity) {
        return userProfileRepository.save(userProfileEntity);
    }

    public UserProfileEntity findByUserId(UUID userId) {
        return null;
    }
}
