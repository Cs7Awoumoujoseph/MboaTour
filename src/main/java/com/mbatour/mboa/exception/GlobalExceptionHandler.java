package com.mbatour.mboa.exception;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@ControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(SiteNotFoundException.class)
    public ResponseEntity<ErrorDetails> handleSiteNotFound(SiteNotFoundException exception,
                                      WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false),
                "SITE_NOT_FOUND"
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(MaxPageImageToSiteException.class)
    public ResponseEntity<ErrorDetails> handleMaxImageSite(MaxPageImageToSiteException exception,
                                                           WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false),
                "MAX_IMAGE_SITE"
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(ElementExistException.class)
    public  ResponseEntity<ErrorDetails> handleElementExist(ElementExistException exception,
            WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false),
                "ELEMENT_EXIST"
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.FOUND);
    }

    @ExceptionHandler(IllegalArgException.class)
    public  ResponseEntity<ErrorDetails> handleIllegalArgumentException(IllegalArgumentException exception,
                                                            WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false),
                "MAX_SIZE_OR_INVALID_IMAGE"
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.FOUND);
    }

    @ExceptionHandler(EmailExistException.class)
    public  ResponseEntity<ErrorDetails> handleEmailExistException(EmailExistException exception,
                                                                        WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false),
                "EMAIL_EXIST"
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.FOUND);
    }

    @ExceptionHandler(InvalidCredentialsException.class)
    public  ResponseEntity<ErrorDetails> handleInvalidCredentialsException(InvalidCredentialsException exception,
                                                                   WebRequest webRequest) {
        ErrorDetails errorDetails = new ErrorDetails(
                LocalDateTime.now(),
                exception.getMessage(),
                webRequest.getDescription(false),
                "CREDENCIAL_EXCEPTION"
        );
        return new ResponseEntity<>(errorDetails, HttpStatus.FOUND);
    }
}
