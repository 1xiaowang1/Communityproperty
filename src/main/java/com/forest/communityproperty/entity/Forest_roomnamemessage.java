package com.forest.communityproperty.entity;

import lombok.Data;

@Data
public class Forest_roomnamemessage {
    /**
     * 关联编号
     * 业主
     * 房间
     * 楼房
     * 业主编号
     * 房间编号
     * 楼房编号
     * 登记时间
     * 房间状态
     *
     * 起始列数
     * 长度
     */
    private int roomNameID;
    private Forest_yezhumessage message;
    private Forest_roomname room;
    private Forest_roommessage floor;
    private int yeZhuID;
    private int roomID;
    private int floorID;
    private String roomNameDate;
    private int roomState;
    private String name;
    private int num;//起始项
    private int size;//长度
}
