package com.amazonClone.logisticSystem.service.order;

import com.amazonClone.logisticSystem.dto.order.request.SaveOrderRequestDto;
import com.amazonClone.logisticSystem.dto.order.response.FindOrdersResDto;

import java.util.List;

public interface OrderService {
    public Long order(SaveOrderRequestDto requestDto);
    public void cancelOrder(Long orderId);
    public List<FindOrdersResDto> findOrders(String email);
}
