package com.global.coffeeshop.entity;

import org.hibernate.annotations.Where;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;

@Entity
@Table(name = "user_table")
@Where(clause = "is_deleted = false")
public class User extends AbstractEntity implements Serializable {

    public User() {

    }

    public User(Long userId) {
        this.id = userId;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(unique = true)
    private String name;

    private String email;

    private String mobile;

    @ManyToOne
    private Role role;

    @OneToMany(mappedBy = "user")
    private List<CoffeeOrder> coffeeOrderList;

    public Role getRole() {
        return role;
    }

    public void setRole(Role role) {
        this.role = role;
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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }
}
