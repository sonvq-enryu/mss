package com.mss.identity.dto.auth;

import com.mss.identity.dto.user.UserDto;
import com.mss.identity.model.enumeration.Gender;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

public class AuthDto {

    @Getter
    @Setter

    public static class SignInRequest {
        @NotBlank(message = "Email cannot be empty")
        @Email(message = "Not in email format")
        private String username;

        @NotBlank(message = "Password cannot be empty")
        @Size(min = 6, message = "Password must be at least 6 characters")
        private String password;
    }


    @Getter
    @Setter
    public static class SignUpRequest extends SignInRequest {
        private String firstName;
        private String lastName;
        private LocalDate birthDate;
        private String phoneNumber;
        private String address;
        private Gender gender;
    }

    @Getter
    @Setter
    @Builder
    public static class SignInResponse {
        private String accessToken;
        private String refreshToken;
        private long expiredAt;
    }

    @Getter
    @Setter
    @NoArgsConstructor
    @AllArgsConstructor
    public static class SignUpResponse {
        private String accessToken;
        private String refreshToken;
        private long expiredAt;
        private UserDto.UserInformation userInformation;
    }
}
