package com.global.coffeeshop.controller;

import com.global.coffeeshop.controller.dto.ErrorResponseDto;
import com.global.coffeeshop.exception.CoffeeShopCustomException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(value = CoffeeShopCustomException.class)
    public ResponseEntity handleCoffeeShopCustomException(CoffeeShopCustomException exception) {
        logger.error(exception.getMessage());
        return new ResponseEntity(new ErrorResponseDto(exception.getMessage()), exception.getHttpStatusCode());
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity handleException(Exception exception) {
        logger.error(exception.getMessage());
        return new ResponseEntity(new ErrorResponseDto("Internal Error"), HttpStatus.INTERNAL_SERVER_ERROR);
    }
}
