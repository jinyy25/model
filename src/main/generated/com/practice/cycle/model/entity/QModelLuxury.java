package com.practice.cycle.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QModelLuxury is a Querydsl query type for ModelLuxury
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QModelLuxury extends EntityPathBase<ModelLuxury> {

    private static final long serialVersionUID = 1457101950L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QModelLuxury modelLuxury = new QModelLuxury("modelLuxury");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final QLuxury luxury;

    public final QModel model;

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public QModelLuxury(String variable) {
        this(ModelLuxury.class, forVariable(variable), INITS);
    }

    public QModelLuxury(Path<? extends ModelLuxury> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QModelLuxury(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QModelLuxury(PathMetadata metadata, PathInits inits) {
        this(ModelLuxury.class, metadata, inits);
    }

    public QModelLuxury(Class<? extends ModelLuxury> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.luxury = inits.isInitialized("luxury") ? new QLuxury(forProperty("luxury")) : null;
        this.model = inits.isInitialized("model") ? new QModel(forProperty("model")) : null;
    }

}

