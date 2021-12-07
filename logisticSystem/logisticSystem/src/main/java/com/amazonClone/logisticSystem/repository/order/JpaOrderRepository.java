package com.amazonClone.logisticSystem.repository.order;

import com.amazonClone.logisticSystem.domain.order.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaOrderRepository extends JpaRepository<Order, Long> {
}
