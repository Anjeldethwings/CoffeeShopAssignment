package com.global.coffeeshop.service;


import com.global.coffeeshop.controller.dto.request.UserDto;
import com.global.coffeeshop.entity.User;
import com.global.coffeeshop.exception.CoffeeShopCustomException;

public interface CoffeeUserService extends CommonService{

    boolean isExistByUserId(Long id);

    UserDto createUser(UserDto user);

    User getUserFromContext() throws CoffeeShopCustomException;

    User getUserByUserName(String username) throws CoffeeShopCustomException;

    boolean validateUser(Long userId) throws CoffeeShopCustomException;
}
