package com.logistic.domain.order;

import com.logistic.domain.delivery.Delivery;
import com.logistic.domain.delivery.DeliveryStatus;
import com.logistic.domain.member.Member;
import com.logistic.domain.orderItem.OrderItem;
import com.logistic.domain.util.BaseTimeEntity;
import com.querydsl.core.annotations.QueryEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@QueryEntity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
@Table(name = "orders")
public class Order extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;
  
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;
  
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Builder
    public Order(Member member) {
        this.member = member;
        changeStatus(OrderStatus.BEFORE_RECEPTION);
    }

    public void changeDelivery(Delivery delivery){
        this.delivery = delivery;
    }

    public void changeStatus(OrderStatus status){
        this.status = status;
    }

    public void cancel(){
        if(delivery.getStatus() == DeliveryStatus.COMPLETE){
            throw new IllegalStateException("이미 배송이 완료된 상품은 취소가 불가능합니다.");
        }
        changeStatus(OrderStatus.CANCEL);
        delivery.changeStatus(DeliveryStatus.CANCEL);
        for(OrderItem orderItem: orderItems){
            orderItem.cancel();
        }
    }

    public int getTotalPrice(){
        return orderItems.stream().mapToInt(OrderItem::getTotalPrice).sum();
    }
}
