package com.global.coffeeshop.service.impl;

import com.global.coffeeshop.entity.CoffeeShop;
import com.global.coffeeshop.respository.CoffeeShopRespository;
import com.global.coffeeshop.service.CoffeeShopService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CoffeeShopServiceImpl implements CoffeeShopService {

    @Autowired
    private CoffeeShopRespository coffeeShopRespository;


    @Override
    public Long createCoffeeShop() {
        //TODO - create coffee shops
        return null;
    }

    @Override
    public CoffeeShop getByCoffeeShopId(Long id) {
        //TODO - search by id
        return null;
    }

    @Override
    public boolean isExistByShopId(Long id) {
        return coffeeShopRespository.existsById(id);
    }


}
