package com.oven.server.api.user.domain;

import com.oven.server.api.work.domain.Work;

import javax.persistence.*;

@Entity
public class InterestingWork {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "instersting_work_id")
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private Work work;

}
