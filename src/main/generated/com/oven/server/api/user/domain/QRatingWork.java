package com.oven.server.api.user.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QRatingWork is a Querydsl query type for RatingWork
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QRatingWork extends EntityPathBase<RatingWork> {

    private static final long serialVersionUID = -1219391037L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QRatingWork ratingWork = new QRatingWork("ratingWork");

    public final com.oven.server.common.QBaseEntity _super = new com.oven.server.common.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final NumberPath<Float> rating = createNumber("rating", Float.class);

    public final QUser user;

    public final com.oven.server.api.work.domain.QWork work;

    public QRatingWork(String variable) {
        this(RatingWork.class, forVariable(variable), INITS);
    }

    public QRatingWork(Path<? extends RatingWork> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QRatingWork(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QRatingWork(PathMetadata metadata, PathInits inits) {
        this(RatingWork.class, metadata, inits);
    }

    public QRatingWork(Class<? extends RatingWork> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
        this.work = inits.isInitialized("work") ? new com.oven.server.api.work.domain.QWork(forProperty("work"), inits.get("work")) : null;
    }

}

