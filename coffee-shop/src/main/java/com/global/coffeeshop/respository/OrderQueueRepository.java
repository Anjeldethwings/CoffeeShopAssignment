package com.global.coffeeshop.respository;

import com.global.coffeeshop.entity.OrderQueue;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface OrderQueueRepository extends JpaRepository<OrderQueue, Long> {

    //    @Query(value = "SELECT c FROM OrderQueue c WHERE c.CoffeeShop.id = :id")
    List<OrderQueue> getOrderQueueByCoffeeShopId(Long id);

}
