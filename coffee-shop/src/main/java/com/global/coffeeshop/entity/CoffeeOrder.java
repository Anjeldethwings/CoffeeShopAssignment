package com.global.coffeeshop.entity;


import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "coffee_order")
@Where(clause = "is_deleted = false")
public class CoffeeOrder extends AbstractEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private OrderQueue orderQueue;

    @OneToMany(mappedBy = "coffeeOrder")
    private List<CoffeeOrderCoffeeType> coffeeOrderCoffeeTypeList;

    @ManyToOne
    private User user;

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public List<CoffeeOrderCoffeeType> getCoffeeOrderCoffeeTypeList() {
        return coffeeOrderCoffeeTypeList;
    }

    public void setCoffeeOrderCoffeeTypeList(List<CoffeeOrderCoffeeType> coffeeOrderCoffeeTypeList) {
        this.coffeeOrderCoffeeTypeList = coffeeOrderCoffeeTypeList;
    }

    public OrderQueue getOrderQueue() {
        return orderQueue;
    }

    public void setOrderQueue(OrderQueue orderQueue) {
        this.orderQueue = orderQueue;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

}
