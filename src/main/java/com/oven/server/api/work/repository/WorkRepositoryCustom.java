package com.oven.server.api.work.repository;

import com.oven.server.api.work.domain.Work;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface WorkRepositoryCustom {

    List<Work> searchWork(int size, Long workId, String keyword);
}
