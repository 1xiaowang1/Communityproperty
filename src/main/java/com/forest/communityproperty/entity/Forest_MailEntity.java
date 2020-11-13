package com.forest.communityproperty.entity;

import lombok.Data;

@Data
public class Forest_MailEntity {
    private String from;
    private String to;
    private String cc;
    private String subject;
    private String content;
    private String name;
    private int id;

}
