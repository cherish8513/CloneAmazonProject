package com.logistic.service.order;

import com.logistic.dto.order.request.SaveOrderReqDto;
import com.logistic.dto.order.response.FindOrdersResDto;

import java.util.List;

public interface OrderService {
    public Long order(SaveOrderReqDto requestDto);
    public void cancelOrder(Long orderId);
    public List<FindOrdersResDto> findOrders(Long memberId);
}
