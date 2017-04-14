package com.ssm.enums;

/**
 * 借阅状态{1：正常状态;0：即将到期;-1:超期;-2：归还状态}
 */
public enum BookBorrowState {
    BORROWING(1, "正常借阅状态"), EXPIRING(0, "即将到期"),
    OVERDUE(-1, "超期归还"), RETURNED(-2, "图书已归还");

    private int state;

    private String stateInfo;

    private BookBorrowState(int state, String stateInfo) {
        this.state = state;
        this.stateInfo = stateInfo;
    }

    public int getState() {
        return state;
    }

    public String getStateInfo() {
        return stateInfo;
    }

    public static BookBorrowState stateof(int index) {
        for (BookBorrowState startEnum : values()) {
            if (startEnum.getState() == index) {
                return startEnum;
            }
        }
        return null;
    }
}
