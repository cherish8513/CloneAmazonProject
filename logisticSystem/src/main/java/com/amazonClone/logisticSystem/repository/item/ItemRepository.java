package com.amazonClone.logisticSystem.repository.item;

import com.amazonClone.logisticSystem.domain.item.Item;

public interface ItemRepository {

    Long save(Item item);

    Item findOne(Long id);

    void delete(Item item);
}
