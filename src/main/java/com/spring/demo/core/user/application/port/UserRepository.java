package com.spring.demo.core.user.application.port;

import com.spring.demo.core.user.domain.User;
import com.spring.demo.core.user.domain.UserStatus;

import java.util.Optional;

public interface UserRepository {

    boolean existsByIdAndStatus(Long id, UserStatus status);

    Optional<User> findById(Long id);

    Optional<User> findByIdAndStatus(Long id, UserStatus status);

    User save(User user);

}
