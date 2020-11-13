package com.forest.communityproperty.entity;

import lombok.Data;

@Data
public class Forest_carareatype {
    /**
     * 车位区域编号
     * 车位区域名称
     * 车位区域时间
     * 车位区域备注
     */
    private int carAreaTypeID;
    private String carAreaTypeName;
    private String carAreaTypeDate;
    private String carAreaTypeBeiZhu;
}
