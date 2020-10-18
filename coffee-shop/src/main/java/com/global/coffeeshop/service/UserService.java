package com.global.coffeeshop.service;


import com.global.coffeeshop.entity.User;
import com.global.coffeeshop.exception.CoffeeShopCustomException;

public interface UserService  extends CommonService{

    boolean isExistByUserId(Long id);

    User getUserFromContext() throws CoffeeShopCustomException;

    User getUserByUserName(String username) throws CoffeeShopCustomException;
}
