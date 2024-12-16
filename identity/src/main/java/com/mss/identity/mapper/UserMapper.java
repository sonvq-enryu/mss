package com.mss.identity.mapper;

import com.mss.identity.dto.auth.AuthDto;
import com.mss.identity.model.enumeration.UserRole;
import com.mss.identity.model.postgres.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mappings({
            @Mapping(target = "username", source = "request.username"),
            @Mapping(target = "hashPassword", source = "hashPassword"),
            @Mapping(target = "role", source = "role"),
    })
    UserEntity toEntity(AuthDto.SignUpRequest request, String hashPassword, UserRole role);
}
