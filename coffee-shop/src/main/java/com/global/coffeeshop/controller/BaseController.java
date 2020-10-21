package com.global.coffeeshop.controller;

import com.global.coffeeshop.controller.dto.response.ErrorResponseDto;
import com.global.coffeeshop.exception.CoffeeShopCustomException;
import io.jsonwebtoken.JwtException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import javax.security.auth.message.AuthException;
import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @ExceptionHandler(value = CoffeeShopCustomException.class)
    public ResponseEntity handleCoffeeShopCustomException(CoffeeShopCustomException exception) {
        logger.error(exception.getMessage());
        return new ResponseEntity(new ErrorResponseDto(exception.getMessage(), "CE01"), exception.getHttpStatusCode());
    }

    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity handleValidationException(MethodArgumentNotValidException exception) {
        List<String> errors = new ArrayList<>();
        exception.getBindingResult().getAllErrors().forEach(error -> errors.add(error.getDefaultMessage()));
        logger.error(errors.toString());
        return new ResponseEntity(new ErrorResponseDto(errors.toString(), "CE02"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = AuthException.class)
    public ResponseEntity handleAuthException(AuthException exception) {
        logger.error(exception.getMessage());
        return new ResponseEntity(new ErrorResponseDto(exception.getMessage(), "CE03"), HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(value = JwtException.class)
    public ResponseEntity handleJwtException(JwtException exception) {
        logger.error(exception.getMessage());
        return new ResponseEntity(new ErrorResponseDto(exception.getMessage(), "CE04"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

    @ExceptionHandler(value = Exception.class)
    public ResponseEntity handleException(Exception exception) {
        logger.error(exception.getMessage());
        return new ResponseEntity(new ErrorResponseDto("Internal Error Occurred", "CE05"), HttpStatus.INTERNAL_SERVER_ERROR);
    }

}
