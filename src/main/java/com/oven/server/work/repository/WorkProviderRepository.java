package com.oven.server.work.repository;

import com.oven.server.work.domain.WorkProvider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface WorkProviderRepository extends JpaRepository<WorkProvider, Long> {
}
