package com.spring.demo.core.post.web.response;

import com.spring.demo.core.post.domain.Post;

public record PostResponse(
    Long id,
    String content,
    Long createdAt,
    Long updatedAt
) {

    public static PostResponse from(Post post) {
        return new PostResponse(
            post.id(),
            post.content(),
            post.createdAt(),
            post.updatedAt()
        );
    }

}
