package com.forest.communityproperty.entity;

import lombok.Data;

@Data
public class Forest_repairrequest {
    /**
     * 维修ID
     * 维修图片
     * 业主ID
     * 申请时间
     * 维修项目
     * 维修状态
     * (0 未处理
     * 1 正在处理
     * 2 处理完成)
     * 维修详情
     */
    private int repairID;
    private String repairImage;
    private int yeZhuID;
    private String shenQingShiJian;
    private String repairMessage;
    private int repairState;
    private String repairDetail;
    private int num;
    private int size;
    private String name;
    private Forest_yezhumessage message;
}
