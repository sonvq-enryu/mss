package com.mss.identity.service;

import com.mss.identity.model.postgres.UserProfileEntity;

public interface UserProfileService {
    UserProfileEntity createUserProfile(UserProfileEntity userProfileEntity);
}
