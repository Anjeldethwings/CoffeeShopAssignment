package com.global.coffeeshop.controller.dto.request;

import java.util.List;

public class CoffeeOrderDto {

    private Long userId;
    private Long shopId;
    private List<Long> coffeeTypeIdList;

    public Long getShopId() {
        return shopId;
    }

    public void setShopId(Long shopId) {
        this.shopId = shopId;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public List<Long> getCoffeeTypeIdList() {
        return coffeeTypeIdList;
    }

    public void setCoffeeTypeIdList(List<Long> coffeeTypeIdList) {
        this.coffeeTypeIdList = coffeeTypeIdList;
    }
}
