package com.example.demo.bookmark.service;

import com.example.demo.bookmark.domain.Bookmark;
import com.example.demo.bookmark.dto.BookmarkDto;
import com.example.demo.bookmark.repository.BookmarkRepository;
import com.example.demo.common.error.BizException;
import com.example.demo.common.error.ErrorCode;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@RequiredArgsConstructor
public class BookmarkServiceImpl implements BookmarkService{

    private final BookmarkRepository bookmarkRepository;

    public List<Bookmark> getAllBookmarkList(){
        return bookmarkRepository.findAll();
    }

    /**
     * 사용자 기준 북마크 리스트 조회
     * @param userId
     * @return List<Bookmark>
     */
    @Transactional(readOnly = true)
    public List<Bookmark> getBookmarkListByUserId(String userId){
        return bookmarkRepository.findBookmarkByUserId(userId);
    }

    /**
     * 북마크 신규 추가
     * @param bookmarkDto
     */
    public void addBookmark(BookmarkDto bookmarkDto){
        if(checkDuplicatedBookmark(bookmarkDto.getUserId(), bookmarkDto.getUrl())){
            throw new BizException(ErrorCode.DUPLICATE_RESOURCE);
        }
        Bookmark bookmark = bookmarkDto.create();
        System.out.println(bookmark.toString());
        bookmarkRepository.save(bookmark);
    }

    /**
     * 북마크 데이터 수정
     * @param bookmarkDto
     */
    public void updateBookmark(BookmarkDto bookmarkDto){
        //수정하고자 하는 데이터가 없을 경우, 에러 발생
        Bookmark bookmark = bookmarkRepository.findById(bookmarkDto.getBookmarkId()).orElseThrow(() -> new BizException(ErrorCode.TARGET_DATA_NOT_FOUND));
        //데이터 수정
        bookmarkRepository.save(bookmarkDto.update(bookmark));
    }

    /**
     * 북마크 데이터 삭제
     * @param bookmarkId
     */
    public void deleteBookmark(Long bookmarkId){
        bookmarkRepository.deleteById(bookmarkId);
    }


    /**
     * 북마크 중복체크
     * @param userId
     * @param url
     * @return 중복일 경우 false
     */
    @Transactional(readOnly = true)
    public boolean checkDuplicatedBookmark(String userId, String url){
        return !bookmarkRepository.findBookmarkByUserIdAndUrl(userId, url).isEmpty();
    }
}
