package com.example.demo.memo.controller;

import com.example.demo.memo.domain.Memo;
import com.example.demo.memo.dto.MemoDto;
import com.example.demo.memo.service.MemoService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/memo")
public class MemoController {

    private final MemoService memoService;

    //메모 리스트 전체 조회
    @GetMapping("/all")
    public ResponseEntity<List<Memo>> getAllMemoList(){
        return new ResponseEntity<>(memoService.getAllMemoList(), HttpStatus.OK);
    }

    //사용자 아이디 기준 메모 리스트 조회
    @GetMapping("/{userId}")
    public ResponseEntity<List<Memo>> getMemoListByUserId(@PathVariable String userId){
        return new ResponseEntity<>(memoService.getMemoListByUserId(userId), HttpStatus.OK);
    }

    //사용자, 연도 기준 메모 리스트 조회
    @GetMapping("/{userId}/{year}")
    public ResponseEntity<List<Memo>> getMemoListByUserId(@PathVariable String userId, @PathVariable int year){
        return new ResponseEntity<>(memoService.getMemoListByUserIdAndYear(userId, year), HttpStatus.OK);
    }

    //사용자, 연도, week 기준 메모 단건 조회 (주단위 메모 조회)
    @GetMapping("/{userId}/{year}/{week}")
    public ResponseEntity<Memo> getMemoListByUserIdAndYear(@PathVariable String userId, @PathVariable int year, @PathVariable String week){
        return new ResponseEntity<>(memoService.getMemoByUserIdAndYearAndWeek(userId, year, week), HttpStatus.OK);
    }

    //메모 신규 추가
    @PostMapping
    public ResponseEntity<Object> addBookmark(@RequestBody MemoDto memoDto){
        memoService.addMemo(memoDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //메모 데이터 수정
    @PutMapping
    public ResponseEntity<Object> updateBookmark(@RequestBody MemoDto memoDto){
        memoService.updateMemo(memoDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
