package com.oven.server.api.user.repository;

import com.oven.server.api.user.domain.InterestingWork;
import com.oven.server.api.user.domain.User;
import com.oven.server.api.work.domain.Work;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface InterestingWorkRepository extends JpaRepository<InterestingWork, Long> {

    List<InterestingWork> findByUserOrderByCreatedAtDesc(User user);

    InterestingWork findByUserAndWork(User user, Work work);

}
