package com.oven.server.api.chat.domain;

import com.oven.server.api.user.domain.User;
import com.oven.server.common.BaseEntity;
import jakarta.persistence.*;

@Entity
public class Entrance extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "entrance_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chatroom_id")
    private Chatroom chatroom;

}
