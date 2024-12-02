package com.spring.demo.core.user.application;

import com.spring.demo.core.user.application.port.UserRepository;
import com.spring.demo.core.user.domain.User;
import com.spring.demo.core.user.domain.UserStatus;
import com.spring.demo.global.exception.ApplicationException;
import com.spring.demo.global.exception.ErrorCode;
import com.spring.demo.global.provider.port.ClockProvider;
import com.spring.demo.global.provider.port.UuidProvider;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.Map;

@RequiredArgsConstructor
@Service
public class UserService {

    private final ClockProvider clockProvider;
    private final UuidProvider uuidProvider;
    private final UserRepository userRepository;
    private final CertificationProcessor certificationProcessor;

    public void validateExistUser(Long userId) {
        if (!userRepository.existsByIdAndStatus(userId, UserStatus.ACTIVE)) {
            throw new ApplicationException(ErrorCode.NOT_FOUND_USER, Map.of("id", userId));
        }
    }


    public User getUser(Long userId) {
        return userRepository.findByIdAndStatus(userId, UserStatus.ACTIVE)
            .orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND_USER, Map.of("id", userId)));
    }

    @Transactional
    public User createUser(String email, String nickname, String address) {
        User user = User.create(email, nickname, address, uuidProvider);
        user = userRepository.save(user);

        certificationProcessor.send(user.email(), user.id(), user.certificationCode());

        return user;
    }

    @Transactional
    public User updateUser(Long userId, String nickname, String address) {
        User user = getUser(userId);

        user = user.update(nickname, address);
        user = userRepository.save(user);

        return user;
    }

    @Transactional
    public void verifyEmail(Long userId, String certificationCode) {
        User user = userRepository.findById(userId)
            .orElseThrow(() -> new ApplicationException(ErrorCode.NOT_FOUND_USER, Map.of("id", userId)));

        user = user.certificate(certificationCode);
        userRepository.save(user);
    }

    @Transactional
    public void login(Long userId) {
        User user = getUser(userId);

        user = user.login(clockProvider);
        userRepository.save(user);
    }

}
