package com.spring.demo.core.user.web.request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;

public record CreateUserRequest(
    @NotEmpty
    @Email
    String email,

    @NotEmpty
    String nickname,

    @NotEmpty
    String address
) {

}
