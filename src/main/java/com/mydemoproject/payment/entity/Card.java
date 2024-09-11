package com.mydemoproject.payment.entity;

import lombok.*;
import lombok.experimental.FieldDefaults;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.List;

@Getter
@Setter
@Entity
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "cards")
@FieldDefaults(level = AccessLevel.PRIVATE)
public class Card {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    Long cardId;

    String cardNumber;
    BigDecimal balance;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "userId")
    User user;


}
