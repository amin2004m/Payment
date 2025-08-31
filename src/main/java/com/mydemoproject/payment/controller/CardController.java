package com.mydemoproject.payment.controller;

import com.mydemoproject.payment.dto.response.CardResponse;
import com.mydemoproject.payment.service.CardService;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;


@RestController
@RequestMapping("v1/cards")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;
    private final RuntimeService runtimeService;

    @PostMapping("create")
    public String createCard(@RequestParam Long id,
                             @RequestParam String username,
                             @RequestParam String password
    ) {
        String action = "createCard";

        runtimeService.createProcessInstanceByKey("registerProcess")
                .setVariable("action", action);
        return cardService.createCard(id, username, password);
    }

    @PostMapping("/transfer")
    public void transferCard(@RequestParam Long debitorId,
                             @RequestParam Long creditorId,
                             @RequestParam BigDecimal amount) {
        String action = "transfer";

        runtimeService.createProcessInstanceByKey("doTransfer")
                .setVariable("action", action);
        cardService.transferCard(debitorId, creditorId, amount);
    }

    @PostMapping("/deposit")
    public BigDecimal deposit(@RequestParam Long cardId,
                              @RequestParam BigDecimal amount,
                              @RequestParam BigDecimal balance,
                              @RequestParam String cardNumber) {

        String action = "deposit";
        runtimeService.createProcessInstanceByKey("doTransfer")
                .setVariable("action", action);

        return cardService.depositToCard(cardId, amount, balance, cardNumber);
    }

    @PostMapping("/withdraw")
    public CardResponse withdraw(@RequestParam Long cardId,
                                 @RequestParam BigDecimal amount,
                                 @RequestParam BigDecimal balance) {

        String action = "withdraw";
        runtimeService.createProcessInstanceByKey("doTransfer")
                .setVariable("action", action);

        return cardService.withdraw(cardId, amount, balance);
    }

    @GetMapping("/checkbalance/{cardId}")
    public BigDecimal checkBalance(@PathVariable Long cardId) {

        String action = "checkBalance";
        runtimeService.createProcessInstanceByKey("doTransfer")
                .setVariable("action", action);

        return cardService.CheckBalance(cardId);
    }
}
