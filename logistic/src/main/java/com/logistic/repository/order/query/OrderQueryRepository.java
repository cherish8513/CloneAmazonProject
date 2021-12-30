package com.logistic.repository.order.query;

import com.logistic.domain.order.Order;

import java.util.List;

public interface OrderQueryRepository {

    public List<Order> findAllOrdersById(Long memberId);
    public List<Order> findBeforeDepOrdersById(Long memberId);
    public List<Order> findOnWayOrdersById(Long memberId);

}
