package com.mss.identity.mapper;

import com.mss.identity.dto.auth.AuthDto;
import com.mss.identity.dto.user.UserDto;
import com.mss.identity.model.postgres.UserProfileEntity;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {
    UserProfileEntity toEntity(AuthDto.SignUpRequest request);
    UserDto.UserInformation toUserInformation(UserProfileEntity userProfileEntity);
}
