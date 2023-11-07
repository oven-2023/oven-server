package com.oven.server.api.chat.repository;

import com.oven.server.api.chat.domain.Chatroom;
import com.oven.server.api.work.domain.Provider;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ChatroomRepository extends JpaRepository<Chatroom, Long> {

    List<Chatroom> findByProvider(Provider provider);

}
