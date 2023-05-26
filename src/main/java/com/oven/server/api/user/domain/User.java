package com.oven.server.api.user.domain;

import com.fasterxml.jackson.databind.ser.Serializers;
import com.oven.server.api.BaseEntity;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@NoArgsConstructor
public class User extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Long id;

    private String userName;

    private String nickname;

    private String password;

    @OneToMany(mappedBy = "user")
    private List<RatingWork> ratingWorkList = new ArrayList<>();

    @OneToMany(mappedBy = "user")
    private List<InterestingWork> interestingWorkList = new ArrayList<>();

    @Builder
    public User(Long id, String userName, String nickname, String password) {
        this.id = id;
        this.userName = userName;
        this.nickname = nickname;
        this.password = password;
    }

}
