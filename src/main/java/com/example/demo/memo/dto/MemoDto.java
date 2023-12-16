package com.example.demo.memo.dto;

import com.example.demo.memo.domain.Memo;
import lombok.Getter;

@Getter
public class MemoDto {

    private Long id;            //Id

    private String userId;      //사용자 아이디

    private String week;        //주

    private int year;           //연도

    private String contents;    //컨텐츠 (메모내용)

    private String regId;       //등록자

    private String updId;       //수정자

    public Memo create(){
        return Memo.builder()
                .userId(userId)
                .week(week)
                .year(year)
                .contents(contents)
                .regId(regId)
                .build();
    }

    public Memo update(Memo memo){
        memo.update(contents, updId);
        return memo;
    }
}
