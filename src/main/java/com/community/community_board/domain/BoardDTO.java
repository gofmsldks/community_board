package com.community.community_board.domain;

import java.time.LocalDateTime;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class BoardDTO {

    /** 번호 (PK) */
    private Long idx;

    /** 제목 */
    private String title;

    /** 내용 */
    private String content;

    /** 작성자 */
    private String writer;

    /** 조회 수 */
    private int viewCnt;

    /** 공지 여부 */
    private String noticeYn;

    /** 비밀 여부 */
    private String secretYn;

    /** 삭제 여부 */
    private String deleteYn;

    /** 등록일 */
    private LocalDateTime insertTime;

    /** 수정일 */
    private LocalDateTime updateTime;

    /** 삭제일 */
    private LocalDateTime deleteTime;


    public void setIdx(Long idx) {
        this.idx = idx;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public void setWriter(String writer) {
        this.writer = writer;
    }

    public void setViewCnt(int viewCnt) {
        this.viewCnt = viewCnt;
    }

    public void setNoticeYn(String noticeYn) {
        this.noticeYn = noticeYn;
    }

    public void setSecretYn(String secretYn) {
        this.secretYn = secretYn;
    }

    public void setDeleteYn(String deleteYn) {
        this.deleteYn = deleteYn;
    }

    public void setInsertTime(LocalDateTime insertTime) {
        this.insertTime = insertTime;
    }

    public void setUpdateTime(LocalDateTime updateTime) {
        this.updateTime = updateTime;
    }

    public void setDeleteTime(LocalDateTime deleteTime) {
        this.deleteTime = deleteTime;
    }

    public Long getIdx() {
        return idx;
    }

    public String getTitle() {
        return title;
    }

    public String getContent() {
        return content;
    }

    public String getWriter() {
        return writer;
    }

    public int getViewCnt() {
        return viewCnt;
    }

    public String getNoticeYn() {
        return noticeYn;
    }

    public String getSecretYn() {
        return secretYn;
    }

    public String getDeleteYn() {
        return deleteYn;
    }

    public LocalDateTime getInsertTime() {
        return insertTime;
    }

    public LocalDateTime getUpdateTime() {
        return updateTime;
    }

    public LocalDateTime getDeleteTime() {
        return deleteTime;
    }
}
