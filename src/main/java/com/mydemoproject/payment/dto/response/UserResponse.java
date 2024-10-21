package com.mydemoproject.payment.dto.response;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.FieldDefaults;


@Data
@Builder
@FieldDefaults(level = AccessLevel.PRIVATE)
public class UserResponse {
    Long userId;
    String firstName;
    String surname;
    String username;
    String password;
    String address;
    String email;
    String docFin;
    String phoneNumber;
}
