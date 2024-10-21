package com.mydemoproject.payment.dto.request;

import com.mydemoproject.payment.entity.User;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.math.BigDecimal;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class CardRequest {

    Long cardId;
    String cardNumber;
    BigDecimal balance;
    Long userId;
    User user;
}
