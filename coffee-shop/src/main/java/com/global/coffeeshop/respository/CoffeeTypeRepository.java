package com.global.coffeeshop.respository;

import com.global.coffeeshop.entity.CoffeeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CoffeeTypeRepository extends JpaRepository<CoffeeType, Long> {

    @Query(value = "SELECT c FROM CoffeeType c WHERE c.id = :id")
    CoffeeType findCoffeeTypeById(@Param("id") Long id);

}
