package com.mydemoproject.payment.controller;

import com.mydemoproject.payment.dto.response.CardResponse;
import com.mydemoproject.payment.service.CardService;
import lombok.RequiredArgsConstructor;
import org.camunda.bpm.engine.RuntimeService;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.HashMap;
import java.util.Map;

@RestController
@RequestMapping("v1/cards")
@RequiredArgsConstructor
public class CardController {

    private final CardService cardService;
    private final RuntimeService runtimeService;

    @PostMapping("create")
    public String createCard(@RequestParam Long id,
                             @RequestParam String username,
                             @RequestParam String password) {

        return cardService.createCard(id, username, password);
    }

    @PostMapping("/transfer")
    public void transferCard(@RequestParam Long debitorId,
                             @RequestParam Long creditorId,
                             @RequestParam BigDecimal amount) {

        Map<String, Object> variables = new HashMap<>();
        variables.put("debitorId", debitorId);
        variables.put("creditorId",creditorId);
        variables.put("amount",amount);
        variables.put("action", "transfer");
        runtimeService.startProcessInstanceByKey("Process_0c4b5k4", variables);

        cardService.transferCard(debitorId, creditorId, amount);
    }

    @PostMapping("/deposit")
    public BigDecimal deposit(@RequestParam Long cardId,
                              @RequestParam BigDecimal amount,
                              @RequestParam BigDecimal balance) {
        Map<String, Object> variables = new HashMap<>();
        variables.put("cardId", cardId);
        variables.put("amount", amount);
        variables.put("balance",balance);
        variables.put("action", "deposit");

        runtimeService.startProcessInstanceByKey("Process_0c4b5k4", variables);

        return cardService.depositToCard(cardId, amount, balance);
    }

    @PostMapping("/withdraw")
    public CardResponse withdraw(@RequestParam Long cardId,
                                 @RequestParam BigDecimal amount,
                                 @RequestParam BigDecimal balance) {
        Map<String, Object> variables = new HashMap<>();
        variables.put("cardId", cardId);
        variables.put("amount", amount);
        variables.put("balance",balance);
        variables.put("action", "withdraw");

        runtimeService.startProcessInstanceByKey("Process_0c4b5k4", variables);

        return cardService.withdraw(cardId, amount, balance);
    }

    @PostMapping("/checkbalance/{cardId}")
    public BigDecimal checkBalance(@PathVariable Long cardId){
        Map<String, Object> variables = new HashMap<>();
        variables.put("cardId", cardId);
        variables.put("action","checkBalance");
        runtimeService.startProcessInstanceByKey("Process_0c4b5k4", variables);


        return cardService.CheckBalance(cardId);
    }
}
