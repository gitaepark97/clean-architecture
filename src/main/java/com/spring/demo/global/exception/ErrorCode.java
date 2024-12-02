package com.spring.demo.global.exception;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;

@RequiredArgsConstructor
@Getter
public enum ErrorCode {
    INTERNAL_SERVER_ERROR(HttpStatus.INTERNAL_SERVER_ERROR, "서버 내부 오류입니다."),

    NOT_FOUND_USER(HttpStatus.NOT_FOUND, "회원을 찾을 수 없습니다."),
    MISMATCH_CERTIFICATION_CODE(HttpStatus.BAD_REQUEST, "자격 증명에 실패하였습니다."),

    NOT_FOUND_POST(HttpStatus.NOT_FOUND, "게시글을 찾을 수 없습니다."),
    ONLY_WRITER_CAN_UPDATE(HttpStatus.FORBIDDEN, "작성자만 게시글을 수정할 수 있습니다."),
    ;

    private final HttpStatus status;
    private final String message;
}
