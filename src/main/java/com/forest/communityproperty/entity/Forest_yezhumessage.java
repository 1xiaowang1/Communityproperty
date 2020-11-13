package com.forest.communityproperty.entity;

import lombok.Data;

@Data
public class Forest_yezhumessage {
    /**
     * 业主编号
     * 业主姓名
     * 业主性别
     * 业主电话
     * 业主身份证
     * 业主邮箱
     * 业主密码
     * 业主登记时间
     * 业主登录名
     * 业主姓名
     * 起始页
     * 长度
     * 性别
     */
    private int yeZhuID;//业主编号
    private String yeZhuName;   //业主姓名
    private String yeZhuSex;
    private String yeZhuPhone;
    private String yeZhuSFZ;
    private String yeZhuEmail;
    private String yeZhuPassword;
    private String yeZhuTime;
    private String yeZhuUserName;
    private String name;
    private int num;//起始项
    private int size;//长度
    private String sex;
    private String emailNum;

}
