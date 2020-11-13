package com.forest.communityproperty.entity;

import lombok.Data;

@Data
public class zhifu {
    /**
     *支付类型
     * 交易金额
     *商户订单号
     *
     * 商品名称
     *
     * app_id
     *
     * 签名
     */
    private String pay_type;
    private String money;
    private int ids;
    //private String order_n;
    private String  subject;
    private String result;
    private String app_id;
    private String sign;
    private String sum;
    private String order_no;
}
