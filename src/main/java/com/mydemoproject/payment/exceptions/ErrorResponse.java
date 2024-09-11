package com.mydemoproject.payment.exceptions;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.FieldDefaults;

import java.util.List;
import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
public class ErrorResponse {

    UUID uuid;
    int errorCode;
    String errorMessage;
    List<FieldErrors> fieldErrors;

}
