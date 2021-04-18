package com.forest.communityproperty.entity;

import lombok.Data;

@Data
public class Forest_carmessage {
    /**
     * 车辆编号
     * 车辆照片
     * 车牌照
     * 车位状态
     * 车位时间
     * 备注
     */
    private int carID;
    private String carImage;
    private String carPlates;
    private int carStates;
    private String catDate;
    private String carBeiZhu;

}
