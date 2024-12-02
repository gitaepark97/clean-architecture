package com.spring.demo.global.response;

import com.spring.demo.global.exception.ApplicationException;
import org.springframework.http.HttpStatus;

public record ApiResponse<T>(
    HttpStatus status,
    String message,
    T data
) {

    public static <T> ApiResponse<T> success(HttpStatus status, T data) {
        return new ApiResponse<>(status, "성공", data);
    }

    public static ApiResponse<?> fail(ApplicationException e) {
        return new ApiResponse<>(e.getStatus(), e.getMessage(), e.getData());
    }

}
