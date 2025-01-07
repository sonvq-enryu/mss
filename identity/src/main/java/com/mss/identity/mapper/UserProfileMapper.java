package com.mss.identity.mapper;

import com.mss.identity.dto.auth.AuthDto;
import com.mss.identity.model.postgres.UserProfileEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserProfileMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "user", ignore = true)
    UserProfileEntity toEntity(AuthDto.SignUpRequest request);
}
