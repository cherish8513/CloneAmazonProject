package com.logistic.repository.order.query;

import com.logistic.domain.order.Order;

import java.util.List;

public interface OrderQueryRepository {

    public List<Order> findOrdersById(Long memberId);

}
