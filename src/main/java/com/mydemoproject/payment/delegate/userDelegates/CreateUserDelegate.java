package com.mydemoproject.payment.delegate.userDelegates;

import com.mydemoproject.payment.controller.UserController;
import com.mydemoproject.payment.dto.request.UserRequest;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("CreateUserDelegate")
@RequiredArgsConstructor
public class CreateUserDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) {
        UserRequest userRequest = new UserRequest();
        userRequest.setFirstName((String) execution.getVariable("firstName"));
        userRequest.setUsername((String) execution.getVariable("username"));
        userRequest.setSurname((String) execution.getVariable("surname"));
        userRequest.setPassword((String) execution.getVariable("password"));
        userRequest.setAddress((String) execution.getVariable("address"));
        userRequest.setEmail((String) execution.getVariable("email"));
        userRequest.setDocFin((String) execution.getVariable("docFin"));
        userRequest.setPhoneNumber((String) execution.getVariable("phoneNumber"));


    }



}
