package com.global.coffeeshop.service;

import com.global.coffeeshop.entity.CoffeeOrderCoffeeType;
import com.global.coffeeshop.exception.CoffeeShopCustomException;

import java.util.List;

public interface CoffeeOrderCoffeeTypeService extends CommonService{

    List<CoffeeOrderCoffeeType> createBulk(List<CoffeeOrderCoffeeType> coffeeOrderCoffeeTypeList);

    boolean deleteBulk(List<Long> id) throws CoffeeShopCustomException;

    boolean delete(Long id) throws CoffeeShopCustomException;
}
