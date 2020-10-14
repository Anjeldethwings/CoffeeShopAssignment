package com.global.coffeeshop.entity;

import javax.persistence.*;

@Entity
@Table(name="order_queue")
public class OrderQueue extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private CoffeeShop coffeeShop;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
