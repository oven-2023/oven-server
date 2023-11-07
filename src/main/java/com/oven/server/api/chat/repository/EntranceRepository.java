package com.oven.server.api.chat.repository;

import com.oven.server.api.chat.domain.Chatroom;
import com.oven.server.api.chat.domain.Entrance;
import com.oven.server.api.user.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EntranceRepository extends JpaRepository<Entrance, Long> {

    Optional<Entrance> findByUserAndChatroom(User user, Chatroom chatroom);

    List<Entrance> findByUser(User user);

}
