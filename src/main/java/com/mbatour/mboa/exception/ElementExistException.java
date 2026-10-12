package com.mbatour.mboa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.FOUND)
public class ElementExistException extends RuntimeException {
    public ElementExistException(String msg){
        super(msg);
    }
}
