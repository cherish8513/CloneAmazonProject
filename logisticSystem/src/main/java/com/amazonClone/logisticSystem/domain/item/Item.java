package com.amazonClone.logisticSystem.domain.item;

import com.amazonClone.logisticSystem.domain.categoryItem.CategoryItem;
import com.amazonClone.logisticSystem.domain.util.Address;
import com.amazonClone.logisticSystem.domain.util.BaseTimeEntity;
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

    @Id
    @GeneratedValue
    @Column(name = "item_id")
    private Long id;

    private String name;

    @Embedded
    private Address origin;

    private int price;

    private int stockQuantity;

    @OneToMany(mappedBy = "item")
    private List<CategoryItem> categories = new ArrayList<>();

    @Builder
    public Item(String name, Address origin, int price, int stockQuantity) {
        this.name = name;
        this.origin = origin;
        this.price = price;
        this.stockQuantity = stockQuantity;
    }

    public void addStockCount(int count) {
        stockQuantity += count;
    }

}
