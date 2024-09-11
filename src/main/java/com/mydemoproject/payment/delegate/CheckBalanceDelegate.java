package com.mydemoproject.payment.delegate;

import com.mydemoproject.payment.service.CardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Slf4j
@Component("CheckBalanceDelegate")
@RequiredArgsConstructor
public class CheckBalanceDelegate implements JavaDelegate {

    private final CardService cardService;

    @Override
    public void execute(DelegateExecution execution) {
        Long cardId = (Long) execution.getVariable("cardId");

        try {
            BigDecimal balance = cardService.CheckBalance(cardId);
            execution.setVariable("balance", balance);
            log.info("Balance check successful for cardId: {}", cardId);
        } catch (Exception e) {
            log.error("Error during balance check for cardId: {}", cardId, e);
            throw new RuntimeException("Error during balance check", e);
        }
    }

}
