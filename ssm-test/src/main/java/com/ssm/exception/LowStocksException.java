package com.ssm.exception;

/**
 * Created by Administrator on 2017/4/3.
 */
public class LowStocksException extends BookBorrowException {
    public LowStocksException(String message) {
        super(message);
    }

    public LowStocksException(String message, Throwable cause) {
        super(message, cause);
    }
}
