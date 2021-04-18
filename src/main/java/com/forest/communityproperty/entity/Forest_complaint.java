package com.forest.communityproperty.entity;

import lombok.Data;

@Data
public class Forest_complaint {
    private int comID;
    private int yeZhuID;
    private String comTitle;
    private String comContent;
    private String comMatter;
    private int comState;
    private String comDate;
    private int num;
    private int size;
    private String name;
    private Forest_yezhumessage message;
}
