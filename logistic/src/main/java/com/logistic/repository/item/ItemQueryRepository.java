package com.logistic.repository.item;

import com.logistic.dto.Item.response.FindItemResDto;

import java.util.List;

public interface ItemQueryRepository {
    List<FindItemResDto> findByPriceLessThan(int price);
    List<FindItemResDto> findByItemNameContaining(String itemName);
}
