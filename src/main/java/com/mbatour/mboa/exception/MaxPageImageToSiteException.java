package com.mbatour.mboa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
public class MaxPageImageToSiteException extends RuntimeException {
    public MaxPageImageToSiteException(String msg) {
        super(msg) ;
    }
}
