package com.oven.server.api.user.repository;

import com.oven.server.api.user.domain.RatingWork;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RatingWorkRepository extends JpaRepository<RatingWork, Long> {
}
