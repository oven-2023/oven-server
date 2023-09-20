package com.oven.server.api.work.domain;

import jakarta.persistence.Embeddable;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Genre {

    private Boolean action = false;         //    액션
    private Boolean SF = false;             //    SF
    private Boolean fantasy = false;        //    판타지
    private Boolean adventure = false;      //    어드벤처(모험)
    private Boolean criminal = false;       //    범죄
    private Boolean thriller = false;       //    스릴러
    private Boolean mystery = false;        //    미스터리
    private Boolean comedy = false;         //    코미디
    private Boolean romance = false;        //    멜로/로맨스
    private Boolean drama = false;          //    드라마
    private Boolean animation = false;      //    애니메이션
    private Boolean horror = false;         //    공포(호러)
    private Boolean variety = false;        //    예능
    private Boolean documentary = false;    //    다큐멘터리
    private Boolean musical = false;        //    뮤지컬
    private Boolean family = false;         //    가족
    private Boolean western = false;        //    서부극(웨스턴)
    private Boolean war = false;            //    전쟁
    private Boolean performance = false;    //    공연
    private Boolean adult = false;          //    성인
    private Boolean music = false;          //    음악

    public String toString() {
        String result = "";

        if(this.action)
            result += "액션, ";
        if(this.adult)
            result += "성인, ";
        if(this.SF)
            result += "SF, ";
        if(this.adventure)
            result += "어드벤처, ";
        if(this.comedy)
            result += "코미디, ";
        if(this.animation)
            result += "애니메이션, ";
        if(this.criminal)
            result += "범죄, ";
        if(this.documentary)
            result += "다큐멘터리, ";
        if(this.drama)
            result += "드라마, ";
        if(this.family)
            result += "가족, ";
        if(this.fantasy)
            result += "판타지, ";
        if(this.horror)
            result += "호러, ";
        if(this.music)
            result += "음악, ";
        if(this.musical)
            result += "뮤지컬, ";
        if(this.mystery)
            result += "미스터리, ";
        if(this.performance)
            result += "공연, ";
        if(this.thriller)
            result += "스릴러, ";
        if(this.variety)
            result += "예능, ";
        if(this.war)
            result += "전쟁, ";
        if(this.western)
            result += "서부극, ";
        if(this.romance)
            result += "로맨스, ";

        return result;
    }
}
