package com.mydemoproject.payment.delegate;

import com.mydemoproject.payment.service.UserService;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("DeleteUserDelegate")
@RequiredArgsConstructor
public class DeleteUserDelegate implements JavaDelegate {

    private final UserService userService;

    @Override
    public void execute(DelegateExecution execution) {

        Long userId = (Long) execution.getVariable("userId");

        userService.deleteUserById(userId);
    }
}