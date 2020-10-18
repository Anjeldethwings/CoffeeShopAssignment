package com.global.coffeeshop.exception;

import org.springframework.http.HttpStatus;

public class CoffeeShopCustomException extends Exception{

    private final HttpStatus httpStatusCode;

    public CoffeeShopCustomException(HttpStatus httpStatusCode, String message) {
        super(message);
        this.httpStatusCode = httpStatusCode;
    }

    public HttpStatus getHttpStatusCode() {
        return httpStatusCode;
    }
}
