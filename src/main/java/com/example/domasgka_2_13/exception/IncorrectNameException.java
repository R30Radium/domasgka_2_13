package com.example.domasgka_2_13.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST)
public class IncorrectNameException extends RuntimeException{

    public IncorrectNameException(String message) {
        super(message);
    }

    public IncorrectNameException(String message, Throwable cause) {
        super(message, cause);
    }

    public IncorrectNameException(Throwable cause) {
        super(cause);
    }

    public IncorrectNameException(String message,
                                          Throwable cause,
                                          boolean enableSuppression,
                                          boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

    public IncorrectNameException(){

    }
}
