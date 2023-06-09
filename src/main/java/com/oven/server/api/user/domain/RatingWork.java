package com.oven.server.api.user.domain;

import com.oven.server.api.BaseEntity;
import com.oven.server.api.work.domain.Work;
import lombok.Getter;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Entity
@Getter
@NoArgsConstructor
public class RatingWork extends BaseEntity {

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

    public RatingWork(User user, Work work, int rating) {
        this.user = user;
        this.work = work;
        this.rating = rating;
    }

    public void changeRating(int rating) {
        this.rating = rating;
    }

}
