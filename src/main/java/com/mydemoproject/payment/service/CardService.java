package com.mydemoproject.payment.service;


import com.mydemoproject.payment.dto.request.CardRequest;
import com.mydemoproject.payment.dto.response.CardResponse;
import com.mydemoproject.payment.entity.Card;
import com.mydemoproject.payment.entity.User;
import com.mydemoproject.payment.exceptions.NotEnoughBalanceException;
import com.mydemoproject.payment.exceptions.UserNotFoundException;
import com.mydemoproject.payment.mapper.CardMapper;
import com.mydemoproject.payment.repository.CardRepository;
import com.mydemoproject.payment.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Optional;
import java.util.Random;


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
                new UserNotFoundException("Debitor Not Found"));

        Card creditorCard = cardRepository.findById(creditorId).orElseThrow(() ->
                new UserNotFoundException("Creditor Not Found"));

        if (debitorCard.getBalance().compareTo(amount) < 0) {
            throw new NotEnoughBalanceException("Insufficient balance for transfer");
        }

        debitorCard.setBalance(debitorCard.getBalance().subtract(amount));
        creditorCard.setBalance(creditorCard.getBalance().add(amount));

        cardRepository.save(debitorCard);
        cardRepository.save(creditorCard);

        System.err.println("Transfer Successful");
    }

    public BigDecimal depositToCard(Long cardId, BigDecimal amount, BigDecimal balance, String cardNumber) {
        Card byId = cardRepository.findById(cardId).orElseThrow(() ->
                new UserNotFoundException("Card not found"));

        BigDecimal newBalance = balance.add(amount);
        byId.setCardId(cardId);
        byId.setBalance(newBalance);
        byId.setCardNumber(cardNumber);

        cardRepository.save(byId);
        System.err.println("Successful");
        return newBalance;
    }

    public CardResponse withdraw(Long cardId, BigDecimal amount, BigDecimal balance) {
        CardRequest cardRequest = new CardRequest();
        CardMapper.mapToEntity(cardRequest);

        var findCARD =cardRepository.findById(cardId).orElseThrow(()->
                new UserNotFoundException("Card not found." +
                        "LINE 100"));
        if (findCARD!=null){
            checkEnoughBalance(balance,amount);
            findCARD.setBalance(balance.subtract(amount));
            cardRepository.save(findCARD);
        }
        return null;
    }

    public BigDecimal CheckBalance(Long cardId) {
        var card = cardRepository.findById(cardId).orElseThrow(() ->
                new UserNotFoundException("Card Not found"));
        BigDecimal balance = card.getBalance();
        return balance;
    }


    // PRIVATE METHODS

    private BigDecimal totalBalance(BigDecimal amount, BigDecimal balance) {
        BigDecimal total = balance.add(amount);
        return total;
    }

    private void checkEnoughBalance(BigDecimal currentBalance, BigDecimal amount) {
        if (currentBalance.compareTo(amount) < 0) {
            System.out.println("Transaction Failed !");
            throw new NotEnoughBalanceException("Balance is not enough");
        }
    }

//    private Card doTransaction(Long accountId, BigDecimal amount, BigDecimal balance, String cardNumber) {
//
//        BigDecimal newAmount = balance.add(amount);
//        User user = new User();
//
//        Card withdraw = new Card();
//        withdraw.setCardId(accountId);
//        withdraw.setBalance(newAmount);
//        withdraw.setUser(user);
//        withdraw.setCardNumber(cardNumber);
//        return cardRepository.save(withdraw);
//    }
}
