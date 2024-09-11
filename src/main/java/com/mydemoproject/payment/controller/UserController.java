package com.mydemoproject.payment.controller;


import com.mydemoproject.payment.dto.request.UserRequest;
import com.mydemoproject.payment.dto.response.UserResponse;
import com.mydemoproject.payment.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("v1/payment")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createUser(@Valid @RequestBody UserRequest request) {
        return userService.createUser(request);
    }

    @GetMapping("/login")
    public UserResponse login(@Valid @RequestParam String username, @RequestParam String password) {
        log.info("Login successful");
        return userService.login(username, password);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserById(@Valid   @PathVariable Long id) {
        userService.deleteUserById(id);
    }

}
