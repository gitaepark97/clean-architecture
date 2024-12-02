package com.spring.demo.core.user.domain;

import com.spring.demo.global.exception.ApplicationException;
import com.spring.demo.global.exception.ErrorCode;
import com.spring.demo.global.provider.port.ClockProvider;
import com.spring.demo.global.provider.port.UuidProvider;
import lombok.Builder;

@Builder(toBuilder = true)
public record User(
    Long id,
    String email,
    String nickname,
    String address,
    String certificationCode,
    UserStatus status,
    Long lastLoginAt
) {

    public static User create(String email, String nickname, String address, UuidProvider uuidProvider) {
        return User.builder()
            .email(email)
            .nickname(nickname)
            .address(address)
            .status(UserStatus.PENDING)
            .certificationCode(uuidProvider.random())
            .build();
    }

    public User update(String nickname, String address) {
        return toBuilder()
            .nickname(nickname)
            .address(address)
            .build();
    }

    public User certificate(String certificationCode) {
        if (!this.certificationCode.equals(certificationCode)) {
            throw new ApplicationException(ErrorCode.MISMATCH_CERTIFICATION_CODE);
        }

        return toBuilder()
            .status(UserStatus.ACTIVE)
            .build();
    }

    public User login(ClockProvider clockProvider) {
        return toBuilder()
            .lastLoginAt(clockProvider.millis())
            .build();
    }

}
