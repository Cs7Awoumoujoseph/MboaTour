package com.mbatour.mboa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.CONTENT_TOO_LARGE)
public class IllegalArgException extends RuntimeException{
    public IllegalArgException(String message) {
        super(message);
    }
}
