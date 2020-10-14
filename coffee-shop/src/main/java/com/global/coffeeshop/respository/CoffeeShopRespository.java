package com.global.coffeeshop.respository;

import com.global.coffeeshop.entity.CoffeeShop;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoffeeShopRespository extends JpaRepository<CoffeeShop, Long> {

}
