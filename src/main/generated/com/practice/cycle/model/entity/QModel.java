package com.practice.cycle.model.entity;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QModel is a Querydsl query type for Model
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QModel extends EntityPathBase<Model> {

    private static final long serialVersionUID = 1300811281L;

    public static final QModel model = new QModel("model");

    public final DateTimePath<java.time.LocalDateTime> createdAt = createDateTime("createdAt", java.time.LocalDateTime.class);

    public final EnumPath<Gender> gender = createEnum("gender", Gender.class);

    public final NumberPath<Long> id = createNumber("id", Long.class);

    public final ListPath<ModelLuxury, QModelLuxury> modelLuxuries = this.<ModelLuxury, QModelLuxury>createList("modelLuxuries", ModelLuxury.class, QModelLuxury.class, PathInits.DIRECT2);

    public final StringPath name = createString("name");

    public final DateTimePath<java.time.LocalDateTime> updatedAt = createDateTime("updatedAt", java.time.LocalDateTime.class);

    public QModel(String variable) {
        super(Model.class, forVariable(variable));
    }

    public QModel(Path<? extends Model> path) {
        super(path.getType(), path.getMetadata());
    }

    public QModel(PathMetadata metadata) {
        super(Model.class, metadata);
    }

}

