package com.mss.core.response;

public record Response<T>(int code, String message, String error, T data) {

}
