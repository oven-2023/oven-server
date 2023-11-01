package com.oven.server.api.chat.domain;

import com.oven.server.api.user.domain.User;
import com.oven.server.api.work.domain.Provider;
import com.oven.server.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor
public class Chatroom extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chatroom_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provider_id")
    private Provider provider;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User roomAdmin;

    private String title;

    private int wholeNum;

    private int count;

    @Builder
    public Chatroom(User roomAdmin, Provider provider, String title, int wholeNum) {
        this.roomAdmin = roomAdmin;
        this.provider = provider;
        this.title = title;
        this.wholeNum = wholeNum;
        this.count = 1;
    }

    public void enter() {
        this.count++;
    }

    public void leave() {
        this.count--;
    }



}
