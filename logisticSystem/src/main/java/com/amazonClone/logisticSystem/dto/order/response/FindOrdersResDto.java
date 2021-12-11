package com.amazonClone.logisticSystem.dto.order.response;

import com.amazonClone.logisticSystem.domain.delivery.Delivery;
import com.amazonClone.logisticSystem.domain.order.Order;
import com.amazonClone.logisticSystem.domain.order.OrderStatus;
import com.amazonClone.logisticSystem.domain.util.Address;
import com.querydsl.core.annotations.QueryProjection;
import lombok.Builder;
import lombok.Data;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Data
public class FindOrdersResDto {
    private List<String> orderItemName = new ArrayList<>();
    private int totalPrice;
    private LocalDateTime orderDate;
    private OrderStatus orderStatus;
    private List<Address> addresses = new ArrayList<>();


    public FindOrdersResDto() {
    }

    
    public FindOrdersResDto(Order order) {
        order.getOrderItems().stream().forEach(oi -> orderItemName.add(oi.getItem().getName()));
        order.getTotalPrice();
        orderDate = order.getCreatedDate();
        orderStatus = order.getStatus();
        order.getDeliveries().stream().forEach(a -> addresses.add(a.getAddress()));
    }
}
