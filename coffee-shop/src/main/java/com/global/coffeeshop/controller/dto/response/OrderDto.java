package com.global.coffeeshop.controller.dto.response;

import java.util.List;

public class OrderDto {
    private Long id;
    private UserDto user;
    private List<CoffeeOrderCoffeeTypeDto> coffeeOrderCoffeeTypeList;
    private QueueDto orderQueue;

    public QueueDto getOrderQueue() {
        return orderQueue;
    }

    public void setOrderQueue(QueueDto orderQueue) {
        this.orderQueue = orderQueue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public UserDto getUser() {
        return user;
    }

    public void setUser(UserDto user) {
        this.user = user;
    }

    public List<CoffeeOrderCoffeeTypeDto> getCoffeeOrderCoffeeTypeList() {
        return coffeeOrderCoffeeTypeList;
    }

    public void setCoffeeOrderCoffeeTypeList(List<CoffeeOrderCoffeeTypeDto> coffeeOrderCoffeeTypeList) {
        this.coffeeOrderCoffeeTypeList = coffeeOrderCoffeeTypeList;
    }
}
