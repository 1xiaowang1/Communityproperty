package com.forest.communityproperty.entity;

import lombok.Data;

@Data
public class Forest_ordermanage {
    /**
     * 收费单ID
     * 收费项ID
     * 	收费状态（
     * 1、已收费
     * 2、未收费
     * ）
     * 收费总价时间
     * 收费年份
     * 收费月份
     * 订单时间
     * 业主ID
     */
    private int orderID;
    private int projectID;
    private int orderState;
    private int orderYear;
    private int orderMonth;
    private String orderDate;
    private double orderPrice;
    private int yeZhuID;
    private Forest_projectmanage manage;
    private Forest_yezhumessage message;
    private int num;
    private int size;
    private String name;
    private double sNum;
}
