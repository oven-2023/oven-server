package com.oven.server.api.chat.repository;

import com.oven.server.api.chat.domain.Entrance;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EntranceRepository extends JpaRepository<Entrance, Long> {

}
