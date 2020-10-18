package com.global.coffeeshop.entity;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "coffee_type")
@Where(clause = "is_deleted = false")
public class CoffeeType extends AbstractEntity implements Serializable {

    public CoffeeType(){

    }

    public CoffeeType(Long typeId){
        this.id = typeId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "coffeeTypeList")
    private List<Menu> menuList;

    @OneToMany(mappedBy = "coffeeType")
    private List<CoffeeOrderCoffeeType> coffeeOrderCoffeeTypeList;

    public List<CoffeeOrderCoffeeType> getCoffeeOrderCoffeeTypeList() {
        return coffeeOrderCoffeeTypeList;
    }

    public void setCoffeeOrderCoffeeTypeList(List<CoffeeOrderCoffeeType> coffeeOrderCoffeeTypeList) {
        this.coffeeOrderCoffeeTypeList = coffeeOrderCoffeeTypeList;
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
}
