package com.example.demo.memo.service;

import com.example.demo.common.error.BizException;
import com.example.demo.common.error.ErrorCode;
import com.example.demo.memo.domain.Memo;
import com.example.demo.memo.dto.MemoDto;
import com.example.demo.memo.repository.MemoRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class MemoServiceImpl implements MemoService{

    private final MemoRepository memoRepository;

    //전체 메모 리스트 조회(개발용)
    public List<Memo> getAllMemoList(){
        return memoRepository.findAll();
    }

    /**
     * 사용자 기준 메모 리스트 조회
     * @param userId
     * @return List<Bookmark>
     */
    @Transactional(readOnly = true)
    public List<Memo> getMemoListByUserId(String userId){
        return memoRepository.findAllByUserId(userId);
    }

    /**
     * 사용자, 연도 기준 메모 리스트 조회
     * @param userId
     * @param year
     * @return
     */
    @Transactional(readOnly = true)
    public List<Memo> getMemoListByUserIdAndYear(String userId, int year){
        return memoRepository.findAllByUserIdAndYear(userId, year);
    }

    /**
     * 사용자, 연도, week 기준 메모 단건 조회 (주단위 메모 조회)
     * @param userId
     * @param year
     * @param week
     * @return
     */
    @Transactional(readOnly = true)
    public Memo getMemoByUserIdAndYearAndWeek(String userId, int year, String week){
        List<Memo> memos = memoRepository.findAllByUserIdAndYearAndWeek(userId, year, week);
        return memos.isEmpty() ? null : memos.get(0);
    }

    /**
     * 메모 신규 추가
     * @param memoDto
     */
    public void addMemo(MemoDto memoDto){
        memoRepository.save(memoDto.create());
    }

    /**
     * 메모 수정
     * @param memoDto
     */
    public void updateMemo(MemoDto memoDto){
        Memo memo = memoRepository.findById(memoDto.getId()).orElseThrow(() -> new BizException(ErrorCode.TARGET_DATA_NOT_FOUND));
        memoRepository.save(memoDto.update(memo));
    }

    /**
     * 메모 데이터 삭제
     * @param id
     */
    public void deleteBookmark(Long id){
        memoRepository.deleteById(id);
    }

    /**
     * 메모 중복 체크
     * @return
     */
    public boolean checkDuplicated(String userId, int year, String week){
        List<Memo> memos = memoRepository.findAllByUserIdAndYearAndWeek(userId, year, week);
        return memos.isEmpty() ? false : true;
    }

}
