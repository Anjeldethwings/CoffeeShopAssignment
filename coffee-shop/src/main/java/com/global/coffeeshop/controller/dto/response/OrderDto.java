package com.global.coffeeshop.controller.dto.response;

import com.global.coffeeshop.controller.dto.request.UserDto;

import java.util.List;

public class OrderDto {

    public OrderDto() {
    }

    public OrderDto(Long id, UserDto user, List<CoffeeTypeDto> coffeeTypeList, QueueDto orderQueue) {
        this.id = id;
        this.user = user;
        this.coffeeTypeList = coffeeTypeList;
        this.orderQueue = orderQueue;
    }

    private Long id;
    private UserDto user;
    private List<CoffeeTypeDto> coffeeTypeList;
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

    public List<CoffeeTypeDto> getCoffeeTypeList() {
        return coffeeTypeList;
    }

    public void setCoffeeTypeList(List<CoffeeTypeDto> coffeeTypeList) {
        this.coffeeTypeList = coffeeTypeList;
    }
}
