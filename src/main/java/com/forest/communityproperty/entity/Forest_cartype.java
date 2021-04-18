package com.forest.communityproperty.entity;

import lombok.Data;

@Data
public class Forest_cartype {
    /**
     * 车位编号
     * 车位名称
     * 车位时间
     * 车位备注
     */
    private int typeID;
    private String cartTypeName;
    private String carTypeDate;
    private String carTypeBeiZhu;
}
