package com.global.coffeeshop.respository;

import com.global.coffeeshop.entity.CoffeeOrderCoffeeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoffeeOrderCoffeeTypeRepository extends JpaRepository<CoffeeOrderCoffeeType, Long> {
}
