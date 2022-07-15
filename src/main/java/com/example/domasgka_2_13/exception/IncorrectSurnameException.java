package com.example.domasgka_2_13.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class IncorrectSurnameException extends RuntimeException{

    public IncorrectSurnameException(String message) {
        super(message);
    }

    public IncorrectSurnameException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectSurnameException(Throwable cause) {
        super(cause);
    }

    public IncorrectSurnameException(String message,
                                  Throwable cause,
                                  boolean enableSuppression,
                                  boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public IncorrectSurnameException(){

    }
}
