package com.ssm.exception;

/**
 * Created by Administrator on 2017/4/3.
 */
public class BookBorrowException extends RuntimeException {

    public BookBorrowException(String message) {
        super(message);
    }

    public BookBorrowException(String message, Throwable cause) {
        super(message, cause);
    }
}
