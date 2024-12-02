package com.spring.demo.core.post.domain;

import com.spring.demo.global.exception.ApplicationException;
import com.spring.demo.global.exception.ErrorCode;
import com.spring.demo.global.provider.port.ClockProvider;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PostTest {

    private final ClockProvider clockProvider = () -> 12354345134L;

    @Test
    public void create_객체를_생성할_수_있다() {
        // given

        // when
        Post post = Post.create(1L, "테스트 내용", clockProvider);

        // then
        assertAll(
            () -> assertThat(post.id()).isNull(), // ID는 null
            () -> assertThat(post.writerId()).isEqualTo(1L),
            () -> assertThat(post.content()).isEqualTo("테스트 내용"),
            () -> assertThat(post.createdAt()).isEqualTo(12354345134L),
            () -> assertThat(post.updatedAt()).isEqualTo(12354345134L)
        );
    }

    @Test
    public void update_객체를_수정할_수_있다() {
        // given
        Post post = Post.create(1L, "테스트 내용", clockProvider);

        // when
        Post updatedPost = post.update(1L, "수정된 내용", clockProvider);

        // then
        assertAll(
            () -> assertThat(updatedPost.id()).isNull(),
            () -> assertThat(updatedPost.writerId()).isEqualTo(post.writerId()), // 작성자는 변경되지 않음
            () -> assertThat(updatedPost.content()).isEqualTo("수정된 내용"),
            () -> assertThat(updatedPost.createdAt()).isEqualTo(post.createdAt()), // 생성일자는 변경되지 않음
            () -> assertThat(updatedPost.updatedAt()).isEqualTo(12354345134L) // 수정일자는 변경됨
        );
    }

    @Test
    public void update_작성자가_일치하지_않으면_예외가_발생한다() {
        // given
        Post post = Post.create(1L, "테스트 내용", clockProvider);

        // then
        assertThrows(ApplicationException.class, () -> {
            // when
            post.update(2L, "수정된 내용", clockProvider);
        }, ErrorCode.ONLY_WRITER_CAN_UPDATE.getMessage());
    }

}
