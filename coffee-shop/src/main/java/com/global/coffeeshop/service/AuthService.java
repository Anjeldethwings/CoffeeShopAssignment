package com.global.coffeeshop.service;

import com.global.coffeeshop.controller.dto.request.LoginDto;
import com.global.coffeeshop.controller.dto.response.AuthenticationResponseDto;
import com.global.coffeeshop.exception.CoffeeShopCustomException;

public interface AuthService extends CommonService {
    AuthenticationResponseDto authenticationCheck(LoginDto loginDto) throws CoffeeShopCustomException;
}
