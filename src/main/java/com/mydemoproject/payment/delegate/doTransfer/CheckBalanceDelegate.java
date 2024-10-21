package com.mydemoproject.payment.delegate.doTransfer;

import com.mydemoproject.payment.controller.CardController;
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

    private final CardController cardController;

    @Override
    public void execute(DelegateExecution execution) {
        Long cardId = (Long) execution.getVariable("cardId");
        BigDecimal amount = (BigDecimal) execution.getVariable("amount");

        try {
            boolean balanceCheck = false;
            BigDecimal balance = cardController.checkBalance(cardId);

            if(amount.compareTo(balance)>0){
                balanceCheck = true;
            }

            execution.setVariable("balance", balance);
            log.info("Balance check successful for cardId: {}", cardId);



            execution.setVariable("balanceCheck",balanceCheck);
        } catch (Exception e) {
            log.error("Error during balance check for cardId: {}", cardId, e);
            execution.setVariable("balanceCheck",false);
        }
    }

}
