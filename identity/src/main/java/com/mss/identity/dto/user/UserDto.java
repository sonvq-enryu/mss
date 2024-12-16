package com.mss.identity.dto.user;

import com.mss.identity.model.enumeration.Gender;
import com.mss.identity.model.enumeration.UserRole;
import lombok.Data;

public class UserDto {

    @Data
    public static class UserInformation {
        private String username;
        private UserRole role;
        private String firstName;
        private String lastName;
        private Gender gender;
        private String phoneNumber;
        private String address;
        private String birthDate;
    }
}
