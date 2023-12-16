package com.example.demo.memo.service;

import com.example.demo.memo.domain.Memo;
import com.example.demo.memo.dto.MemoDto;

import java.util.List;

public interface MemoService {
    //전체 메모 리스트 조회(개발용)
    List<Memo> getAllMemoList();

    //사용자 기준 메모 리스트 조회
    List<Memo> getMemoListByUserId(String userId);

    //사용자, 연도 기준 메모 리스트 조회
    List<Memo> getMemoListByUserIdAndYear(String userId, int year);

    //사용자, 연도, week 기준 메모 단건 조회 (주단위 메모 조회)
    Memo getMemoByUserIdAndYearAndWeek(String userId, int year, String week);

    //메모 신규 추가
    void addMemo(MemoDto memoDto);

    //메모 수정
    void updateMemo(MemoDto memoDto);

    //메모 데이터 삭제
    void deleteBookmark(Long id);

    //메모 중복 체크
    boolean checkDuplicated(String userId, int year, String week);
}
