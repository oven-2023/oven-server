package com.oven.server.api.user.domain;

import com.oven.server.api.work.domain.Work;

import javax.persistence.*;

@Entity
public class RatingWork {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "work_id")
    private Work work;

    private int rating;     // 1~5점

}
