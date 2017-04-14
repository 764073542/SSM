package com.ssm.exception;

/**
 * Created by Administrator on 2017/4/3.
 */
public class RepeatBorrowing extends BookBorrowException{
    public RepeatBorrowing(String message) {
        super(message);
    }

    public RepeatBorrowing(String message, Throwable cause) {
        super(message, cause);
    }
}
