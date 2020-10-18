package com.global.coffeeshop.respository;

import com.global.coffeeshop.entity.CoffeeOrder;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface CoffeeOrderRepository extends JpaRepository<CoffeeOrder, Long> {

    @Query(value = "SELECT c FROM CoffeeOrder c WHERE c.id = :id")
    CoffeeOrder findCoffeeOrderById(@Param("id") Long id);

}
