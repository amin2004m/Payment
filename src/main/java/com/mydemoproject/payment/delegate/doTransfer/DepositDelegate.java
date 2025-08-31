package com.mydemoproject.payment.delegate.doTransfer;

import com.mydemoproject.payment.controller.CardController;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Slf4j
@Component("DepositDelegate")
@RequiredArgsConstructor
public class DepositDelegate implements JavaDelegate {

    @Override
    public void execute(DelegateExecution execution) {
        Long cardId = (Long) execution.getVariable("cardId");
        BigDecimal amount = (BigDecimal) execution.getVariable("amount");
        BigDecimal balance = (BigDecimal) execution.getVariable("balance");
        String cardNumber = (String) execution.getVariable("cardNumber");

        log.info("Deposit delegate init");
    }

}
