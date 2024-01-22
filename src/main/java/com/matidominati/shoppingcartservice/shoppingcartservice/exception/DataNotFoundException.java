package com.matidominati.shoppingcartservice.shoppingcartservice.exception;

import org.springframework.http.HttpStatus;

public class DataNotFoundException extends CartException {
    public DataNotFoundException(String message) {
        super(message, HttpStatus.NOT_FOUND);
    }
}
