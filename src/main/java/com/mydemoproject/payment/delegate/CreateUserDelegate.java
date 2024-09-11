package com.mydemoproject.payment.delegate;

import com.mydemoproject.payment.dto.request.UserRequest;
import com.mydemoproject.payment.dto.response.UserResponse;
import com.mydemoproject.payment.entity.User;
import com.mydemoproject.payment.service.UserService;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("CreateUserDelegate")
@RequiredArgsConstructor
public class CreateUserDelegate implements JavaDelegate {
    private final UserService userService;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        UserRequest userRequest = new UserRequest();
        userRequest.setFirstName((String) execution.getVariable("firstName"));
        userRequest.setUsername((String) execution.getVariable("username"));
        userRequest.setSurname((String) execution.getVariable("surname"));
        userRequest.setPassword((String) execution.getVariable("password"));
        userRequest.setAddress((String) execution.getVariable("address"));
        userRequest.setEmail((String) execution.getVariable("email"));
        userRequest.setDocFin((String) execution.getVariable("docFin"));
        userRequest.setPhoneNumber((String) execution.getVariable("phoneNumber"));

        userService.createUser(userRequest);
    }



}
