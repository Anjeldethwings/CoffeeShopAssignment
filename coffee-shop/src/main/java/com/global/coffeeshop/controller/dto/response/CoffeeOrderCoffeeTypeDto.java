package com.global.coffeeshop.controller.dto.response;

public class CoffeeOrderCoffeeTypeDto {
    private Long id;

    private CoffeeTypeDto coffeeType;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public CoffeeTypeDto getCoffeeType() {
        return coffeeType;
    }

    public void setCoffeeType(CoffeeTypeDto coffeeType) {
        this.coffeeType = coffeeType;
    }
}
