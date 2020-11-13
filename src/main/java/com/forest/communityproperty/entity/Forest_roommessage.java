package com.forest.communityproperty.entity;

import lombok.Data;

@Data
public class Forest_roommessage {
    /**
     * 楼房信息
     * 楼盘ID
     * 楼盘名称
     * 备注
     */
    private  int floorID;
    private String floorName;
    private String floorDate;
    private String floorBeiZhu;
    private  int num;
    private String name;
}
