package com.ssm.dto;

/**
 * Created by Administrator on 2017/4/4.
 */
public abstract class BaseResult<T> {
    private boolean isSuccess;
    private T data;
    private String errorMsg;

    public BaseResult(boolean success, T data) {
        this.isSuccess = success;
        this.data = data;
    }

    public BaseResult(boolean success, String errorMsg) {
        this.isSuccess = success;
        this.errorMsg = errorMsg;
    }

    public boolean isSuccess() {
        return isSuccess;
    }

    public void setSuccess(boolean success) {
        this.isSuccess = success;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public String getMsg() {
        return errorMsg;
    }

    public void setMsg(String errorMsg) {
        this.errorMsg = errorMsg;
    }

    @Override
    public String toString() {
        return "UserLoginResult{" +
                "success=" + isSuccess +
                ", data=" + data +
                ", errorMsg='" + errorMsg + '\'' +
                '}';
    }
}
