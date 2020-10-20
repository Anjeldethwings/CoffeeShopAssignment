package com.global.coffeeshop.service;

import com.global.coffeeshop.controller.dto.request.CoffeeOrderDto;
import com.global.coffeeshop.controller.dto.response.CoffeeOrderResDto;
import com.global.coffeeshop.controller.dto.response.OrderDto;
import com.global.coffeeshop.exception.CoffeeShopCustomException;

public interface CoffeeOrderService extends CommonService{

    CoffeeOrderResDto createCoffeeOrder(CoffeeOrderDto coffeeOrderDto) throws CoffeeShopCustomException;

    CoffeeOrderResDto updateCoffeeOrder(CoffeeOrderDto coffeeOrderDto, Long id) throws CoffeeShopCustomException;

    OrderDto getOrderByOrderId(Long id) throws CoffeeShopCustomException;

    boolean isExistByOrderId(Long id);

    boolean deleteOrder(Long id) throws CoffeeShopCustomException;
}
