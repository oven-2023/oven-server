package com.oven.server.api.user.repository;

import com.oven.server.api.user.domain.RatingWork;
import com.oven.server.api.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface RatingWorkRepository extends JpaRepository<RatingWork, Long> {

    List<RatingWork> findByUserOrderByCreatedAtDesc(User user);

}
