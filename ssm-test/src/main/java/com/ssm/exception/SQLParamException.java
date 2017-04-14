package com.ssm.exception;

/**
 * Created by Administrator on 2017/4/3.
 */
public class SQLParamException extends BookBorrowException {
    public SQLParamException(String message) {
        super(message);
    }

    public SQLParamException(String message, Throwable cause) {
        super(message, cause);
    }
}
