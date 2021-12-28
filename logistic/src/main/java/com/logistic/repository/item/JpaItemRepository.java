package com.logistic.repository.item;

import com.logistic.domain.item.Item;
import org.springframework.data.jpa.repository.JpaRepository;

public interface JpaItemRepository extends JpaRepository<Item, Long>, ItemQueryRepository {

}
