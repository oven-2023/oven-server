package com.oven.server.api.work.domain;

import com.oven.server.common.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@ToString
@NoArgsConstructor
public class Work extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "work_id")
    private Long id;

    private String titleKr;

    private String titleEng;

    private int year;

    private String ageRating;  // 관람연령등급

    private String director;

    private String actor;

    @Column(length = 10000)
    private String summary;

    private String poster;

    @OneToMany(mappedBy = "work")
    private List<WorkProvider> providerList;

    @Embedded
    private Genre genre;

    @Builder
    public Work(String titleKr, String titleEng, int year, String ageRating, String director, String actor, String summary, String poster, Genre genre) {
        this.titleKr = titleKr;
        this.titleEng = titleEng;
        this.year = year;
        this.ageRating = ageRating;
        this.director = director;
        this.actor = actor;
        this.summary = summary;
        this.poster = poster;
        this.genre = genre;
        this.providerList = new ArrayList<>();
    }

}
