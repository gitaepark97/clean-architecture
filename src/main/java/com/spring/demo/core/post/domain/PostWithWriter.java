package com.spring.demo.core.post.domain;

import com.spring.demo.core.user.domain.User;

public record PostWithWriter(
    Post post,
    Writer writer
) {

    public static PostWithWriter from(Post post, User user) {
        return new PostWithWriter(post, Writer.from(user));
    }

}
