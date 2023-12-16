package com.example.demo.bookmark.domain;

import com.example.demo.common.entity.BaseEntity;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.*;

@Entity
@Getter @ToString
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Bookmark extends BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long bookmarkId;        //북마크 아이디

    private String userId;          //사용자 아이디

    private String url;             //북마크 url

    private String bookmarkName;    //북마크 이름

    private String description;     //북마크 설명

    private int priority;           //우선순위

    private String regId;           //등록자

    private String updId;           //수정자

    @Builder
    public Bookmark(String userId, String url, String bookmarkName, String description, String regId){
        this.userId = userId;
        this.url = url;
        this.bookmarkName = bookmarkName;
        this.description = description;
        this.updId = regId;
        this.regId = regId;
        this.priority = -1;
    }

    public void update(String userId, String url, String bookmarkName, String description, String updId){
        this.userId = userId;
        this.url = url;
        this.bookmarkName = bookmarkName;
        this.description = description;
        this.updId = updId;
    }
}
