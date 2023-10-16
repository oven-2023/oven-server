package com.oven.server.api.work.repository;

import com.oven.server.api.work.domain.Work;
import com.querydsl.core.types.dsl.BooleanExpression;
import com.querydsl.jpa.impl.JPAQueryFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import java.util.List;

import static com.oven.server.api.work.domain.QWork.work;

@Repository
public class WorkRepositoryCustomImpl extends QuerydslRepositorySupport implements WorkRepositoryCustom {

    private final JPAQueryFactory queryFactory;

    public WorkRepositoryCustomImpl(JPAQueryFactory queryFactory) {
        super(Work.class);
        this.queryFactory = queryFactory;
    }

    @Override
    public List<Work> searchWork(Pageable pageable, Long workId, String keyword) {

        return queryFactory
                .select(work)
                .from(work)
                .where(
                        ltWorkId(workId),
                        keywordContaining(keyword)
                )
                .limit(pageable.getPageSize() + 1)
                .fetch();

    }

    private BooleanExpression ltWorkId(Long workId) {
        return (workId == null) ? null : work.id.lt(workId);
    }

    private BooleanExpression keywordContaining(String keyword) {
        return (keyword == null) ? null : work.titleKr.contains(keyword);
    }

}
