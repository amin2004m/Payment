package com.mydemoproject.payment.exceptions;

import lombok.NoArgsConstructor;

@NoArgsConstructor
public class ServiceException extends RuntimeException{

    public ServiceException(int errorCode, String errorMessage) {
        super(errorMessage);
    }
    public static ServiceException of(int code, String errorMessage) {
        return new ServiceException(code, errorMessage);
    }

    public static ServiceException of(ErrorEnum errorEnum) {
        return new ServiceException(
                errorEnum.getErrorCode(),
                errorEnum.getErrorMessage());
    }
}
