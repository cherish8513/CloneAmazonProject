package com.amazonClone.logisticSystem.domain.stock;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QStock is a Querydsl query type for Stock
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QStock extends EntityPathBase<Stock> {

    private static final long serialVersionUID = -1400312079L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QStock stock = new QStock("stock");

    public final com.amazonClone.logisticSystem.domain.delivery.QDelivery delivery;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final com.amazonClone.logisticSystem.domain.item.QItem item;

    public final com.amazonClone.logisticSystem.domain.member.QMember member;

    public QStock(String variable) {
        this(Stock.class, forVariable(variable), INITS);
    }

    public QStock(Path<? extends Stock> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QStock(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QStock(PathMetadata metadata, PathInits inits) {
        this(Stock.class, metadata, inits);
    }

    public QStock(Class<? extends Stock> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.delivery = inits.isInitialized("delivery") ? new com.amazonClone.logisticSystem.domain.delivery.QDelivery(forProperty("delivery"), inits.get("delivery")) : null;
        this.item = inits.isInitialized("item") ? new com.amazonClone.logisticSystem.domain.item.QItem(forProperty("item"), inits.get("item")) : null;
        this.member = inits.isInitialized("member") ? new com.amazonClone.logisticSystem.domain.member.QMember(forProperty("member")) : null;
    }

}

