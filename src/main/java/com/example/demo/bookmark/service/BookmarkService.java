package com.example.demo.bookmark.service;


import com.example.demo.bookmark.domain.Bookmark;
import com.example.demo.bookmark.dto.BookmarkDto;

import java.util.List;

public interface BookmarkService {

    //북마크 데이터 전체 조회
    List<Bookmark> getAllBookmarkList();

    //사용자 기준 북마크 리스트 조회
    List<Bookmark> getBookmarkListByUserId(String userId);

    //북마크 신규 추가
    void addBookmark(BookmarkDto bookmarkDto);

    //북마크 데이터 수정
    void updateBookmark(BookmarkDto bookmarkDto);

    //북마크 중복체크
    boolean checkDuplicatedBookmark(String userId, String url);

    //북마크 데이터 삭제
    void deleteBookmark(Long bookmarkId);
}
