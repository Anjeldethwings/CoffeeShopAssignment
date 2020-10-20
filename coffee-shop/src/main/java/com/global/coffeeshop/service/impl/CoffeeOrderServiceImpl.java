package com.global.coffeeshop.service.impl;

import com.global.coffeeshop.controller.dto.request.CoffeeOrderDto;
import com.global.coffeeshop.controller.dto.response.CoffeeOrderResDto;
import com.global.coffeeshop.controller.dto.response.OrderDto;
import com.global.coffeeshop.entity.CoffeeOrder;
import com.global.coffeeshop.entity.CoffeeType;
import com.global.coffeeshop.entity.OrderQueue;
import com.global.coffeeshop.entity.User;
import com.global.coffeeshop.exception.CoffeeShopCustomException;
import com.global.coffeeshop.respository.CoffeeOrderRepository;
import com.global.coffeeshop.service.*;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
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
    private CoffeeShopService coffeeShopService;

    @Autowired
    private CoffeeUserService coffeeUserService;

    @Autowired
    private OrderQueueService orderQueueService;

    @Autowired
    private CoffeeTypeService coffeeTypeService;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    @Transactional
    public CoffeeOrderResDto createCoffeeOrder(CoffeeOrderDto coffeeOrderDto) throws CoffeeShopCustomException {

        if (!coffeeShopService.isExistByShopId(coffeeOrderDto.getShopId())) {
            logger.error("Shop id is not found.");
            throw new CoffeeShopCustomException(HttpStatus.BAD_REQUEST, "Shop id is not found.");
        }

        Long shopId = coffeeOrderDto.getShopId();
        User user = coffeeUserService.getUserFromContext();

        Long queueId = orderQueueService.getSuitableQueueByShopId(shopId).getQueueNo();
        OrderQueue orderQueue = new OrderQueue(queueId);

        CoffeeOrder coffeeOrder = new CoffeeOrder();
        coffeeOrder.setOrderQueue(orderQueue);
        coffeeOrder.setUser(user);

        List<CoffeeType> coffeeTypeList = new ArrayList<>();
        coffeeOrderDto.getCoffeeTypeIdList().forEach(coffeeItem -> {
            CoffeeType coffeeType = new CoffeeType(coffeeItem);
            coffeeTypeList.add(coffeeType);
        });

        coffeeOrder.setCoffeeTypeList(coffeeTypeList);
        CoffeeOrder coffeeOrderCreated = coffeeOrderRepository.save(coffeeOrder);

        CoffeeOrderResDto coffeeOrderResDto = new CoffeeOrderResDto();
        modelMapper.map(coffeeOrderDto, coffeeOrderResDto);
        coffeeOrderResDto.setOrderId(coffeeOrderCreated.getId());
        coffeeOrderResDto.setQueueId(queueId);

        return coffeeOrderResDto;
    }

    @Override
    @Transactional
    @CacheEvict(cacheNames = "coffee_order_cache", key = "#id")
    public CoffeeOrderResDto updateCoffeeOrder(CoffeeOrderDto coffeeOrderDto, Long id) throws CoffeeShopCustomException {

        Optional<CoffeeOrder> coffeeOrder = Optional.ofNullable(coffeeOrderRepository.findById(id)
                .orElseThrow(() -> new CoffeeShopCustomException(HttpStatus.BAD_REQUEST, "Order id not found for, order id : " + id)));

        CoffeeOrder order = coffeeOrder.get();

        //validate the user
        coffeeUserService.validateUser(order.getUser().getId());

        List<CoffeeType> coffeeTypeList = new ArrayList<>();
        coffeeOrderDto.getCoffeeTypeIdList().forEach(coffeeItem -> {
            CoffeeType coffeeType = new CoffeeType(coffeeItem);
            coffeeTypeList.add(coffeeType);
        });

        order.setCoffeeTypeList(coffeeTypeList);
        coffeeOrderRepository.save(order);

        CoffeeOrderResDto coffeeOrderResDto = new CoffeeOrderResDto();
        modelMapper.map(coffeeOrderDto, coffeeOrderResDto);
        coffeeOrderResDto.setShopId(order.getOrderQueue().getCoffeeShop().getId());
        coffeeOrderResDto.setOrderId(id);
        coffeeOrderResDto.setQueueId(order.getOrderQueue().getId());
        return coffeeOrderResDto;
    }

    @Override
    @Cacheable(cacheNames = "coffee_order_cache", key = "#id")
    public OrderDto getOrderByOrderId(Long id) throws CoffeeShopCustomException {

        Optional<CoffeeOrder> coffeeOrder = Optional.ofNullable(coffeeOrderRepository.findById(id)
                .orElseThrow(() -> new CoffeeShopCustomException(HttpStatus.BAD_REQUEST, "Order id not found for, order id : " + id)));

        coffeeUserService.validateUser(coffeeOrder.get().getUser().getId());

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

        Optional<CoffeeOrder> coffeeOrder = Optional.ofNullable(coffeeOrderRepository.findById(id)
                .orElseThrow(() -> new CoffeeShopCustomException(HttpStatus.BAD_REQUEST, "Order id not found for, order id : " + id)));

        coffeeUserService.validateUser(coffeeOrder.get().getUser().getId());

        coffeeOrder.get().setDeleted(true);
        coffeeOrderRepository.save(coffeeOrder.get());

        return true;
    }
}
