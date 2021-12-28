package com.logistic.domain.item;

import com.logistic.domain.categoryItem.CategoryItem;
import com.logistic.domain.member.Member;
import com.logistic.domain.util.Address;
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
public class Item extends BaseTimeEntity {

    /**
     * 등록 물품 ID
     */
    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    /**
     * 등록 물품 이름
     */
    private String name;

    /**
     * 등록 물품 원산지
     */
    @Embedded
    private Address origin;

    /**
     * 등록 물품 가격
     */
    private int price;

    /**
     * 등록 물품 재고
     */
    private int stockQuantity;

    /**
     * 생산자 객체
     */
    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "member_id")
    private Member seller;

    /**
     * 등록 물품 카테고리 리스트
     */
    @OneToMany(mappedBy = "item")
    private List<CategoryItem> categories = new ArrayList<>();

    @Builder
    public Item(String name, Address origin, int price, int stockQuantity, Member seller) {
        this.name = name;
        this.origin = origin;
        this.price = price;
        this.stockQuantity = stockQuantity;
        this.seller = seller;
    }

    public void updateItem(String name, Address origin, int price, int stockQuantity) {
        this.name = name;
        this.origin = origin;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public void addStockCount(int count) {
        stockQuantity += count;
    }
}
