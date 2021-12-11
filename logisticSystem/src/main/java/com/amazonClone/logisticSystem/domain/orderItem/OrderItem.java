package com.amazonClone.logisticSystem.domain.orderItem;

import com.amazonClone.logisticSystem.domain.item.Item;
import com.amazonClone.logisticSystem.domain.order.Order;
import com.amazonClone.logisticSystem.domain.util.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class OrderItem extends BaseTimeEntity {

    @Id
    @GeneratedValue
    @Column(name = "order_item_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "order_id")
    private Order order;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    private int orderPrice;

    @Builder
    public OrderItem(Item item, int count, Order order) {
        this.item = item;
        this.count = count;
        orderPrice = item.getPrice();
        if(order != null){
            changeOrder(order);
        }
    }

    private int count;

    public void changeOrder(Order order) {
        this.order = order;
        order.getOrderItems().add(this);
    }

    public void cancel() {
        item.addStockCount(count);
    }

    public int getTotalPrice() {
        return orderPrice * count;
    }
}
