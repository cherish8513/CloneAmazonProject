package com.amazonClone.logisticSystem.repository.order.query;

import com.amazonClone.logisticSystem.domain.order.Order;

import java.util.List;

public interface OrderQueryRepository {

    public List<Order> findOrdersByEmail(String email);

}
