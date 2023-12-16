package com.example.demo.memo.domain;

import com.example.demo.common.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.*;

@Entity
@Getter
@ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Memo extends BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;            //Id

    private String userId;      //사용자 아이디

    private String week;        //주

    private int year;           //연도

    @Lob
    private String contents;    //컨텐츠 (메모내용)

    private String regId;       //등록자

    private String updId;       //수정자

    @Builder
    private Memo(String userId, String week, int year, String contents, String regId){
        this.userId = userId;
        this.week = week;
        this.year = year;
        this.contents = contents;
        this.regId = regId;
        this.updId = regId;
    }

    public void update(String contents, String updId){
        this.contents = contents;
        this.updId = updId;
    }
}
