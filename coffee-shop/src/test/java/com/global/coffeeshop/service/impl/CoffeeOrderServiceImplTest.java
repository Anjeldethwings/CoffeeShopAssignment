package com.global.coffeeshop.service.impl;

import com.global.coffeeshop.controller.dto.response.CoffeeOrderResDto;
import com.global.coffeeshop.entity.User;
import com.global.coffeeshop.respository.CoffeeTypeRepository;
import com.global.coffeeshop.respository.RoleRepository;
import com.global.coffeeshop.respository.UserRepository;
import com.global.coffeeshop.service.CoffeeOrderService;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class CoffeeOrderServiceImplTest {

    @Autowired
    private CoffeeOrderService coffeeOrderService;

    @Autowired
    private RoleRepository roleRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CoffeeTypeRepository coffeeTypeRepository;

    private User user;

    CoffeeOrderResDto coffeeOrderResDto = null;

    @BeforeEach
    void setUp() {
    }

    @AfterEach
    void tearDown() {
    }

    @Test
    void createCoffeeOrder() {
        //TODO - set user cotext in the test
//        List<Long> coffeeList = new ArrayList<>();
//        coffeeList.add((long) 1);
//        coffeeList.add((long) 2);
//
//        CoffeeOrderDto coffeeOrderDto = new CoffeeOrderDto((long) 2, coffeeList);
//        try {
//            coffeeOrderCreatedDto = coffeeOrderService.createCoffeeOrder(coffeeOrderDto);
//        } catch (CoffeeShopCustomException e) {
//            e.printStackTrace();
//        }
//        Assert.assertNotNull(coffeeOrderCreatedDto.getOrderId());
    }

    @Test
    void getOrderByOrderId() {
    }

    @Test
    void isExistByOrderId() {
    }

}