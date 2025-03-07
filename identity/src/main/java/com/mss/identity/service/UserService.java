package com.mss.identity.service;

import com.mss.identity.dto.auth.AuthDto;
import com.mss.identity.model.postgres.UserEntity;

import java.util.Optional;

public interface UserService {

    Optional<UserEntity> findUserByUsername(String username);

    UserEntity createUser(UserEntity user);

    UserEntity createUserIncludeProfile(AuthDto.SignUpRequest request);
}
