package com.global.coffeeshop.service.impl;

import com.global.coffeeshop.controller.dto.request.CoffeeOrderDto;
import com.global.coffeeshop.controller.dto.response.CoffeeOrderResDto;
import com.global.coffeeshop.exception.CoffeeShopCustomException;
import com.global.coffeeshop.service.CoffeeOrderService;
import org.junit.Assert;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class CoffeeOrderServiceImplTest {

    @Autowired
    private CoffeeOrderService coffeeOrderService;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;

    CoffeeOrderResDto coffeeOrderResDto = null;

    @BeforeEach
    void setUp() {
        final UserDetails userDetails = userService.loadUserByUsername("Amila");
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
        SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createCoffeeOrder() {
        List<Long> coffeeList = new ArrayList<>();
        coffeeList.add((long) 1);
        coffeeList.add((long) 2);

        CoffeeOrderDto coffeeOrderDto = new CoffeeOrderDto((long) 2, coffeeList);
        try {
            coffeeOrderResDto = coffeeOrderService.createCoffeeOrder(coffeeOrderDto);
        } catch (CoffeeShopCustomException e) {
            e.printStackTrace();
        }
        Assert.assertNotNull(coffeeOrderResDto.getOrderId());
    }

    @Test
    void getOrderByOrderId() {
        try {
            Assert.assertNotNull(coffeeOrderService.getOrderByOrderId(coffeeOrderResDto.getOrderId()));
        } catch (CoffeeShopCustomException e) {
            Assert.fail();
        }
    }

    @Test
    void isExistByOrderId() {
        Assert.assertTrue(coffeeOrderService.isExistByOrderId(coffeeOrderResDto.getOrderId()));
    }
}