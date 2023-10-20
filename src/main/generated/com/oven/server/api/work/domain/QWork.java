package com.oven.server.api.work.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QWork is a Querydsl query type for Work
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QWork extends EntityPathBase<Work> {

    private static final long serialVersionUID = 1946525836L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QWork work = new QWork("work");

    public final com.oven.server.common.QBaseEntity _super = new com.oven.server.common.QBaseEntity(this);

    public final StringPath actor = createString("actor");

    public final StringPath ageRating = createString("ageRating");

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final StringPath director = createString("director");

    public final QGenre genre;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final StringPath poster = createString("poster");

    public final ListPath<WorkProvider, QWorkProvider> providerList = this.<WorkProvider, QWorkProvider>createList("providerList", WorkProvider.class, QWorkProvider.class, PathInits.DIRECT2);

    public final StringPath summary = createString("summary");

    public final StringPath titleEng = createString("titleEng");

    public final StringPath titleKr = createString("titleKr");

    public final NumberPath<Integer> year = createNumber("year", Integer.class);

    public QWork(String variable) {
        this(Work.class, forVariable(variable), INITS);
    }

    public QWork(Path<? extends Work> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QWork(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QWork(PathMetadata metadata, PathInits inits) {
        this(Work.class, metadata, inits);
    }

    public QWork(Class<? extends Work> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.genre = inits.isInitialized("genre") ? new QGenre(forProperty("genre")) : null;
    }

}

