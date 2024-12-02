package com.spring.demo.core.post.web.response;

import com.spring.demo.core.post.domain.Writer;

public record WriterResponse(
    Long id,
    String nickname
) {

    public static WriterResponse from(Writer writer) {
        return new WriterResponse(
            writer.id(),
            writer.nickname()
        );
    }

}
