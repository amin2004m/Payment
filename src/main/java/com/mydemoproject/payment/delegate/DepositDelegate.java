package com.mydemoproject.payment.delegate;

import com.mydemoproject.payment.service.CardService;
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

    private final CardService cardService;

    @Override
    public void execute(DelegateExecution execution) {
        Long cardId = (Long) execution.getVariable("cardId");
        BigDecimal amount = (BigDecimal) execution.getVariable("amount");
        BigDecimal balance = (BigDecimal) execution.getVariable("balance");

        try {
            BigDecimal updatedBalance = cardService.depositToCard(cardId, amount, balance);
            execution.setVariable("updatedBalance", updatedBalance);
            log.info("Deposit successful for cardId: {}", cardId);
        } catch (Exception e) {
            log.error("Error during deposit for cardId: {}", cardId, e);
            throw new RuntimeException("Error during deposit", e);
        }
    }
}
