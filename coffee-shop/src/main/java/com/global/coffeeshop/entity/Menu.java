package com.global.coffeeshop.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "menu")
public class Menu extends AbstractEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    private CoffeeShop coffeeShop;

    @ManyToMany(fetch = FetchType.EAGER, cascade = CascadeType.PERSIST)
    @JoinTable(
            name = "menu_coffee_types",
            joinColumns = {@JoinColumn(name = "menu_id")},
            inverseJoinColumns = {@JoinColumn(name = "coffee_type_id")}
    )
    private List<CoffeeType> coffeeTypeList;

    public List<CoffeeType> getCoffeeTypeList() {
        return coffeeTypeList;
    }

    public void setCoffeeTypeList(List<CoffeeType> coffeeTypeList) {
        this.coffeeTypeList = coffeeTypeList;
    }

    public CoffeeShop getCoffeeShop() {
        return coffeeShop;
    }

    public void setCoffeeShop(CoffeeShop coffeeShop) {
        this.coffeeShop = coffeeShop;
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
}
