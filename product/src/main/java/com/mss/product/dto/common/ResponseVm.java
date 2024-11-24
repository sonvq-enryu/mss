package com.mss.product.dto.common;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.util.List;

@Data
@AllArgsConstructor
public class ResponseVm<T> {
    private int code;
    private String message;
    private List<String> errors;
    private T response;

    public static <T> ResponseVm<T> success(T response) {
        return new ResponseVm<>(200, "", null, response);
    }

    public static ResponseVm<Void> error(int code, String message, List<String> errors) {
        return new ResponseVm<>(code, message, errors, null);
    }
}
