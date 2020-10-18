package com.global.coffeeshop.service.impl;

import com.global.coffeeshop.service.CoffeeShopService;
import org.junit.Assert;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class CoffeeShopServiceImplTest {

    @Autowired
    private CoffeeShopService coffeeShopService;

    @Test
    void isExistByShopId() {
        Assert.assertTrue(coffeeShopService.isExistByShopId((long) 1));
    }
}