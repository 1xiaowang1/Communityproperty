package com.forest.communityproperty.entity;

import lombok.Data;

@Data
public class Forest_projectmanage {
    /**
     * 收费项编号
     * 收费项名称
     * 收费价格
     * 收费项时间
     */
    private int projectID;
    private String projectName;
    private double projectPrice;
    private String projectDate;
}
