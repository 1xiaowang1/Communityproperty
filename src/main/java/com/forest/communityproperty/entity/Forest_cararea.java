package com.forest.communityproperty.entity;

import lombok.Data;

@Data
public class Forest_cararea {
    /**
     * 车辆关联编号
     * 车位编号
     * 车位区域编号
     * 业主编号
     * 车位时间
     */
    private int carAreaID;
    private int carID;
    private int typeID;
    private int yeZhuID;
    private int areaTypeID;
    private String carDate;
    private int num;//起始项
    private int size;//长度
    private Forest_cartype type;//车位信息
    private Forest_carmessage message;//车辆信息
    private Forest_yezhumessage yeZhuMessage;//业主信息
    private Forest_carareatype areaType;//车位区域
    private String name;
    private int recordStates;
}
