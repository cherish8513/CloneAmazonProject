package com.logistic.domain.stock;

import com.logistic.domain.delivery.Delivery;
import com.logistic.domain.item.Item;
import com.logistic.domain.member.Member;
import com.querydsl.core.annotations.QueryEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@QueryEntity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class Stock {

    @Id @GeneratedValue
    @Column(name = "sell_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "member_id")
    private Member member;

    @ManyToOne
    @JoinColumn(name = "item_id")
    private Item item;

    @OneToOne
    @JoinColumn(name = "delivery_id")
    private Delivery delivery;
}
