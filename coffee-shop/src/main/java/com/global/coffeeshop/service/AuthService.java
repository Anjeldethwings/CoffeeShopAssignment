package com.global.coffeeshop.service;

import com.global.coffeeshop.controller.dto.request.LoginDto;
import com.global.coffeeshop.controller.dto.response.AuthenticationResDto;
import com.global.coffeeshop.exception.CoffeeShopCustomException;

import javax.security.auth.message.AuthException;

public interface AuthService extends CommonService {
    AuthenticationResDto authenticationCheck(LoginDto loginDto) throws CoffeeShopCustomException, AuthException;
}
