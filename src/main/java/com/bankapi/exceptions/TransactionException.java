package com.bankapi.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class TransactionException {
    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity handleNotReadableException() {
        return new ResponseEntity("It is not possible to create this transaction", HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity handleIllegalArgumentException() {
        return new ResponseEntity("Your request incorrect", HttpStatus.BAD_REQUEST);
    }
}
