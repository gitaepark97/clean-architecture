package com.spring.demo.core.user.web;

import com.spring.demo.core.user.application.UserService;
import com.spring.demo.core.user.web.request.UpdateUserRequest;
import com.spring.demo.core.user.web.response.UserResponse;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users/me")
class MyUserController {

    private final UserService userService;

    @GetMapping
    UserResponse getMyUser(
        @RequestParam Long userId
    ) {
        return UserResponse.from(userService.getUser(userId));
    }

    @PutMapping
    UserResponse updateMyUser(
        @RequestParam Long userId,
        @RequestBody @Valid UpdateUserRequest request
    ) {
        return UserResponse.from(userService.updateUser(userId, request.nickname(), request.address()));
    }

}
