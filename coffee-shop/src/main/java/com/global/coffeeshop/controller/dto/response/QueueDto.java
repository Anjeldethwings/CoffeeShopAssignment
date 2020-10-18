package com.global.coffeeshop.controller.dto.response;

public class QueueDto {
    private Long id;
    private CoffeeShopDto coffeeShop;

    public CoffeeShopDto getCoffeeShop() {
        return coffeeShop;
    }

    public void setCoffeeShop(CoffeeShopDto coffeeShop) {
        this.coffeeShop = coffeeShop;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
