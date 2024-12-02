package com.spring.demo.core.post.domain;

import com.spring.demo.global.exception.ApplicationException;
import com.spring.demo.global.exception.ErrorCode;
import com.spring.demo.global.provider.port.ClockProvider;
import lombok.Builder;

@Builder(toBuilder = true)
public record Post(
    Long id,
    Long writerId,
    String content,
    Long createdAt,
    Long updatedAt
) {

    public static Post create(Long writerId, String content, ClockProvider clockProvider) {
        return Post.builder()
            .writerId(writerId)
            .content(content)
            .createdAt(clockProvider.millis())
            .updatedAt(clockProvider.millis())
            .build();
    }

    public Post update(Long writerId, String content, ClockProvider clockProvider) {
        if (!this.writerId.equals(writerId)) {
            throw new ApplicationException(ErrorCode.ONLY_WRITER_CAN_UPDATE);
        }

        return toBuilder()
            .content(content)
            .updatedAt(clockProvider.millis())
            .build();
    }

}
