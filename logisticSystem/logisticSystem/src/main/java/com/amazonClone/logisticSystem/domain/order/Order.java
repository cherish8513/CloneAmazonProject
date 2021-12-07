package com.amazonClone.logisticSystem.domain.order;

import com.amazonClone.logisticSystem.domain.delivery.Delivery;
import com.amazonClone.logisticSystem.domain.delivery.DeliveryStatus;
import com.amazonClone.logisticSystem.domain.member.Member;
import com.amazonClone.logisticSystem.domain.orderItem.OrderItem;
import com.amazonClone.logisticSystem.domain.util.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Order extends BaseTimeEntity {

    @Id @GeneratedValue
    @Column(name = "order_id")
    private Long id;

    private OrderStatus status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member member;

    @OneToMany(mappedBy = "order")
    private List<OrderItem> orderItems = new ArrayList<>();

    @OneToMany(mappedBy = "order")
    private List<Delivery> deliveries = new ArrayList<>();

    @Builder
    public Order(Member member, List<OrderItem> orderItems, List<Delivery> deliveries) {
        this.member = member;
        addOrderItem(orderItems);
        addDelivery(deliveries);
        changeStatus(OrderStatus.BEFORE_RECEPTION);
    }

    public void changeStatus(OrderStatus status){
        this.status = status;
    }

    public void addOrderItem(List<OrderItem> orderItems){
        for (OrderItem orderItem: orderItems) {
            this.orderItems.add(orderItem);
            orderItem.changeOrder(this);
        }
    }

    public void addDelivery(List<Delivery> deliveries){
        for (Delivery delivery: deliveries) {
            this.deliveries.add(delivery);
            delivery.changeOrder(this);
        }
    }

    public void cancel(){
        for (Delivery delivery: deliveries) {
            if(delivery.getStatus() == DeliveryStatus.COMPLETE){
                throw new IllegalStateException("이미 배송이 완료된 상품은 취소가 불가능합니다.");
            }
        }
        changeStatus(OrderStatus.CANCEL);
        for(OrderItem orderItem: orderItems){
            orderItem.cancel();
        }
    }

    public int getTotalPrice(){
        return orderItems.stream().mapToInt(OrderItem::getTotalPrice).sum();
    }
}
