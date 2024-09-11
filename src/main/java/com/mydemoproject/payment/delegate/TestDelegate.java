package com.mydemoproject.payment.delegate;

import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Component("TestDelegate")
public class TestDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution delegateExecution) {
        System.err.println("delegate e daxil oldu");

        delegateExecution.setVariable("salam", "salammm");
    }


}
