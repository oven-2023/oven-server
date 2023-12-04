package com.oven.server.api.work.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QWorkProvider is a Querydsl query type for WorkProvider
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QWorkProvider extends EntityPathBase<WorkProvider> {

    private static final long serialVersionUID = 1120976541L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QWorkProvider workProvider = new QWorkProvider("workProvider");

    public final com.oven.server.common.QBaseEntity _super = new com.oven.server.common.QBaseEntity(this);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final QProvider provider;

    public final QWork work;

    public QWorkProvider(String variable) {
        this(WorkProvider.class, forVariable(variable), INITS);
    }

    public QWorkProvider(Path<? extends WorkProvider> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QWorkProvider(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QWorkProvider(PathMetadata metadata, PathInits inits) {
        this(WorkProvider.class, metadata, inits);
    }

    public QWorkProvider(Class<? extends WorkProvider> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.provider = inits.isInitialized("provider") ? new QProvider(forProperty("provider")) : null;
        this.work = inits.isInitialized("work") ? new QWork(forProperty("work"), inits.get("work")) : null;
    }

}

