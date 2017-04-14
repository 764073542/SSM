package com.ssm.dto;

/**
 * Created by Administrator on 2017/4/4.
 */
public class UserLoginResult<T> extends BaseResult<T> {

    public UserLoginResult(boolean success, T data) {
        super(success, data);
    }

    public UserLoginResult(boolean success, String errorMsg) {
        super(success, errorMsg);
    }


}
