package com.global.coffeeshop.controller.dto.request;

import io.swagger.annotations.ApiModel;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import java.util.List;

@ApiModel
public class CoffeeOrderDto {

    @NotNull(message = "shop id shouldn't be null value")
    private Long shopId;

    @NotEmpty(message = "shop id shouldn't be empty value")
    @NotNull(message = "coffee item list shouldn't be null value")
    private List<Long> coffeeTypeIdList;

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
}
