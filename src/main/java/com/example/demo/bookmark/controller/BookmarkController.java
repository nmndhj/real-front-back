package com.example.demo.bookmark.controller;

import com.example.demo.bookmark.domain.Bookmark;
import com.example.demo.bookmark.dto.BookmarkDto;
import com.example.demo.bookmark.service.BookmarkService;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/bookmark")
public class BookmarkController {

    private final BookmarkService bookmarkService;

    //북마크 전체 리스트 조회 (개발용)
    @GetMapping("/all")
    public ResponseEntity<List<Bookmark>> getAllBookmark(){
        return new ResponseEntity<>(bookmarkService.getAllBookmarkList(), HttpStatus.OK);
    }

    //사용자 기준 북마크 리스트 조회
    @GetMapping("/{userId}")
    public ResponseEntity<List<Bookmark>> getAllBookmarkByUserId(@PathVariable String userId){
        return new ResponseEntity<>(bookmarkService.getBookmarkListByUserId(userId), HttpStatus.OK);
    }

    //북마크 신규 추가
    @PostMapping
    public ResponseEntity<Object> addBookmark(@RequestBody BookmarkDto bookmarkDto){
        bookmarkService.addBookmark(bookmarkDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //북마크 데이터 수정
    @PutMapping
    public ResponseEntity<Object> updateBookmark(@RequestBody BookmarkDto bookmarkDto){
        bookmarkService.updateBookmark(bookmarkDto);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //북마크 데이터 삭제
    @DeleteMapping("/{bookmarkId}")
    public ResponseEntity<Object> deleteBookmark(@PathVariable Long bookmarkId) {
        bookmarkService.deleteBookmark(bookmarkId);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    //북마크 중복체크
    @GetMapping("/{userId}/{url}")
    public ResponseEntity<Object> checkDuplicatedBookmark(@PathVariable String userId, @PathVariable String url){
        return new ResponseEntity<>(bookmarkService.checkDuplicatedBookmark(userId, url), HttpStatus.OK);
    }

}
