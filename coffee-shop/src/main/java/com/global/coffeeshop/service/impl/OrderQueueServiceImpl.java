package com.global.coffeeshop.service.impl;

import com.global.coffeeshop.entity.OrderQueue;
import com.global.coffeeshop.exception.CoffeeShopCustomException;
import com.global.coffeeshop.respository.OrderQueueRepository;
import com.global.coffeeshop.service.OrderQueueService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OrderQueueServiceImpl implements OrderQueueService {

    @Autowired
    private OrderQueueRepository orderQueueRepository;

    static Integer totalSize = 0;

    @Override
    public Long getSuitableQueueByShopId(Long id) throws CoffeeShopCustomException {

        List<OrderQueue> orderQueueList = orderQueueRepository.getOrderQueueByCoffeeShopId(id);

        if (orderQueueList.isEmpty()) {
            logger.info("Empty queue for the selected shop id");
            throw new CoffeeShopCustomException(HttpStatus.BAD_REQUEST, "Empty queue for the selected shop id");
        }

        HashMap<Long, Integer> queueDetals = new HashMap<>();
        orderQueueList.forEach(queue -> {
            totalSize = 0;
            queue.getCoffeeOrderList().parallelStream().forEach(order -> {
                Integer size = order.getCoffeeOrderCoffeeTypeList().size();
                totalSize = totalSize + size;
            });
            queueDetals.put(queue.getId(), totalSize);
        });

        Long key = Collections.min(queueDetals.entrySet(), Map.Entry.comparingByValue()).getKey();

        return key;
    }

    @Override
    public List<OrderQueue> getQueueByShopId(Long id) {
        List<OrderQueue> orderQueueList = orderQueueRepository.getOrderQueueByCoffeeShopId(id);
        return orderQueueList;
    }
}
