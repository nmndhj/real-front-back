package com.example.demo.memo.repository;

import com.example.demo.memo.domain.Memo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface MemoRepository extends JpaRepository<Memo, Long> {
    //사용자, 연도 기준 메모 리스트 조회
    List<Memo> findAllByUserIdAndYear(String userId, int year);

    //사용자 기준 메모 리스트 조회
    List<Memo> findAllByUserId(String userId);

    //사용자, 연도, 주 기준 메모 조회 (단건)
    List<Memo> findAllByUserIdAndYearAndWeek(String userId, int year, String week);

}
