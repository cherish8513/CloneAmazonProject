package com.amazonClone.logisticSystem.domain.delivery;

import com.amazonClone.logisticSystem.domain.order.Order;
import com.amazonClone.logisticSystem.domain.util.Address;
import com.amazonClone.logisticSystem.domain.util.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
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

    DeliveryStatus status;

    LocalDateTime sendDate;

    LocalDateTime receiveDate;

    @Builder
    public Delivery(Address address) {
        this.address = address;
    }

    public void changeOrder(Order order) {
        this.order = order;
    }
}
