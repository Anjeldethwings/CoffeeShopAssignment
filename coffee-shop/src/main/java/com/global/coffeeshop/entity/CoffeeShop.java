package com.global.coffeeshop.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "coffee_shop")
public class CoffeeShop extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    private Integer phone;

    @OneToMany(mappedBy = "coffeeShop")
    private List<Menu> menuList;

    @OneToMany(mappedBy = "coffeeShop")
    private List<OrderQueue> orderQueueList;

    public List<OrderQueue> getOrderQueueList() {
        return orderQueueList;
    }

    public void setOrderQueueList(List<OrderQueue> orderQueueList) {
        this.orderQueueList = orderQueueList;
    }

    public List<Menu> getMenuList() {
        return menuList;
    }

    public void setMenuList(List<Menu> menuList) {
        this.menuList = menuList;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }
}
