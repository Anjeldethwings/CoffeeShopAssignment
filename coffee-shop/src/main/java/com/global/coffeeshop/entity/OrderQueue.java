package com.global.coffeeshop.entity;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name="order_queue")
@Where(clause = "is_deleted = false")
public class OrderQueue extends AbstractEntity implements Serializable {

    public OrderQueue(){

    }

    public OrderQueue(Long queueId){
        this.id = queueId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    private CoffeeShop coffeeShop;

    @OneToMany(mappedBy = "orderQueue", fetch = FetchType.EAGER)
    private List<CoffeeOrder> coffeeOrderList;

    public CoffeeShop getCoffeeShop() {
        return coffeeShop;
    }

    public void setCoffeeShop(CoffeeShop coffeeShop) {
        this.coffeeShop = coffeeShop;
    }

    public List<CoffeeOrder> getCoffeeOrderList() {
        return coffeeOrderList;
    }

    public void setCoffeeOrderList(List<CoffeeOrder> coffeeOrderList) {
        this.coffeeOrderList = coffeeOrderList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }
}
