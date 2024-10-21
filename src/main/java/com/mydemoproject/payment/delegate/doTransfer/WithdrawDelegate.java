package com.mydemoproject.payment.delegate.doTransfer;

import com.mydemoproject.payment.controller.CardController;
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

    @Override
    public void execute(DelegateExecution execution) {
        Long cardId = (Long) execution.getVariable("cardId");
        BigDecimal amount = (BigDecimal) execution.getVariable("amount");
        BigDecimal balance = (BigDecimal) execution.getVariable("balance");

        try {
            log.info("Withdrawal successful for cardId: {}", cardId);
        } catch (Exception e) {
            log.error("Error during withdrawal for cardId: {}", cardId, e);
            throw new RuntimeException("Error during withdrawal", e);
        }
    }
}
