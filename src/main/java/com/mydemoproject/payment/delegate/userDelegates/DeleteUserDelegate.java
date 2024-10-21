package com.mydemoproject.payment.delegate.userDelegates;

import com.mydemoproject.payment.controller.UserController;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("DeleteUserDelegate")
@RequiredArgsConstructor
public class DeleteUserDelegate implements JavaDelegate {

    private final UserController userController;

    @Override
    public void execute(DelegateExecution execution) {

        Long userId = (Long) execution.getVariable("userId");

//        userController.deleteUserById(userId);
    }
}