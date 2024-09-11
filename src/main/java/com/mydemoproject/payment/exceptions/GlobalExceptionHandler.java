package com.mydemoproject.payment.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(UserNotFoundException.class)
    @ResponseStatus(HttpStatus.NOT_FOUND)
    @ResponseBody
    public ErrorResponse handleUserNotFoundException(UserNotFoundException ex) {
        return createErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage());
    }

    @ExceptionHandler(NotEnoughBalanceException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public ErrorResponse handleNotEnoughBalanceException(NotEnoughBalanceException ex) {
        return createErrorResponse(HttpStatus.BAD_REQUEST, "Not enough balance");
    }

    @ExceptionHandler(ErrorCreatingUserException.class)
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    @ResponseBody
    public ErrorResponse handleErrorCreatingUserException(ErrorCreatingUserException ex) {
        return createErrorResponse(HttpStatus.INTERNAL_SERVER_ERROR, "User cannot created");
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    @ResponseBody
    public Map<String, String> handleValidatorException(MethodArgumentNotValidException ex) {
        Map<String, String> errors = new HashMap<>();

        ex.getBindingResult().getAllErrors().forEach(error -> {
            String fieldName = ((FieldError) error).getField();
            String errorMessage = error.getDefaultMessage();
            errors.put(fieldName, errorMessage);
        });
        return errors;
    }

    private ErrorResponse createErrorResponse(HttpStatus status, String message) {
        return new ErrorResponse(
                UUID.randomUUID(),
                status.value(),
                message,
                null
        );
    }
}