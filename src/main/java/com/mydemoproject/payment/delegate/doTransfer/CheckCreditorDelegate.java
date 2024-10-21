package com.mydemoproject.payment.delegate.doTransfer;

import com.mydemoproject.payment.entity.Card;
import com.mydemoproject.payment.exceptions.UserNotFoundException;
import com.mydemoproject.payment.repository.CardRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.camunda.bpm.engine.delegate.DelegateExecution;
import org.camunda.bpm.engine.delegate.JavaDelegate;
import org.springframework.stereotype.Component;

@Slf4j
@Component("CheckCreditorId")
@RequiredArgsConstructor
public class CheckCreditorDelegate implements JavaDelegate {

    private final CardRepository cardRepository;

    @Override
    public void execute(DelegateExecution execution) throws Exception {
        Long creditorId = (Long) execution.getVariable("creditorId");

        log.info("Check Creditor ID Delegate was started");
        Card creditorCard = cardRepository.findById(creditorId).orElseThrow(() ->
                new UserNotFoundException("Creditor Not Found"));
    }
}
