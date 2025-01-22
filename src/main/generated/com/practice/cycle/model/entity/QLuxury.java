package com.practice.cycle.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QLuxury is a Querydsl query type for Luxury
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QLuxury extends EntityPathBase<Luxury> {

    private static final long serialVersionUID = 1647967525L;

    public static final QLuxury luxury = new QLuxury("luxury");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<ModelLuxury, QModelLuxury> modelLuxuries = this.<ModelLuxury, QModelLuxury>createList("modelLuxuries", ModelLuxury.class, QModelLuxury.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public QLuxury(String variable) {
        super(Luxury.class, forVariable(variable));
    }

    public QLuxury(Path<? extends Luxury> path) {
        super(path.getType(), path.getMetadata());
    }

    public QLuxury(PathMetadata metadata) {
        super(Luxury.class, metadata);
    }

}

