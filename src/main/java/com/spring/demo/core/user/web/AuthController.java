package com.spring.demo.core.user.web;

import com.spring.demo.core.user.application.UserService;
import com.spring.demo.core.user.web.request.CreateUserRequest;
import com.spring.demo.core.user.web.response.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/auth")
public class AuthController {

    private final UserService userService;

    @PostMapping("/register")
    @ResponseStatus(HttpStatus.CREATED)
    UserResponse createUser(@RequestBody @Valid CreateUserRequest request) {
        return UserResponse.from(userService.createUser(request.email(), request.nickname(), request.address()));
    }

    @GetMapping("/verify")
    ResponseEntity<Void> verifyEmail(
        @RequestParam Long userId,
        @RequestParam String certificationCode
    ) {
        userService.verifyEmail(userId, certificationCode);

        return ResponseEntity.status(HttpStatus.FOUND)
            .location(URI.create("http://localhost:3000"))
            .build();
    }

    @PostMapping("/login")
    void login(@RequestParam Long userId) {
        userService.login(userId);
    }

}
