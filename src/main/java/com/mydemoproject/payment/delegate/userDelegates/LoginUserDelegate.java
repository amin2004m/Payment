package com.mydemoproject.payment.delegate.userDelegates;


import com.mydemoproject.payment.controller.UserController;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.RuntimeService;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Data
@Component("LoginUserDelegate")
@RequiredArgsConstructor
public class LoginUserDelegate implements JavaDelegate {

    private final UserController userController;
    private final RuntimeService runtimeService;

    @Override
    public void execute(DelegateExecution execution) {


        System.err.println("LOGIN DELEGATE ");
        String username = (String) execution.getVariable("username");
        String password = (String) execution.getVariable("password");
        String action = (String) execution.getVariable("action");

//        userController.login(username, password);

        if ("login".equals(action)) {
            System.out.println("User successfully logged in.");
        }

    }
}
