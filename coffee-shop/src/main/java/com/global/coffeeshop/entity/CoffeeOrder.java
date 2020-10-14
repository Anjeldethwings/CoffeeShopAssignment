package com.global.coffeeshop.entity;


import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "coffee_order")
public class CoffeeOrder extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany
    @JoinTable(
            name = "coffee_order_coffee_types",
            joinColumns = {@JoinColumn(name = "coffee_order_id")},
            inverseJoinColumns = {@JoinColumn(name = "coffee_type_id")}
    )
    private List<CoffeeType> coffeeTypeList;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public List<CoffeeType> getCoffeeTypeList() {
        return coffeeTypeList;
    }

    public void setCoffeeTypeList(List<CoffeeType> coffeeTypeList) {
        this.coffeeTypeList = coffeeTypeList;
    }
}
