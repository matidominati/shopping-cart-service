package com.matidominati.shoppingcartservice.shoppingcartservice.exception;

import org.springframework.http.HttpStatus;

public class CartAlreadyExistsException extends CartException{
    public CartAlreadyExistsException(String message) {
        super(message, HttpStatus.CONFLICT);
    }
}
