package com.spring.demo.global.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class ApplicationExceptionHandler {

    @ExceptionHandler(ApplicationException.class)
    public ApplicationException handleApplicationException(ApplicationException e) {
        return e;
    }

    @ExceptionHandler(Exception.class)
    public ApplicationException handleException(Exception e) {
        log.error(e.getMessage(), e);
        return new ApplicationException(ErrorCode.INTERNAL_SERVER_ERROR);
    }

}
