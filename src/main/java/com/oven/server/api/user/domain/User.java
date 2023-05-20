package com.oven.server.api.user.domain;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String userName;

    private String nickname;

    private String password;

    @OneToMany(mappedBy = "user")
    private List<RatingWork> ratingWorkList = new ArrayList<>();

    private List<InterestingWork> interestingWorkList = new ArrayList<>();

}
