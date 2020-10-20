package com.global.coffeeshop.service;

import com.global.coffeeshop.controller.dto.response.QueueDetailsDto;
import com.global.coffeeshop.entity.OrderQueue;
import com.global.coffeeshop.exception.CoffeeShopCustomException;

import java.util.List;

public interface OrderQueueService extends CommonService {

    QueueDetailsDto getSuitableQueueByShopId(Long id) throws CoffeeShopCustomException;

    List<OrderQueue> getQueueByShopId(Long id);

}
