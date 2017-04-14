package com.ssm.model;

import com.ssm.enums.BookBorrowState;

import java.util.Date;

public class BorrowInfo {
    private String bookName;

    private String userName;

    private Date startTime;

    private Date endTime;

    private Integer state;

    public BorrowInfo() {
    }

    public BorrowInfo(String userName, String bookName, Date startTime, Date endTime, BookBorrowState bookBorrowState) {
        this.bookName = bookName;
        this.userName = userName;
        this.startTime = startTime;
        this.endTime = endTime;
        this.state = bookBorrowState.getState();
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName == null ? null : bookName.trim();
    }

    public Integer getState() {
        return state;
    }

    public void setState(Integer state) {
        this.state = state;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName == null ? null : userName.trim();
    }

    public Date getStartTime() {
        return startTime;
    }

    public void setStartTime(Date startTime) {
        this.startTime = startTime;
    }

    public Date getEndTime() {
        return endTime;
    }

    public void setEndTime(Date endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "BorrowInfo{" +
                "bookName='" + bookName + '\'' +
                ", userName='" + userName + '\'' +
                ", startTime=" + startTime +
                ", endTime=" + endTime +
                ", state=" + state +
                '}';
    }
}