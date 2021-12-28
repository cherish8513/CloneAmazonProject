package com.logistic.domain.item;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QItem is a Querydsl query type for Item
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QItem extends EntityPathBase<Item> {

    private static final long serialVersionUID = -806173659L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QItem item = new QItem("item");

    public final com.logistic.domain.util.QBaseTimeEntity _super = new com.logistic.domain.util.QBaseTimeEntity(this);

    public final ListPath<com.logistic.domain.categoryItem.CategoryItem, SimplePath<com.logistic.domain.categoryItem.CategoryItem>> categories = this.<com.logistic.domain.categoryItem.CategoryItem, SimplePath<com.logistic.domain.categoryItem.CategoryItem>>createList("categories", com.logistic.domain.categoryItem.CategoryItem.class, SimplePath.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final StringPath name = createString("name");

    public final SimplePath<com.logistic.domain.util.Address> origin = createSimple("origin", com.logistic.domain.util.Address.class);

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final com.logistic.domain.member.QMember seller;

    public final NumberPath<Integer> stockQuantity = createNumber("stockQuantity", Integer.class);

    public QItem(String variable) {
        this(Item.class, forVariable(variable), INITS);
    }

    public QItem(Path<? extends Item> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QItem(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QItem(PathMetadata metadata, PathInits inits) {
        this(Item.class, metadata, inits);
    }

    public QItem(Class<? extends Item> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.seller = inits.isInitialized("seller") ? new com.logistic.domain.member.QMember(forProperty("seller")) : null;
    }

}

