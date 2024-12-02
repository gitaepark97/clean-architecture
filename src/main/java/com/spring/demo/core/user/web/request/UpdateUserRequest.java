package com.spring.demo.core.user.web.request;

import jakarta.validation.constraints.NotEmpty;

public record UpdateUserRequest(
    @NotEmpty
    String nickname,

    @NotEmpty
    String address
) {
    
}
