package com.logistic.repository.order.query;

import com.logistic.domain.delivery.DeliveryStatus;
import com.logistic.domain.delivery.QDelivery;
import com.logistic.domain.member.QMember;
import com.logistic.domain.order.Order;
import com.logistic.domain.order.QOrder;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class OrderQueryRepositoryImpl implements OrderQueryRepository {

    private final JPAQueryFactory query;
    private final QMember member = new QMember("member1");
    private final QOrder order = new QOrder("order1");
    private final QDelivery delivery = new QDelivery("delivery1");

    @Override
    public List<Order> findAllOrdersById(Long memberId) {
        return query
                .select(order)
                .from(order)
                .join(order.member, member)
                .join(order.delivery, delivery).fetchJoin()
                .where(memberIdEq(memberId))
                .fetch();
    }

    @Override
    public List<Order> findBeforeDepOrdersById(Long memberId) {
        return query
                .select(order)
                .from(order)
                .join(order.member, member)
                .join(order.delivery, delivery).fetchJoin()
                .where(memberIdEq(memberId), beforeDeliveryEq(DeliveryStatus.BEFORE_DEPARTURE))
                .fetch();
    }

    @Override
    public List<Order> findOnWayOrdersById(Long memberId) {
        return query
                .select(order)
                .from(order)
                .join(order.member, member)
                .join(order.delivery, delivery).fetchJoin()
                .where(memberIdEq(memberId), beforeDeliveryEq(DeliveryStatus.ON_THE_WAY))
                .fetch();
    }

    private BooleanExpression memberIdEq(Long memberIdCond) {
        return memberIdCond != null ? member.id.eq(memberIdCond) : null;
    }

    private BooleanExpression beforeDeliveryEq(DeliveryStatus statusCond) {
        return statusCond != null ? order.delivery.status.eq(statusCond) : null;
    }
}
