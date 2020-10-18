package com.global.coffeeshop.service;

import com.global.coffeeshop.controller.dto.request.CoffeeOrderDto;
import com.global.coffeeshop.controller.dto.response.CoffeeOrderCreatedDto;
import com.global.coffeeshop.controller.dto.response.OrderDto;
import com.global.coffeeshop.exception.CoffeeShopCustomException;

public interface CoffeeOrderService extends CommonService{

    CoffeeOrderCreatedDto createCoffeeOrder(CoffeeOrderDto coffeeOrderDto) throws CoffeeShopCustomException;

    CoffeeOrderCreatedDto updateCoffeeOrder(CoffeeOrderDto coffeeOrderDto, Long id) throws CoffeeShopCustomException;

    OrderDto getOrderByOrderId(Long id) throws CoffeeShopCustomException;

    boolean isExistByOrderId(Long id);

    boolean deleteOrder(Long id) throws CoffeeShopCustomException;
}
