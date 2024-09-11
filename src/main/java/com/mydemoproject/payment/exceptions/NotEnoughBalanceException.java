package com.mydemoproject.payment.exceptions;

public class NotEnoughBalanceException extends RuntimeException{

    public NotEnoughBalanceException(String message) {
        super(message);
    }
}
