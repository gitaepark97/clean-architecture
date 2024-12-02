package com.spring.demo.core.user.domain;

import com.spring.demo.global.exception.ApplicationException;
import com.spring.demo.global.provider.port.ClockProvider;
import com.spring.demo.global.provider.port.UuidProvider;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;
import static org.junit.jupiter.api.Assertions.assertThrows;

class UserTest {

    private final ClockProvider clockProvider = () -> 12354345134L;
    private final UuidProvider uuidProvider = () -> "test-uuid-1234";

    @Test
    public void create_객체를_생성할_수_있다() {
        // given

        // when
        User user = User.create("user1@email.com", "회원1", "대한민국", uuidProvider);

        // then
        assertAll(
            () -> assertThat(user.id()).isNull(), // ID는 null
            () -> assertThat(user.email()).isEqualTo("user1@email.com"),
            () -> assertThat(user.nickname()).isEqualTo("회원1"),
            () -> assertThat(user.address()).isEqualTo("대한민국"),
            () -> assertThat(user.certificationCode()).isEqualTo("test-uuid-1234"),
            () -> assertThat(user.status()).isEqualTo(UserStatus.PENDING),
            () -> assertThat(user.lastLoginAt()).isNull() // 마지막 로그인 시간은 null
        );
    }

    @Test
    public void update_객체를_수정할_수_있다() {
        // given
        User user = User.create("user1@email.com", "회원1", "대한민국", uuidProvider);

        // when
        User updatedUser = user.update("회원2", "서울특별시");

        // then
        assertAll(
            () -> assertThat(updatedUser.id()).isNull(),
            () -> assertThat(updatedUser.email()).isEqualTo(user.email()), // 이메일은 변경되지 않음
            () -> assertThat(updatedUser.nickname()).isEqualTo("회원2"),
            () -> assertThat(updatedUser.address()).isEqualTo("서울특별시"),
            () -> assertThat(updatedUser.certificationCode()).isEqualTo(user.certificationCode()),
            () -> assertThat(updatedUser.status()).isEqualTo(user.status()), // 상태는 변경되지 않음
            () -> assertThat(updatedUser.lastLoginAt()).isEqualTo(user.lastLoginAt()) // 마지막 로그인 시간은 변경되지 않음
        );
    }

    @Test
    public void certificate_인증코드가_일치하면_상태를_갱신할_수_있다() {
        // given
        User user = User.create("user1@email.com", "회원1", "대한민국", uuidProvider);

        // when
        User certifiedUser = user.certificate("test-uuid-1234");

        // then
        assertAll(
            () -> assertThat(certifiedUser.id()).isNull(),
            () -> assertThat(certifiedUser.email()).isEqualTo(user.email()),
            () -> assertThat(certifiedUser.status()).isEqualTo(UserStatus.ACTIVE) // 상태가 ACTIVE로 변경됨
        );
    }

    @Test
    public void certificate_인증코드가_일치하지_않으면_예외가_발생한다() {
        // given
        User user = User.create("user1@email.com", "회원1", "대한민국", uuidProvider);

        // then
        assertThrows(ApplicationException.class, () -> {
            // when
            user.certificate("wrong-uuid");
        });
    }

    @Test
    public void login_로그인_시간을_갱신할_수_있다() {
        // given
        User user = User.create("user1@email.com", "회원1", "대한민국", uuidProvider);

        // when
        User loggedInUser = user.login(clockProvider);

        // then
        assertAll(
            () -> assertThat(loggedInUser.id()).isNull(),
            () -> assertThat(loggedInUser.email()).isEqualTo(user.email()),
            () -> assertThat(loggedInUser.lastLoginAt()).isEqualTo(12354345134L)
        );
    }

}