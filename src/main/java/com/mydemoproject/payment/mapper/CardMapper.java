package com.mydemoproject.payment.mapper;


import com.mydemoproject.payment.dto.request.CardRequest;
import com.mydemoproject.payment.dto.response.CardResponse;
import com.mydemoproject.payment.entity.Card;
import com.mydemoproject.payment.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class CardMapper {

    public static Card mapToEntity(CardRequest cardRequest){

        User user = new User();
        user.setUserId(cardRequest.getUserId());

        return Card.builder()
                .cardId(cardRequest.getCardId())
                .cardNumber(cardRequest.getCardNumber())
                .balance(cardRequest.getBalance())
                .user(user)
                .build();
    }

    public static CardResponse mapToResponse(Card card){

        return CardResponse.builder()
                .cardId(card.getCardId())
                .cardNumber(card.getCardNumber())
                .balance(card.getBalance())
                .user(card.getUser())
                .build();
    }
}
