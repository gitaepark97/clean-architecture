package com.spring.demo.core.user.web.response;

import com.spring.demo.core.user.domain.User;
import com.spring.demo.core.user.domain.UserStatus;

public record UserResponse(
    Long id,
    String email,
    String nickname,
    String address,
    UserStatus status,
    Long lastLoginAt
) {

    public static UserResponse from(User user) {
        return new UserResponse(
            user.id(),
            user.email(),
            user.nickname(),
            user.address(),
            user.status(),
            user.lastLoginAt()
        );
    }

}
