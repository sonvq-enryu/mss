package com.mss.identity.dto.common;

import com.mss.core.exception.ApiException;

public record ErrorVm(int code, String error, String message) {

    public ErrorVm(ApiException exception, String message) {
        this(exception.statusCode().code(), exception.statusCode().error(), message);
    }

    public ErrorVm(ApiException ex) {
        this(ex.statusCode().code(), ex.statusCode().error(), ex.statusCode().error());
    }
}
