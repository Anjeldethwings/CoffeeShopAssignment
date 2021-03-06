package com.global.coffeeshop.controller.dto.response;

import java.util.List;

public class CoffeeOrderResDto {

    public CoffeeOrderResDto() {
    }

    public CoffeeOrderResDto(Long shopId, List<Long> coffeeTypeIdList, Long queueId, Long orderId) {
        this.shopId = shopId;
        this.coffeeTypeIdList = coffeeTypeIdList;
        this.queueId = queueId;
        this.orderId = orderId;
    }

    private Long shopId;
    private List<Long> coffeeTypeIdList;
    private Long queueId;
    private Long orderId;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public List<Long> getCoffeeTypeIdList() {
        return coffeeTypeIdList;
    }

    public void setCoffeeTypeIdList(List<Long> coffeeTypeIdList) {
        this.coffeeTypeIdList = coffeeTypeIdList;
    }

    public Long getQueueId() {
        return queueId;
    }

    public void setQueueId(Long queueId) {
        this.queueId = queueId;
    }

    public Long getOrderId() {
        return orderId;
    }

    public void setOrderId(Long orderId) {
        this.orderId = orderId;
    }
}
