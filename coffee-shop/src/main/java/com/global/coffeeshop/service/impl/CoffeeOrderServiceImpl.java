package com.global.coffeeshop.service.impl;

import com.global.coffeeshop.controller.dto.request.CoffeeOrderDto;
import com.global.coffeeshop.controller.dto.response.CoffeeOrderCreatedDto;
import com.global.coffeeshop.controller.dto.response.OrderDto;
import com.global.coffeeshop.entity.*;
import com.global.coffeeshop.exception.CoffeeShopCustomException;
import com.global.coffeeshop.respository.CoffeeOrderRepository;
import com.global.coffeeshop.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CachePut;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class CoffeeOrderServiceImpl implements CoffeeOrderService {

    @Autowired
    private CoffeeOrderRepository coffeeOrderRepository;

    @Autowired
    private CoffeeOrderCoffeeTypeService coffeeOrderCoffeeTypeService;

    @Autowired
    private CoffeeShopService coffeeShopService;

    @Autowired
    private UserService userService;

    @Autowired
    private OrderQueueService orderQueueService;

    @Autowired
    private CoffeeTypeService coffeeTypeService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public CoffeeOrderCreatedDto createCoffeeOrder(CoffeeOrderDto coffeeOrderDto) throws CoffeeShopCustomException {

        if (coffeeOrderDto.getCoffeeTypeIdList().isEmpty()) {
            logger.error("Coffee item list is empty.");
            throw new CoffeeShopCustomException(HttpStatus.BAD_REQUEST, "Coffee item list is empty.");
        }

        if (coffeeOrderDto.getShopId() == null) {
            logger.error("Shop id is empty.");
            throw new CoffeeShopCustomException(HttpStatus.BAD_REQUEST, "Shop id is empty.");
        }

        if (!coffeeShopService.isExistByShopId(coffeeOrderDto.getShopId())) {
            logger.error("Shop id is not found.");
            throw new CoffeeShopCustomException(HttpStatus.BAD_REQUEST, "Shop id is not found.");
        }

        Long shopId = coffeeOrderDto.getShopId();
        User user = userService.getUserFromContext();

        Long queueId = orderQueueService.getSuitableQueueByShopId(shopId);
        OrderQueue orderQueue = new OrderQueue(queueId);

        CoffeeOrder coffeeOrder = new CoffeeOrder();
        coffeeOrder.setOrderQueue(orderQueue);
        coffeeOrder.setUser(user);
        CoffeeOrder coffeeOrderCreated = coffeeOrderRepository.save(coffeeOrder);

        //TODO - correction
        if (coffeeOrderCreated == null) {
            logger.error("Internal error occurred for order create");
            throw new CoffeeShopCustomException(HttpStatus.BAD_REQUEST, "Internal error occurred for order create");
        }

        List<CoffeeOrderCoffeeType> coffeeOrderCoffeeTypeList = new ArrayList<>();

        for (Long coffeeTypeId : coffeeOrderDto.getCoffeeTypeIdList()) {

            CoffeeType coffeeType = new CoffeeType(coffeeTypeId);

            CoffeeOrderCoffeeType coffeeOrderCoffeeType = new CoffeeOrderCoffeeType();
            coffeeOrderCoffeeType.setCoffeeOrder(coffeeOrderCreated);
            coffeeOrderCoffeeType.setCoffeeType(coffeeType);
            coffeeOrderCoffeeTypeList.add(coffeeOrderCoffeeType);
        }

        coffeeOrderCoffeeTypeService.createBulk(coffeeOrderCoffeeTypeList);

        CoffeeOrderCreatedDto coffeeOrderCreatedDto = new CoffeeOrderCreatedDto();

        modelMapper.map(coffeeOrderDto, coffeeOrderCreatedDto);

        coffeeOrderCreatedDto.setOrderId(coffeeOrderCreated.getId());
        coffeeOrderCreatedDto.setQueueId(queueId);

        return coffeeOrderCreatedDto;
    }

    @Override
    @Transactional
    @CachePut(cacheNames = "coffee_order", key = "#id")
    public CoffeeOrderCreatedDto updateCoffeeOrder(CoffeeOrderDto coffeeOrderDto, Long id) throws CoffeeShopCustomException {

        if (!isExistByOrderId(id)) {
            logger.error("Order id not found for update order operation");
            throw new CoffeeShopCustomException(HttpStatus.BAD_REQUEST, "Order id not found for update order operation");
        }

        Optional<CoffeeOrder> coffeeOrder = coffeeOrderRepository.findById(id);

        if (!coffeeOrder.isPresent()) {
            logger.error("Order is not found for get order operation");
            throw new CoffeeShopCustomException(HttpStatus.BAD_REQUEST, "Order is not found for update order operation");
        }

        CoffeeOrder order = coffeeOrder.get();

        List<Long> removeList = new ArrayList<>();
        List<Long> coffeeTypeIdList = new ArrayList<>();
        coffeeTypeIdList.addAll(coffeeOrderDto.getCoffeeTypeIdList());

        order.getCoffeeOrderCoffeeTypeList().parallelStream().forEach(coffee -> {
            Long coffeeType = coffee.getCoffeeType().getId();

            if (coffeeTypeIdList.contains(coffeeType)) {
                coffeeTypeIdList.remove(coffeeType);
            } else {
                removeList.add(coffee.getId());
            }

        });

        List<CoffeeOrderCoffeeType> coffeeOrderCoffeeTypeList = new ArrayList<>();
        for (Long coffeeTypeId : coffeeOrderDto.getCoffeeTypeIdList()) {

            CoffeeType coffeeType = new CoffeeType(coffeeTypeId);

            CoffeeOrderCoffeeType coffeeOrderCoffeeType = new CoffeeOrderCoffeeType();
            coffeeOrderCoffeeType.setCoffeeOrder(order);
            coffeeOrderCoffeeType.setCoffeeType(coffeeType);
            coffeeOrderCoffeeTypeList.add(coffeeOrderCoffeeType);
        }

        coffeeOrderCoffeeTypeService.createBulk(coffeeOrderCoffeeTypeList);
        coffeeOrderCoffeeTypeService.deleteBulk(removeList);

        CoffeeOrderCreatedDto coffeeOrderCreatedDto = new CoffeeOrderCreatedDto();

        modelMapper.map(coffeeOrderDto, coffeeOrderCreatedDto);

        coffeeOrderCreatedDto.setShopId(order.getOrderQueue().getCoffeeShop().getId());
        coffeeOrderCreatedDto.setOrderId(id);
        coffeeOrderCreatedDto.setQueueId(order.getOrderQueue().getId());

        return coffeeOrderCreatedDto;
    }

    @Override
    @Cacheable(cacheNames = "coffee_order", key = "#id")
    public OrderDto getOrderByOrderId(Long id) throws CoffeeShopCustomException {

        if (!isExistByOrderId(id)) {
            logger.error("Order id not found for get order operation");
            throw new CoffeeShopCustomException(HttpStatus.BAD_REQUEST, "Order id not found for get order operation");
        }

        Optional<CoffeeOrder> coffeeOrder = coffeeOrderRepository.findById(id);

        if (!coffeeOrder.isPresent()) {
            logger.error("Order is not found for get order operation");
            throw new CoffeeShopCustomException(HttpStatus.BAD_REQUEST, "Order is not found for get order operation");
        }

        OrderDto orderDto = new OrderDto();
        modelMapper.map(coffeeOrder.get(), orderDto);

        return orderDto;
    }

    @Override
    public boolean isExistByOrderId(Long id) {
        return coffeeOrderRepository.existsById(id);
    }

    @Override
    public boolean deleteOrder(Long id) throws CoffeeShopCustomException {

        if (!isExistByOrderId(id)) {
            logger.error("Order is not found for delete order operation");
            throw new CoffeeShopCustomException(HttpStatus.BAD_REQUEST, "Order is not found for delete order operation");
        }

        Optional<CoffeeOrder> coffeeOrder = coffeeOrderRepository.findById(id);

        if (!coffeeOrder.isPresent()) {
            logger.error("Order is not found for get order operation");
            throw new CoffeeShopCustomException(HttpStatus.BAD_REQUEST, "Order is not found for get order operation");
        }

        coffeeOrder.get().setDeleted(true);
        coffeeOrder.get().getCoffeeOrderCoffeeTypeList().forEach(deleteItem -> {
            deleteItem.setDeleted(true);
        });

        coffeeOrderRepository.save(coffeeOrder.get());

        return true;
    }
}
