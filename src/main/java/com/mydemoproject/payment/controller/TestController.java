//package com.mydemoproject.payment.controller;
//
//
//import com.mydemoproject.payment.dto.request.UserRequest;
//import lombok.RequiredArgsConstructor;
//import org.camunda.bpm.engine.RuntimeService;
//import org.camunda.bpm.engine.runtime.ProcessInstance;
//import org.springframework.web.bind.annotation.*;
//
//import java.util.HashMap;
//import java.util.Map;
//
//
//@RestController
//@RequestMapping("/test")
//@RequiredArgsConstructor
//public class TestController {
//
//    private final RuntimeService runtimeService;
//
//    @GetMapping("/test")
//    public String foo(@RequestParam String action,
//                      @RequestParam String username,
//                      @RequestParam String password) {
//
//        Map<String, Object> variables = new HashMap<>();
//        variables.put("action",action);
//        variables.put("username", username);
//        variables.put("password", password);
//        ProcessInstance processInstance =
//                runtimeService.startProcessInstanceByKey("Process_0c4b5k4", variables);
//
//        return "Process started with ID: " +
//                "\n" + processInstance.getId() + "\n" +
//                "Username: " + username + ", password: " + password;
//    }
//
//    @PostMapping("/register")
//    public String startRegisterProcess(@RequestParam String action, @RequestBody UserRequest userRequest) {
//        Map<String, Object> variables = new HashMap<>();
//        variables.put("action", action);
//        variables.put("firstName", userRequest.getFirstName());
//        variables.put("username", userRequest.getUsername());
//        variables.put("surname", userRequest.getSurname());
//        variables.put("password", userRequest.getPassword());
//        variables.put("address", userRequest.getAddress());
//        variables.put("email", userRequest.getEmail());
//        variables.put("docFin", userRequest.getDocFin());
//        variables.put("phoneNumber", userRequest.getPhoneNumber());
//
//        runtimeService.startProcessInstanceByKey("Process_0c4b5k4", variables);
//        return "Register process started";
//    }
//
//}