package com.global.coffeeshop.entity;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name="coffee_type")
public class CoffeeType extends AbstractEntity{

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;

    @ManyToMany(mappedBy = "coffeeTypeList")
    private List<Menu> menuList;

    @ManyToMany(mappedBy = "coffeeTypeList")
    private List<CoffeeOrder> coffeeOrderList;

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
