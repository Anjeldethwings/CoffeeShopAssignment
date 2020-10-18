package com.global.coffeeshop.service.impl;

import com.global.coffeeshop.entity.User;
import com.global.coffeeshop.exception.CoffeeShopCustomException;
import com.global.coffeeshop.respository.UserRepository;
import com.global.coffeeshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Override
    public boolean isExistByUserId(Long id) {
        return userRepository.existsById(id);
    }

    @Override
    public User getUserFromContext() throws CoffeeShopCustomException {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return getUserByUserName(username);
    }

    @Override
    public User getUserByUserName(String username) throws CoffeeShopCustomException {

        if (username.isEmpty()) {
            logger.error("Invalid username");
            throw new CoffeeShopCustomException(HttpStatus.BAD_REQUEST, "Invalid username");
        }

        //TODO - isExistByUserName()

        return userRepository.getUserByName(username);
    }
}
