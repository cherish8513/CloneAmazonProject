package com.logistic.domain.delivery;

import com.logistic.domain.order.Order;
import com.logistic.domain.util.Address;
import com.logistic.domain.util.BaseTimeEntity;
import com.querydsl.core.annotations.QueryEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@QueryEntity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Delivery extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "delivery_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "order_id")
    Order order;

    @Embedded
    Address address;

    @Enumerated(EnumType.STRING)
    DeliveryStatus status;

    LocalDateTime sendDate;

    LocalDateTime receiveDate;

    @Builder
    public Delivery(Address address, Order order) {
        this.address = address;
        if(order != null){
            changeOrder(order);
        }
    }

    public void changeOrder(Order order) {
        this.order = order;
        order.getDeliveries().add(this);
    }
}
