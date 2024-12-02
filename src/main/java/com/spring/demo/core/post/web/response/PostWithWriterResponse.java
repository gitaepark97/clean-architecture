package com.spring.demo.core.post.web.response;

import com.spring.demo.core.post.domain.PostWithWriter;

public record PostWithWriterResponse(
    PostResponse post,
    WriterResponse writer
) {

    public static PostWithWriterResponse from(PostWithWriter postWithWriter) {
        return new PostWithWriterResponse(
            PostResponse.from(postWithWriter.post()),
            WriterResponse.from(postWithWriter.writer())
        );
    }

}
