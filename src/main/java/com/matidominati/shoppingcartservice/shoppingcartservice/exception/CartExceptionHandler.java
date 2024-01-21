package com.matidominati.shoppingcartservice.shoppingcartservice.exception;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import java.time.ZonedDateTime;

@ControllerAdvice
public class CartExceptionHandler {
    @ExceptionHandler(DataNotFoundException.class)
    public ResponseEntity<Object> handleDataNotFoundException(DataNotFoundException ex) {
        return ResponseEntity.status(ex.getHttpStatus()).body(ErrorResponse.builder()
                .message(ex.getMessage())
                .httpStatus(ex.getHttpStatus())
                .timestamp(ZonedDateTime.now())
                .build());
    }

    @ExceptionHandler(CartAlreadyExistsException.class)
    public ResponseEntity<Object> handleCartAlreadyExistsException(CartAlreadyExistsException ex) {
        return ResponseEntity.status(ex.getHttpStatus()).body(ErrorResponse.builder()
                .message(ex.getMessage())
                .httpStatus(ex.getHttpStatus())
                .timestamp(ZonedDateTime.now())
                .build());
    }
}
