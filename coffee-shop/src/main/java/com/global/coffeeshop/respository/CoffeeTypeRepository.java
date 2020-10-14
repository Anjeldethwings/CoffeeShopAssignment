package com.global.coffeeshop.respository;

import com.global.coffeeshop.entity.CoffeeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CoffeeTypeRepository extends JpaRepository<CoffeeType, Long> {

}
