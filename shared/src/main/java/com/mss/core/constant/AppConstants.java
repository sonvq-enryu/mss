package com.mss.core.constant;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.experimental.Accessors;

public class AppConstants {


    @Getter
    @Accessors(chain = true, fluent = true)
    @AllArgsConstructor(access = AccessLevel.PACKAGE)
    public enum CustomClaim {
        UID("uid", "Unique ID"),
        IP("ip", "IP"),
        ROLE("role", "Role of user"),
        SCOPE("scope", "Token scopes");

        private final String name;
        private final String description;
    }

    @Getter
    @Accessors(chain = true, fluent = true)
    @AllArgsConstructor(access = AccessLevel.PACKAGE)
    public enum CustomJwtScope {
        REFRESH_TOKEN("refresh_token", "Refresh token scope"),
        RESET_PASSWORD("reset_password", "Reset password scope");

        private final String name;
        private final String description;
    }

    @Getter
    @Accessors(chain = true, fluent = true)
    @AllArgsConstructor(access = AccessLevel.PACKAGE)
    public enum StatusCode {
        OK(200, ""),
        NOT_FOUND(404, "Data not found"),
        BAD_REQUEST(400, "Bad request"),
        UNAUTHORIZED(401, "Unauthorized"),
        FORBIDDEN(403, "Forbidden"),
        SERVER_ERROR(500, "Internal server error");

        private final int code;
        private final String error;
    }
}
