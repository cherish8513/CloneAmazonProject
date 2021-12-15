package com.amazonClone.logisticSystem.repository.item;

import com.amazonClone.logisticSystem.dto.Item.FindItemResDto;

import java.util.List;

public interface ItemQueryRepository {
    List<FindItemResDto> findByPriceLessThan(int price);
    List<FindItemResDto> findByItemNameContaining(String itemName);
}
