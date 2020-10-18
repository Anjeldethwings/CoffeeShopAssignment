package com.global.coffeeshop.service;

import com.global.coffeeshop.entity.CoffeeShop;

public interface CoffeeShopService extends CommonService {

    Long createCoffeeShop();

    CoffeeShop getByCoffeeShopId(Long id);

    boolean isExistByShopId(Long id);
}
