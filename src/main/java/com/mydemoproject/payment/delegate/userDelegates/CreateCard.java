package com.mydemoproject.payment.delegate.userDelegates;

import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("CreateCardDelegate")
@Slf4j
public class CreateCard implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) {

        Long id = (Long) execution.getVariable("id");
        String username = (String) execution.getVariable("username");
        String password = (String) execution.getVariable("password");

        log.info("Card was Created");
    }

}
