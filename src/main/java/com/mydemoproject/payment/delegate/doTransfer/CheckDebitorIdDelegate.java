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
@Component("CheckDebitorId")
@RequiredArgsConstructor
public class CheckDebitorIdDelegate implements JavaDelegate {
   private final CardRepository cardRepository;

    @Override
    public void execute(DelegateExecution execution) {
        Long debitorId = (Long) execution.getVariable("debitorId");
        log.info("Check Debitor Id Delegate was started");
        Card debitorCard = cardRepository.findById(debitorId).orElseThrow(() ->
                new UserNotFoundException("Debitor Not Found"));

    }
}
