package com.mbatour.mboa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.NOT_FOUND)
public class SiteNotFoundException extends RuntimeException {
    public SiteNotFoundException(String msg) {
        super(msg) ;
    }
}
