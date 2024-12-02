package com.spring.demo.core.user.infrastructure;

import com.spring.demo.core.user.application.port.UserRepository;
import com.spring.demo.core.user.domain.User;
import com.spring.demo.core.user.domain.UserStatus;
import com.spring.demo.core.user.infrastructure.entity.UserEntity;
import com.spring.demo.core.user.infrastructure.entity.UserEntityRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@RequiredArgsConstructor
@Repository
class UserRepositoryImpl implements UserRepository {

    private final UserEntityRepository userEntityRepository;

    @Override
    public boolean existsByIdAndStatus(Long id, UserStatus status) {
        return userEntityRepository.existsByIdAndStatus(id, status);
    }

    @Override
    public Optional<User> findById(Long id) {
        return userEntityRepository.findById(id).map(UserEntity::toUser);
    }

    @Override
    public Optional<User> findByIdAndStatus(Long id, UserStatus status) {
        return userEntityRepository.findByIdAndStatus(id, status).map(UserEntity::toUser);
    }

    @Override
    public User save(User user) {
        return userEntityRepository.save(UserEntity.from(user)).toUser();
    }

}
