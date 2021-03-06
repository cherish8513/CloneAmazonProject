package com.logistic.repository.item;

import com.logistic.dto.Item.response.FindItemResDto;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;

import java.util.List;

@RequiredArgsConstructor
public class ItemQueryRepositoryImpl implements ItemQueryRepository {
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
