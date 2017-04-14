package com.ssm.enums;

/**
 * Created by Administrator on 2017/4/2.
 */
public enum BookDBOrderEnums {
    DESC(1, "DESC"), ASC(2, "ASC");

    private int order;

    private String orderInfo;

    private BookDBOrderEnums(int order, String orderInfo) {
        this.order = order;
        this.orderInfo = orderInfo;
    }

    public int getOrder() {
        return order;
    }

    public String getOrderInfo() {
        return orderInfo;
    }

    public static BookDBOrderEnums stateof(int index) {
        for (BookDBOrderEnums order : values()) {
            if (order.getOrder() == index) {
                return order;
            }
        }
        return null;
    }
}