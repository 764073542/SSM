package com.ssm.dto;

import com.ssm.enums.BookBorrowState;
import com.ssm.model.BorrowInfo;

/**
 * Created by Administrator on 2017/4/3.
 */
public class BookBorrowDto {
    //状态信息
    private int state;
    // 状态标识
    private String stateInfo;
    //借阅信息
    private BorrowInfo borrowInfo;

    public BookBorrowDto() {
    }

    public BookBorrowDto(int state, BookBorrowState bookBorrowState, BorrowInfo borrowInfo) {
        this.state = state;
        this.stateInfo = bookBorrowState.getStateInfo();
        this.borrowInfo = borrowInfo;
    }

    public int getState() {
        return state;
    }

    public void setState(int state) {
        this.state = state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public void setStateInfo(String stateInfo) {
        this.stateInfo = stateInfo;
    }

    public BorrowInfo getBorrowInfo() {
        return borrowInfo;
    }

    public void setBorrowInfo(BorrowInfo borrowInfo) {
        this.borrowInfo = borrowInfo;
    }

    @Override
    public String toString() {
        return "BookBorrowDto{" +
                "state=" + state +
                ", stateInfo='" + stateInfo + '\'' +
                ", borrowInfo=" + borrowInfo +
                '}';
    }
}
