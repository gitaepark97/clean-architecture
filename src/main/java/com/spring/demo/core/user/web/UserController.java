package com.spring.demo.core.user.web;

import com.spring.demo.core.user.application.UserService;
import com.spring.demo.core.user.web.response.UserResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/users")
class UserController {

    private final UserService userService;

    @GetMapping("/{userId}")
    UserResponse getUser(@PathVariable Long userId) {
        return UserResponse.from(userService.getUser(userId));
    }

}
