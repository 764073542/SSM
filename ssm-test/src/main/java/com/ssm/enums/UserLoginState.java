package com.ssm.enums;

/**
 * Created by Administrator on 2017/4/4.
 */
public enum UserLoginState {
    SUCCESS(1, "login success"),
    NO_USER(0, "user does not exist,please to register"),
    PASSWORD_INCORRECT(-1, "Incorrect user password,please try again"),
    INPUT_ILLEGAL(-2,"illegal input");
    private int state;

    private String stateInfo;

    private UserLoginState(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static UserLoginState stateof(int index) {
        for (UserLoginState loginEnum : values()) {
            if (loginEnum.getState() == index) {
                return loginEnum;
            }
        }
        return null;
    }
}
