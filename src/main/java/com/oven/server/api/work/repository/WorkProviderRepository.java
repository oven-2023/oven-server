package com.oven.server.api.work.repository;

import com.oven.server.api.work.domain.WorkProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WorkProviderRepository extends JpaRepository<WorkProvider, Long> {

    List<WorkProvider> findWorkProviderByWorkId(Long workId);
}
