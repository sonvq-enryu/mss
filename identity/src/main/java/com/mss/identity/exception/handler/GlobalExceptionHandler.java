package com.mss.identity.exception.handler;

import com.mss.identity.dto.common.ErrorVm;
import io.swagger.v3.oas.annotations.Hidden;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@Slf4j
@Hidden
@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = {UsernameNotFoundException.class})
    public ResponseEntity<ErrorVm> handleException(UsernameNotFoundException e) {
        log.error("Exception occurred", e);
        return ResponseEntity.status(403).body(new ErrorVm(400, "Username or password not found", "Username or password not found"));
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ErrorVm> handleException(Exception e) {
        log.error("Exception occurred", e);
        return ResponseEntity.status(500).body(new ErrorVm(500, "Internal Server Error", "Internal Server Error"));
    }
}
