package com.global.coffeeshop.service.impl;

import com.global.coffeeshop.controller.dto.response.QueueDetailsDto;
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

    static Long totalSize = Long.valueOf(0);

    @Override
    public QueueDetailsDto getSuitableQueueByShopId(Long id) throws CoffeeShopCustomException {

        List<OrderQueue> orderQueueList = orderQueueRepository.getOrderQueueByCoffeeShopId(id);

        if (orderQueueList.isEmpty()) {
            logger.info("Empty queue for the selected shop id");
            throw new CoffeeShopCustomException(HttpStatus.BAD_REQUEST, "Empty queue for the selected shop id");
        }

        HashMap<Long, Long> queueDetals = new HashMap<>();
        orderQueueList.forEach(queue -> {
            totalSize = Long.valueOf(0);
            queue.getCoffeeOrderList().parallelStream().forEach(order -> {
                Long size = Long.valueOf(order.getCoffeeTypeList().size());
                totalSize = totalSize + size;
            });
            queueDetals.put(queue.getId(), totalSize);
        });

        Long key = Collections.min(queueDetals.entrySet(), Map.Entry.comparingByValue()).getKey();

        return new QueueDetailsDto(key, queueDetals.get(key));
    }

    @Override
    public List<OrderQueue> getQueueByShopId(Long id) {
        List<OrderQueue> orderQueueList = orderQueueRepository.getOrderQueueByCoffeeShopId(id);
        return orderQueueList;
    }
}
