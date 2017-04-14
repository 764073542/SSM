package com.ssm.dao;

import com.ssm.model.BorrowInfo;
import com.ssm.model.BorrowInfoKey;

public interface BorrowInfoMapper {
    int deleteByPrimaryKey(BorrowInfoKey key);

    int insert(BorrowInfo record);

    int insertSelective(BorrowInfo record);

    BorrowInfo selectByPrimaryKey(BorrowInfoKey key);

    int updateByPrimaryKeySelective(BorrowInfo record);

    int updateByPrimaryKey(BorrowInfo record);
}