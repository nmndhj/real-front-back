package com.example.demo.bookmark.repository;

import com.example.demo.bookmark.domain.Bookmark;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface BookmarkRepository extends JpaRepository<Bookmark, Long> {
    //사용자 아이디 기준 북마크 리스트 조회
    List<Bookmark> findBookmarkByUserId(String userId);

    //사용자 아이디, url 기준 북마크 리스트 조회
    List<Bookmark> findBookmarkByUserIdAndUrl(String userId, String url);

    //사용자 아이디, url 기준 중복 체크
    List<Bookmark> findBookmarkByUrlAndBookmarkIdNot(String url, Long bookmarkId);
}
