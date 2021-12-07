package com.amazonClone.logisticSystem.repository.item;

import com.amazonClone.logisticSystem.domain.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaItemRepository extends JpaRepository<Item, Long> {
}
