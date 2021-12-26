package com.amazonClone.logisticSystem.service.order;

import com.amazonClone.logisticSystem.dto.order.request.SaveOrderReqDto;
import com.amazonClone.logisticSystem.dto.order.response.FindOrdersResDto;

import java.util.List;

public interface OrderService {
    public Long order(SaveOrderReqDto requestDto);
    public void cancelOrder(Long orderId);
    public List<FindOrdersResDto> findOrders(Long memberId);
}
