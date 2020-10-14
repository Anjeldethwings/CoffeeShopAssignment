package com.global.coffeeshop.respository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface OrderQueueRepository extends JpaRepository<OrderQueueRepository, Long> {
}
