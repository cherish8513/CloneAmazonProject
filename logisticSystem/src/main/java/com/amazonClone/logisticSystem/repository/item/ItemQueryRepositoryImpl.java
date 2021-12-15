package com.amazonClone.logisticSystem.repository.item;

import com.amazonClone.logisticSystem.dto.Item.FindItemResDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@RequiredArgsConstructor
public class ItemQueryRepositoryImpl implements ItemQueryRepository{
    private final JPAQueryFactory query;

    @Override
    public List<FindItemResDto> findByPriceLessThan(int price) {
        return null;
    }

    @Override
    public List<FindItemResDto> findByItemNameContaining(String itemName) {
        return null;
    }
}
