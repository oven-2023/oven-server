package com.oven.server.api.chat.domain;

import com.oven.server.api.user.domain.User;
import com.oven.server.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;


@Entity
@Getter
@NoArgsConstructor
public class Message extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "message_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "chatroom_id")
    private Chatroom chatroom;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User sender;

    @Lob
    private String content;

    @Builder
    public Message(Chatroom chatroom, User sender, String content) {
        this.chatroom = chatroom;
        this.sender = sender;
        this.content = content;
    }

}
