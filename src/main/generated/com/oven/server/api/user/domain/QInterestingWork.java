package com.oven.server.api.user.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QInterestingWork is a Querydsl query type for InterestingWork
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QInterestingWork extends EntityPathBase<InterestingWork> {

    private static final long serialVersionUID = -129692332L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QInterestingWork interestingWork = new QInterestingWork("interestingWork");

    public final com.oven.server.common.QBaseEntity _super = new com.oven.server.common.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final QUser user;

    public final com.oven.server.api.work.domain.QWork work;

    public QInterestingWork(String variable) {
        this(InterestingWork.class, forVariable(variable), INITS);
    }

    public QInterestingWork(Path<? extends InterestingWork> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QInterestingWork(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QInterestingWork(PathMetadata metadata, PathInits inits) {
        this(InterestingWork.class, metadata, inits);
    }

    public QInterestingWork(Class<? extends InterestingWork> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.user = inits.isInitialized("user") ? new QUser(forProperty("user")) : null;
        this.work = inits.isInitialized("work") ? new com.oven.server.api.work.domain.QWork(forProperty("work"), inits.get("work")) : null;
    }

}

