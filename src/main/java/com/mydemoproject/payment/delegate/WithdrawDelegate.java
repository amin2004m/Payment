package com.mydemoproject.payment.delegate;

import com.mydemoproject.payment.dto.response.CardResponse;
import com.mydemoproject.payment.service.CardService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Slf4j
@Component("WithdrawDelegate")
@RequiredArgsConstructor
public class WithdrawDelegate implements JavaDelegate {
    private final CardService cardService;

    @Override
    public void execute(DelegateExecution execution) {
        Long cardId = (Long) execution.getVariable("cardId");
        BigDecimal amount = (BigDecimal) execution.getVariable("amount");
        BigDecimal balance = (BigDecimal) execution.getVariable("balance");

        try {
            CardResponse response =
                    cardService.withdraw(cardId, amount, balance);
            execution.setVariable("transactionResponse", response);
            log.info("Withdrawal successful for cardId: {}", cardId);
        } catch (Exception e) {
            log.error("Error during withdrawal for cardId: {}", cardId, e);
            throw new RuntimeException("Error during withdrawal", e);
        }
    }
}
