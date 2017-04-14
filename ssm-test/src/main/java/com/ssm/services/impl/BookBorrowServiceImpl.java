package com.ssm.services.impl;

import com.ssm.model.BorrowInfo;
import com.ssm.services.BookBorrowService;

import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/4/3.
 */
public class BookBorrowServiceImpl implements BookBorrowService{
    public List<BorrowInfo> queryAll() {
        return null;
    }

    public List<BorrowInfo> queryByUserName(String userName) {
        return null;
    }

    public List<BorrowInfo> queryByBookName(String bookName) {
        return null;
    }

    public List<BorrowInfo> queryByState(int state) {
        return null;
    }

    public List<BorrowInfo> queryByTime(Date start_date, Date end_date) {
        return null;
    }

    public Long getInfoId(BorrowInfo info) {
        return null;
    }

    public int insertBorrowInfo(BorrowInfo info) {
        return 0;
    }

    public int updateBorrowState(Long id, int state) {
        return 0;
    }

    public int deleteBorrowInfoByUserName(String userName) {
        return 0;
    }
}
