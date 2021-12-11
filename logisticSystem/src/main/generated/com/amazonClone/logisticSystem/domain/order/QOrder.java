package com.amazonClone.logisticSystem.domain.order;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QOrder is a Querydsl query type for Order
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QOrder extends EntityPathBase<Order> {

    private static final long serialVersionUID = 1397163361L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QOrder order = new QOrder("order");

    public final com.amazonClone.logisticSystem.domain.util.QBaseTimeEntity _super = new com.amazonClone.logisticSystem.domain.util.QBaseTimeEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final ListPath<com.amazonClone.logisticSystem.domain.delivery.Delivery, SimplePath<com.amazonClone.logisticSystem.domain.delivery.Delivery>> deliveries = this.<com.amazonClone.logisticSystem.domain.delivery.Delivery, SimplePath<com.amazonClone.logisticSystem.domain.delivery.Delivery>>createList("deliveries", com.amazonClone.logisticSystem.domain.delivery.Delivery.class, SimplePath.class, PathInits.DIRECT2);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.amazonClone.logisticSystem.domain.member.QMember member;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final ListPath<com.amazonClone.logisticSystem.domain.orderItem.OrderItem, SimplePath<com.amazonClone.logisticSystem.domain.orderItem.OrderItem>> orderItems = this.<com.amazonClone.logisticSystem.domain.orderItem.OrderItem, SimplePath<com.amazonClone.logisticSystem.domain.orderItem.OrderItem>>createList("orderItems", com.amazonClone.logisticSystem.domain.orderItem.OrderItem.class, SimplePath.class, PathInits.DIRECT2);

    public final EnumPath<OrderStatus> status = createEnum("status", OrderStatus.class);

    public final NumberPath<Integer> totalPrice = createNumber("totalPrice", Integer.class);

    public QOrder(String variable) {
        this(Order.class, forVariable(variable), INITS);
    }

    public QOrder(Path<? extends Order> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QOrder(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QOrder(PathMetadata metadata, PathInits inits) {
        this(Order.class, metadata, inits);
    }

    public QOrder(Class<? extends Order> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.member = inits.isInitialized("member") ? new com.amazonClone.logisticSystem.domain.member.QMember(forProperty("member")) : null;
    }

}

