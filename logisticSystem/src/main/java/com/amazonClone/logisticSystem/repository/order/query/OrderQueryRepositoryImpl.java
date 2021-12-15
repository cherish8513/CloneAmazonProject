package com.amazonClone.logisticSystem.repository.order.query;

import com.amazonClone.logisticSystem.domain.member.QMember;
import com.amazonClone.logisticSystem.domain.order.Order;
import com.amazonClone.logisticSystem.domain.order.QOrder;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderQueryRepositoryImpl implements OrderQueryRepository{

    private final JPAQueryFactory query;
    private final QMember member = new QMember("member1");
    private final QOrder order = new QOrder("order1");

    @Override
    public List<Order> findOrdersByEmail(String email) {
        return query
                .select(order)
                .from(order)
                .join(order.member, member).fetchJoin()
                .where(member.email.eq(email))
                .fetch();
    }
}
