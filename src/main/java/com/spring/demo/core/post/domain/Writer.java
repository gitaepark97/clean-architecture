package com.spring.demo.core.post.domain;

import com.spring.demo.core.user.domain.User;

public record Writer(
    Long id,
    String nickname
) {

    public static Writer from(User user) {
        return new Writer(user.id(), user.nickname());
    }

}
