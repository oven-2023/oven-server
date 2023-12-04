package com.oven.server.api.chat.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;
import com.querydsl.core.types.dsl.PathInits;


/**
 * QEntrance is a Querydsl query type for Entrance
 */
@Generated("com.querydsl.codegen.DefaultEntitySerializer")
public class QEntrance extends EntityPathBase<Entrance> {

    private static final long serialVersionUID = 1361349880L;

    private static final PathInits INITS = PathInits.DIRECT2;

    public static final QEntrance entrance = new QEntrance("entrance");

    public final com.oven.server.common.QBaseEntity _super = new com.oven.server.common.QBaseEntity(this);

    public final QChatroom chatroom;

    //inherited
    public final DateTimePath<java.time.LocalDateTime> createdAt = _super.createdAt;

    public final NumberPath<Long> id = createNumber("id", Long.class);

    //inherited
    public final DateTimePath<java.time.LocalDateTime> modifiedAt = _super.modifiedAt;

    public final com.oven.server.api.user.domain.QUser user;

    public QEntrance(String variable) {
        this(Entrance.class, forVariable(variable), INITS);
    }

    public QEntrance(Path<? extends Entrance> path) {
        this(path.getType(), path.getMetadata(), PathInits.getFor(path.getMetadata(), INITS));
    }

    public QEntrance(PathMetadata metadata) {
        this(metadata, PathInits.getFor(metadata, INITS));
    }

    public QEntrance(PathMetadata metadata, PathInits inits) {
        this(Entrance.class, metadata, inits);
    }

    public QEntrance(Class<? extends Entrance> type, PathMetadata metadata, PathInits inits) {
        super(type, metadata, inits);
        this.chatroom = inits.isInitialized("chatroom") ? new QChatroom(forProperty("chatroom"), inits.get("chatroom")) : null;
        this.user = inits.isInitialized("user") ? new com.oven.server.api.user.domain.QUser(forProperty("user")) : null;
    }

}

