package com.amazonClone.logisticSystem.dto.Item.response;

import com.amazonClone.logisticSystem.domain.categoryItem.CategoryItem;
import com.amazonClone.logisticSystem.domain.util.Address;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 등록, 수정 등 범용적으로 사용하는 DTO (모든 속성 포함, 응답 시 사용)
 */
@Getter @Setter
public class ItemResDto {

    private Long id;

    private String name;

    private Address origin;

    private int price;

    private int stockQuantity;

    private List<CategoryItem> categories;
}
