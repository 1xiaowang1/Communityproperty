package com.forest.communityproperty.entity;

import lombok.Data;

@Data
public class Forest_currentEntry {
    /**
     * 日常记录ID
     * 日常名称（姓名）
     * 物业ID
     * 业主ID
     * 类型ID
     * 物业
     * 业主
     * 日常类型
     * 备注
     */
    private int currentEntryID;
    private String currentEntryName;
    private int xtYongHuID;
    private int yeZhuID;
    private int styleID;
    private Forest_xitongyonghu user;
    private Forest_yezhumessage message;
    private Forest_currentEntryStyle currentEntryStyle;
    private String currentEntryBeiZhu;
}
