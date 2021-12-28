package com.logistic.dto.Item.request;

import com.logistic.domain.categoryItem.CategoryItem;
import com.logistic.domain.util.Address;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 등록, 수정 등 범용적으로 사용하는 DTO (ID를 제외한 모든 속성 포함, 요청 시 사용)
 */
@Getter
@Setter
public class SaveItemReqDto {

    private String name;

    private Address origin;

    private int price;

    private int stockQuantity;

    private List<CategoryItem> categories;
}

