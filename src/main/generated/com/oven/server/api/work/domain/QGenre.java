package com.oven.server.api.work.domain;

import static com.querydsl.core.types.PathMetadataFactory.*;

import com.querydsl.core.types.dsl.*;

import com.querydsl.core.types.PathMetadata;
import javax.annotation.processing.Generated;
import com.querydsl.core.types.Path;


/**
 * QGenre is a Querydsl query type for Genre
 */
@Generated("com.querydsl.codegen.DefaultEmbeddableSerializer")
public class QGenre extends BeanPath<Genre> {

    private static final long serialVersionUID = 197681000L;

    public static final QGenre genre = new QGenre("genre");

    public final BooleanPath action = createBoolean("action");

    public final BooleanPath adult = createBoolean("adult");

    public final BooleanPath adventure = createBoolean("adventure");

    public final BooleanPath animation = createBoolean("animation");

    public final BooleanPath comedy = createBoolean("comedy");

    public final BooleanPath criminal = createBoolean("criminal");

    public final BooleanPath documentary = createBoolean("documentary");

    public final BooleanPath drama = createBoolean("drama");

    public final BooleanPath family = createBoolean("family");

    public final BooleanPath fantasy = createBoolean("fantasy");

    public final BooleanPath horror = createBoolean("horror");

    public final BooleanPath music = createBoolean("music");

    public final BooleanPath musical = createBoolean("musical");

    public final BooleanPath mystery = createBoolean("mystery");

    public final BooleanPath performance = createBoolean("performance");

    public final BooleanPath romance = createBoolean("romance");

    public final BooleanPath SF = createBoolean("SF");

    public final BooleanPath thriller = createBoolean("thriller");

    public final BooleanPath variety = createBoolean("variety");

    public final BooleanPath war = createBoolean("war");

    public final BooleanPath western = createBoolean("western");

    public QGenre(String variable) {
        super(Genre.class, forVariable(variable));
    }

    public QGenre(Path<? extends Genre> path) {
        super(path.getType(), path.getMetadata());
    }

    public QGenre(PathMetadata metadata) {
        super(Genre.class, metadata);
    }

}

