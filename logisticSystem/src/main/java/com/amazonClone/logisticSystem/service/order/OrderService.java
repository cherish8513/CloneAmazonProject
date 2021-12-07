package com.amazonClone.logisticSystem.service.order;

import com.amazonClone.logisticSystem.dto.order.OrderRequestDto;

public interface OrderService {
    public Long order(OrderRequestDto requestDto);
}
