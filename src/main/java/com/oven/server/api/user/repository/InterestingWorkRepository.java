package com.oven.server.api.user.repository;

import com.oven.server.api.user.domain.InterestingWork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface InterestingWorkRepository extends JpaRepository<InterestingWork, Long> {

}
