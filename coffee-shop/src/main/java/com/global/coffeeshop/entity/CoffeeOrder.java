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

    @ManyToMany
    @JoinTable(
            name = "order_coffee_types",
            joinColumns = {@JoinColumn(referencedColumnName = "id", name = "order_id")},
            inverseJoinColumns = {@JoinColumn(referencedColumnName = "id", name = "coffee_type_id")}
    )
    private List<CoffeeType> coffeeTypeList;

    @ManyToOne
    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public OrderQueue getOrderQueue() {
        return orderQueue;
    }

    public void setOrderQueue(OrderQueue orderQueue) {
        this.orderQueue = orderQueue;
    }

    public List<CoffeeType> getCoffeeTypeList() {
        return coffeeTypeList;
    }

    public void setCoffeeTypeList(List<CoffeeType> coffeeTypeList) {
        this.coffeeTypeList = coffeeTypeList;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
}
