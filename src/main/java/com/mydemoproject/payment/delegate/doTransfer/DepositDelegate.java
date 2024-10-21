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

    private final CardController cardController;

    @Override
    public void execute(DelegateExecution execution) {
        Long cardId = (Long) execution.getVariable("cardId");
        BigDecimal amount = (BigDecimal) execution.getVariable("amount");
        BigDecimal balance = (BigDecimal) execution.getVariable("balance");
        String cardNumber = (String) execution.getVariable("cardNumber");

//        cardController.deposit(cardId,amount,balance,cardNumber);
        log.info("Deposit delegate init");
//        try {
//            BigDecimal updatedBalance = cardService.depositToCard(cardId, amount, balance, cardNumber);
//            execution.setVariable("updatedBalance", updatedBalance);
//            log.info("Deposit successful for cardId: {}", cardId);
//        } catch (Exception e) {
//            log.error("Error during deposit for cardId: {}", cardId, e);
//            throw new RuntimeException("Error during deposit", e);
//        }
    }
}
