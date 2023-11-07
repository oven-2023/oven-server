package com.oven.server.api.chat.repository;

import com.oven.server.api.chat.domain.Chatroom;
import com.oven.server.api.chat.domain.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MessageRepository extends JpaRepository<Message, Long> {

    List<Message> findByChatroom(Chatroom chatroom);

}
