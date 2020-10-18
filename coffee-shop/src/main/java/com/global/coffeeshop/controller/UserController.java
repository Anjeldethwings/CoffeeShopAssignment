package com.global.coffeeshop.controller;

import com.global.coffeeshop.controller.dto.ErrorResponseDto;
import com.global.coffeeshop.controller.dto.response.UserDto;
import com.global.coffeeshop.exception.CoffeeShopCustomException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@RequestMapping("users")
public class UserController {

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<UserDto> createUser(@RequestBody UserDto userDto, HttpServletRequest request) throws CoffeeShopCustomException {
        return new ResponseEntity(new ErrorResponseDto("Not implemented yet"), HttpStatus.BAD_REQUEST);
    }


}
