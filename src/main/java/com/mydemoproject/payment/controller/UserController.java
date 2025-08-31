package com.mydemoproject.payment.controller;


import com.mydemoproject.payment.dto.request.UserRequest;
import com.mydemoproject.payment.dto.response.UserResponse;
import com.mydemoproject.payment.service.UserService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.TreeSet;


@Slf4j
@RestController
@RequestMapping("v1/payment")
@RequiredArgsConstructor
public class UserController {

    private final UserService userService;
    private final RuntimeService runtimeService;

    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    public UserResponse createUser(@Valid @RequestBody UserRequest request) {
        String action = "register";
        runtimeService.createProcessInstanceByKey("registerProcess")
                .setVariable("action", action);
        return userService.createUser(request);
    }

    @GetMapping("/login")
    public UserResponse login(@Valid @RequestParam String username, @RequestParam String password) {
        log.info("Login successful");
        String action = "login";
        runtimeService.createProcessInstanceByKey("LoginProcess")
                .setVariable("username", username)
                .setVariable("password", password)
                .setVariable("action", action)
                .execute();

        return userService.login(username, password);
    }

    @DeleteMapping("/delete/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void deleteUserById(@Valid @PathVariable Long id) {
        String action = "delete";
        runtimeService.startProcessInstanceByKey("LoginProcess", action);
        userService.deleteUserById(id);
    }

}
