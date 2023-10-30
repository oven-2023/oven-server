package com.oven.server.api.chat.domain;

import com.oven.server.api.work.domain.Provider;
import com.oven.server.common.BaseEntity;
import jakarta.persistence.*;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Entity
@Getter
@NoArgsConstructor
public class Chatroom extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "chatroom_id")
    private Long id;

    @Column(name = "chatroom_uuid", unique = true)
    private UUID uuid;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "provider_id")
    private Provider provider;

    private String title;

    private int wholeNum;

    private int count;

    @Builder
    public Chatroom(Provider provider, String title, int wholeNum) {
        this.uuid = UUID.randomUUID();
        this.provider = provider;
        this.title = title;
        this.wholeNum = wholeNum;
        this.count = 1;
    }

}
