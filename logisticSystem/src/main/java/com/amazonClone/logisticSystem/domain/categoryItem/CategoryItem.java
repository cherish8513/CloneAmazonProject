package com.amazonClone.logisticSystem.domain.categoryItem;

import com.amazonClone.logisticSystem.domain.category.Category;
import com.amazonClone.logisticSystem.domain.item.Item;
import com.amazonClone.logisticSystem.domain.util.BaseTimeEntity;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Getter
public class CategoryItem extends BaseTimeEntity {
    @Id
    @GeneratedValue
    @Column(name = "category_item_id")
    private Long id;

    @ManyToOne
    @JoinColumn(name = "category_id")
    Category category;

    @ManyToOne
    @JoinColumn(name = "item_id")
    Item item;

}
