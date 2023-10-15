package com.oven.server.api.work.repository;

import com.oven.server.api.work.domain.Work;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkRepository extends JpaRepository<Work, Long> {

    List<Work> findByTitleKrContaining(String keyword);

    List<Work> findTop10ByOrderByIdAsc();

    @Query(value = "SELECT * FROM oven.work order by RAND() limit 4",nativeQuery = true)
    List<Work> findRandoms();

}
