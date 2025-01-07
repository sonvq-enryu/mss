package com.mss.core.exception;

import com.mss.core.constant.AppConstants;
import lombok.Getter;
import lombok.experimental.Accessors;

@Getter
@Accessors(chain = true, fluent = true)
public class ApiException extends Exception {
    private final AppConstants.StatusCode statusCode;
    private final String message;

    public ApiException(AppConstants.StatusCode code, String msg) {
        statusCode = code;
        message = msg;
    }
}
