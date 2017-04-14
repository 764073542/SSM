package com.ssm.dao;

import com.ssm.model.Book;

public interface BookMapper {
    int insert(Book record);

    int insertSelective(Book record);
}