package com.global.coffeeshop.respository;

import com.global.coffeeshop.entity.CoffeeShop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CoffeeShopRespository extends JpaRepository<CoffeeShop, Long> {

    @Query(value = "SELECT c FROM CoffeeShop c WHERE c.id = :id")
    CoffeeShop findCoffeeShopById(@Param("id") Long id);



}
