package com.mydemoproject.payment.exceptions;

import lombok.AccessLevel;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.experimental.FieldDefaults;

@Getter
@RequiredArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE, makeFinal = true)

public enum ErrorEnum {

    USER_NOT_FOUND(404,"User not found!"),
    ERROR_CREATING_USER(521,"User cannot created !"),
    NOT_ENOUGH_BALANCE(404,"Not enough balance !"),
    USER_ALREADY_EXISTS(46,"User Already Exists !");

    int errorCode;
    String errorMessage;
}
