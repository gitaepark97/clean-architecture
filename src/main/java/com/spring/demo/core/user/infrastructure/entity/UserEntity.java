package com.spring.demo.core.user.infrastructure.entity;

import com.spring.demo.core.user.domain.User;
import com.spring.demo.core.user.domain.UserStatus;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@Getter
@Builder
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@AllArgsConstructor(access = AccessLevel.PRIVATE)
@Entity(name = "\"user\"")
@DynamicInsert
@DynamicUpdate
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String email;

    private String nickname;

    private String address;

    private String certificationCode;

    @Enumerated(EnumType.STRING)
    private UserStatus status;

    private Long lastLoginAt;

    public static UserEntity from(User user) {
        return UserEntity.builder()
            .id(user.id())
            .email(user.email())
            .nickname(user.nickname())
            .address(user.address())
            .certificationCode(user.certificationCode())
            .status(user.status())
            .lastLoginAt(user.lastLoginAt())
            .build();
    }

    public User toUser() {
        return User.builder()
            .id(id)
            .email(email)
            .nickname(nickname)
            .address(address)
            .certificationCode(certificationCode)
            .status(status)
            .lastLoginAt(lastLoginAt)
            .build();
    }

}
