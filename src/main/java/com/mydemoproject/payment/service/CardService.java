package com.mydemoproject.payment.service;


import com.mydemoproject.payment.dto.request.CardRequest;
import com.mydemoproject.payment.dto.response.CardResponse;
import com.mydemoproject.payment.entity.Card;
import com.mydemoproject.payment.entity.User;
import com.mydemoproject.payment.exceptions.CardNotFoundException;
import com.mydemoproject.payment.exceptions.NotEnoughBalanceException;
import com.mydemoproject.payment.exceptions.UserNotFoundException;
import com.mydemoproject.payment.mapper.CardMapper;
import com.mydemoproject.payment.repository.CardRepository;
import com.mydemoproject.payment.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Random;

import static com.mydemoproject.payment.exceptions.ErrorEnum.*;

@Slf4j
@Service
@RequiredArgsConstructor
public class CardService {

    private final CardRepository cardRepository;
    private final UserRepository userRepository;


    public String generateCard() {
        Random random = new Random();

        StringBuilder cardNumber = new StringBuilder(28);
        cardNumber.append("AZ");
        for (int i = 0; i < 16; i++) {
            cardNumber.append(random.nextInt(9));
        }
        return cardNumber.toString();
    }

    public String createCard(Long id, String username, String password) {
        Optional<User> user = userRepository.findByUsernameAndPassword(username, password);

        String newCard = generateCard();
        Card card = new Card();
        card.setCardNumber(newCard);
        card.setCardId(user.get().getUserId());
        card.setUser(user.get());
        card.setBalance(BigDecimal.ZERO);
        cardRepository.save(card);

        return " Your card: " + newCard;
    }

    @Transactional
    public void transferCard(
            Long debitorId
            , Long creditorId
            , BigDecimal amount) {

        Card debitorCard = cardRepository.findById(debitorId).orElseThrow(() ->
                new UserNotFoundException(USER_NOT_FOUND.getErrorMessage()));

        Card creditorCard = cardRepository.findById(creditorId).orElseThrow(() ->
                new UserNotFoundException(CREDITOR_NOT_FOUND.getErrorMessage()));

        if (debitorCard.getBalance().compareTo(amount) < 0) {
            throw new NotEnoughBalanceException(NOT_ENOUGH_BALANCE.getErrorMessage());
        }

        debitorCard.setBalance(debitorCard.getBalance().subtract(amount));
        creditorCard.setBalance(creditorCard.getBalance().add(amount));

        cardRepository.save(debitorCard);
        cardRepository.save(creditorCard);

        log.info("Transfer Successful");
    }

    public BigDecimal depositToCard(Long cardId, BigDecimal amount, BigDecimal balance, String cardNumber) {
        Card byId = cardRepository.findById(cardId).orElseThrow(() ->
                new CardNotFoundException(CARD_NOT_FOUND.getErrorMessage()));

        BigDecimal newBalance = balance.add(amount);
        byId.setCardId(cardId);
        byId.setBalance(newBalance);
        byId.setCardNumber(cardNumber);

        cardRepository.save(byId);
        log.info("Successful transaction: " + newBalance);
        return newBalance;
    }

    public CardResponse withdraw(Long cardId, BigDecimal amount, BigDecimal balance) {
        CardRequest cardRequest = new CardRequest();
        CardMapper.mapToEntity(cardRequest);

        var findCARD = cardRepository.findById(cardId).orElseThrow(() ->
                new CardNotFoundException(CARD_NOT_FOUND.getErrorMessage()));
        if (findCARD != null) {
            checkEnoughBalance(balance, amount);
            findCARD.setBalance(balance.subtract(amount));
            cardRepository.save(findCARD);
        }
        return null;
    }

    public BigDecimal CheckBalance(Long cardId) {
        var card = cardRepository.findById(cardId).orElseThrow(() ->
                new CardNotFoundException(CARD_NOT_FOUND.getErrorMessage()));
        BigDecimal balance = card.getBalance();
        return balance;
    }

    private void checkEnoughBalance(BigDecimal currentBalance, BigDecimal amount) {
        if (currentBalance.compareTo(amount) < 0) {
            log.info("Transaction Failed !");
            throw new NotEnoughBalanceException(NOT_ENOUGH_BALANCE.getErrorMessage());
        }
    }

}
