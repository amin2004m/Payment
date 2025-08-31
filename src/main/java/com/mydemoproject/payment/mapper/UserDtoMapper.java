package com.mydemoproject.payment.mapper;

import com.mydemoproject.payment.dto.request.UserRequest;
import com.mydemoproject.payment.dto.response.UserResponse;
import com.mydemoproject.payment.entity.Card;
import com.mydemoproject.payment.entity.User;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.stream.Collectors;

@Component
@RequiredArgsConstructor
public class UserDtoMapper {

    public static User mapToUserEntity(UserRequest userRequest) {

        return User.builder()
                .userId(userRequest.getUserId())
                .firstName(userRequest.getFirstName())
                .surname(userRequest.getSurname())
                .username(userRequest.getUsername())
                .password(userRequest.getPassword())
                .email(userRequest.getEmail())
                .phoneNumber(userRequest.getPhoneNumber())
                .docFin(userRequest.getDocFin())
                .address(userRequest.getAddress())
                .cards(userRequest.getCards() != null ?
                        userRequest.getCards().stream()
                                .map(card -> Card.builder()
                                        .cardId(card.getCardId())
                                        .cardNumber(card.getCardNumber())
                                        .balance(card.getBalance())
                                        .user(card.getUser())
                                        .build())
                                .collect(Collectors.toList()) : Collections.emptyList())
                .build();

    }

    public static UserResponse mapToUserResponse(User user) {
        return UserResponse.builder()
                .userId(user.getUserId())
                .firstName(user.getFirstName())
                .surname(user.getSurname())
                .username(user.getUsername())
                .password(user.getPassword())
                .email(user.getEmail())
                .phoneNumber(user.getPhoneNumber())
                .docFin(user.getDocFin())
                .address(user.getAddress())
                .build();
    }

}

