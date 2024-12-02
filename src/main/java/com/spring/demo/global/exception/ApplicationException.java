package com.spring.demo.global.exception;

import lombok.Getter;
import org.springframework.http.HttpStatus;

import java.util.Map;

@Getter
public class ApplicationException extends RuntimeException {

    private final HttpStatus status;
    private final Map<String, Object> data;

    public ApplicationException(ErrorCode code, Map<String, Object> data) {
        super(code.getMessage());
        this.status = code.getStatus();
        this.data = data;
    }

    public ApplicationException(ErrorCode code) {
        this(code, null);
    }

}
