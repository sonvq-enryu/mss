package com.mss.identity.mapper;

import com.mss.identity.dto.auth.AuthDto;
import com.mss.identity.model.postgres.UserEntity;
import com.mss.identity.model.postgres.UserProfileEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {
    @Mapping(target = "id", ignore = true)
    UserEntity toEntity(AuthDto.SignUpRequest signUpRequest, String hashPassword, UserProfileEntity profile);
}
