package com.spring.demo.core.post.web.request;

import jakarta.validation.constraints.NotEmpty;

public record UpdatePostRequest(
    @NotEmpty
    String content
) {

}
