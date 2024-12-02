package com.spring.demo.core.user.infrastructure.entity;

import com.spring.demo.core.user.domain.UserStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserEntityRepository extends JpaRepository<UserEntity, Long> {

    boolean existsByIdAndStatus(Long id, UserStatus status);

    Optional<UserEntity> findByIdAndStatus(Long id, UserStatus status);

}
