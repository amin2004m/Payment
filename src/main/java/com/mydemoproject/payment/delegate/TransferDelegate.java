package com.mydemoproject.payment.delegate;

import com.mydemoproject.payment.service.CardService;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Component("TransferDelegate")
@RequiredArgsConstructor
public class TransferDelegate implements JavaDelegate {

    private final CardService cardId;

    @Override
    public void execute(DelegateExecution execution) {
        Long debitorId = (Long) execution.getVariable("debitorId");
        Long creditorId = (Long) execution.getVariable("creditorId");
        BigDecimal amount = (BigDecimal) execution.getVariable("amount");

        cardId.transferCard(debitorId, creditorId, amount);
    }
}
