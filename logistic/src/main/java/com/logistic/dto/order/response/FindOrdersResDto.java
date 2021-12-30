package com.logistic.dto.order.response;

import com.logistic.domain.order.Order;
import com.logistic.domain.order.OrderStatus;
import com.logistic.domain.util.Address;
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
    private Address addresses;


    protected FindOrdersResDto() {
    }


    
    public FindOrdersResDto(Order order) {
        order.getOrderItems().stream().forEach(oi -> orderItemName.add(oi.getItem().getName()));
        order.getTotalPrice();
        orderDate = order.getCreatedDate();
        orderStatus = order.getStatus();
        addresses = order.getDelivery().getAddress();
    }
}
