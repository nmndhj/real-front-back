package com.example.demo.bookmark.dto;

import com.example.demo.bookmark.domain.Bookmark;
import lombok.Getter;

@Getter
public class BookmarkDto {
    private Long bookmarkId;        //북마크 아이디

    private String userId;          //사용자 아이디

    private String url;             //북마크 url

    private String bookmarkName;    //북마크 이름

    private String description;     //북마크 설명

    private int priority;           //우선순위

    private String regId;           //등록자

    private String updId;           //수정자

    //북마크 생성
    public Bookmark create(){
        return Bookmark.builder()
                .userId(userId)
                .url(url)
                .bookmarkName(bookmarkName)
                .description(description)
                .regId(regId)
                .build();
    }

    //북마크 수정
    public Bookmark update(Bookmark bookmark){
        bookmark.update(userId, url, bookmarkName, description, updId);
        return bookmark;
    }
}
