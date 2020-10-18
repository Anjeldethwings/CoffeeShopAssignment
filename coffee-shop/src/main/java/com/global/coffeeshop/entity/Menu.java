package com.global.coffeeshop.entity;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "menu")
@Where(clause = "is_deleted = false")
public class Menu extends AbstractEntity implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToOne
    private CoffeeShop coffeeShop;


    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "menu_coffee_types",
            joinColumns = {@JoinColumn(referencedColumnName = "id", name = "menu_id")},
            inverseJoinColumns = {@JoinColumn(referencedColumnName = "id", name = "coffee_type_id")}
    )
    private List<CoffeeType> coffeeTypeList;

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

    public CoffeeShop getCoffeeShop() {
        return coffeeShop;
    }

    public void setCoffeeShop(CoffeeShop coffeeShop) {
        this.coffeeShop = coffeeShop;
    }

    public List<CoffeeType> getCoffeeTypeList() {
        return coffeeTypeList;
    }

    public void setCoffeeTypeList(List<CoffeeType> coffeeTypeList) {
        this.coffeeTypeList = coffeeTypeList;
    }
}
