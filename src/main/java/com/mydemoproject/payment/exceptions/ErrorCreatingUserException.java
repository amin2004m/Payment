package com.mydemoproject.payment.exceptions;

public class ErrorCreatingUserException extends RuntimeException{
    public ErrorCreatingUserException(String message) {
        super(message);
    }
}
