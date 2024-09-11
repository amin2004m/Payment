package com.mydemoproject.payment.dto.response;

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
public class CardResponse {

    Long cardId;
    String cardNumber;
    BigDecimal balance;
    User user;
}
