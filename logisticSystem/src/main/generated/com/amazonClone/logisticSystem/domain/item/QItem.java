package com.amazonClone.logisticSystem.domain.item;

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

    private static final long serialVersionUID = 660137665L;

    public static final QItem item = new QItem("item");

    public final com.amazonClone.logisticSystem.domain.util.QBaseTimeEntity _super = new com.amazonClone.logisticSystem.domain.util.QBaseTimeEntity(this);

    public final ListPath<com.amazonClone.logisticSystem.domain.categoryItem.CategoryItem, SimplePath<com.amazonClone.logisticSystem.domain.categoryItem.CategoryItem>> categories = this.<com.amazonClone.logisticSystem.domain.categoryItem.CategoryItem, SimplePath<com.amazonClone.logisticSystem.domain.categoryItem.CategoryItem>>createList("categories", com.amazonClone.logisticSystem.domain.categoryItem.CategoryItem.class, SimplePath.class, PathInits.DIRECT2);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdDate = _super.createdDate;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedDate = _super.modifiedDate;

    public final StringPath name = createString("name");

    public final SimplePath<com.amazonClone.logisticSystem.domain.util.Address> origin = createSimple("origin", com.amazonClone.logisticSystem.domain.util.Address.class);

    public final NumberPath<Integer> price = createNumber("price", Integer.class);

    public final NumberPath<Integer> stockQuantity = createNumber("stockQuantity", Integer.class);

    public QItem(String variable) {
        super(Item.class, forVariable(variable));
    }

    public QItem(Path<? extends Item> path) {
        super(path.getType(), path.getMetadata());
    }

    public QItem(PathMetadata metadata) {
        super(Item.class, metadata);
    }

}

