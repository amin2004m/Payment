package com.mydemoproject.payment.delegate;


import com.mydemoproject.payment.entity.User;
import com.mydemoproject.payment.service.UserService;
import lombok.Data;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Data
@Component("LoginUserDelegate")
@RequiredArgsConstructor
public class LoginUserDelegate implements JavaDelegate {

    private final UserService userService;

    @Override
    public void execute(DelegateExecution execution) {


        System.err.println("LOGIN DELEGATE ");
        String username = (String) execution.getVariable("username");
        String password = (String) execution.getVariable("password");

        userService.login(username, password);



    }
}
