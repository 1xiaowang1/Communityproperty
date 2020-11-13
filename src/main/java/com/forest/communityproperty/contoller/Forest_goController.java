package com.forest.communityproperty.contoller;

import com.forest.communityproperty.entity.Forest_ordermanage;
import com.forest.communityproperty.entity.ZhiFuMX;
import com.forest.communityproperty.entity.zhifu;
import com.forest.communityproperty.global.Forest_dataTreatingUtils;
import com.forest.communityproperty.service.Forest_ordermanageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.*;

@RestController
public class Forest_goController {
    @Autowired
    Forest_ordermanageService forest_ordermanageService;
    private String uid;
    private Map<String, Object> map = new HashMap<>();
    //存储string类型的数据
    public String[] list = new String[6];
    //存储int类型的数据
    public int[] arr = new int[2];
    //存储金额数据
    public float[] aff = new float[2];
    public String Order_no;
    private String id;

    @RequestMapping("payType")
    public Map<String, Object> goPay(@RequestBody zhifu zhifu, HttpServletRequest request) {
        try {
            System.out.println(Forest_dataTreatingUtils.MC());
        } catch (Exception var15) {
            var15.printStackTrace();
        }
        Forest_ordermanage oreder = new Forest_ordermanage();
        Calendar now = Calendar.getInstance();
        oreder.setOrderYear(now.get(Calendar.YEAR));
        oreder.setOrderMonth(now.get(Calendar.MONTH) + 1);
        //设置时间的格式
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获取时间的方法
        Date d = new Date();
        //将获取的时间转换成设置的时间格式进行存储
        oreder.setOrderDate(sf.format(d));
        //将各项数据进行赋值
        oreder.setYeZhuID(zhifu.getIds());
        oreder.setProjectID(1);
        oreder.setOrderPrice(Double.parseDouble(zhifu.getSum()));
        oreder.setOrderState(1);

        //查询是否已经存在该订单
        List<Forest_ordermanage> list1 = forest_ordermanageService.selectOrderKey(oreder);
        if (list1.size() == 1) {
            map.put("code", 500);
            return map;
        }
        //如果不存在该订单则进行新增订单
        oreder.setOrderState(2);
        int i = forest_ordermanageService.insertSelective(oreder);
        if (i == 1) {//新增成功后，取出订单编号
            List<Forest_ordermanage> lists = forest_ordermanageService.selectOrderKey(oreder);
            id = String.valueOf(lists.get(0).getOrderID());
        }
        System.out.println("开始跳转支付");
        //初始化值  和一些重要参数值
        ZhiFuMX ZF = new ZhiFuMX();
        //获取商户订单号
        ZF.setA1(id);
        //支付名称
        ZF.setA2("亦欢支付");
        //支付类型  43支付宝  44微信
        ZF.setA3(Integer.parseInt(zhifu.getPay_type()));
        //支付金额
        ZF.setA4(Float.parseFloat(zhifu.getMoney()));
        try {
            //进行数据连接
            String PJ = "order_no=" + ZF.getA1() + "&subject=" + ZF.getA2() + "&pay_type=" + ZF.getA3() + "&money=" + ZF.getA4() + "&app_id=" + ZF.getA5() + "&extra=" + ZF.getA6() + "&" + ZF.getA7();
            //进行MD5数据加密
            String a1 = Forest_dataTreatingUtils.MD5(PJ);
            //赋值给与A8
            ZF.setA8(a1);
            //将值进行存储
            list[0] = ZF.getA1();
            list[1] = ZF.getA2();
            arr[0] = ZF.getA3();
            aff[0] = ZF.getA4();
            arr[1] = ZF.getA5();
            list[2] = ZF.getA6();
            list[3] = ZF.getA7();
            list[4] = ZF.getA8();
        } catch (Exception var14) {
            var14.printStackTrace();
        }
        HttpSession session = request.getSession();
        session.setAttribute("order", list[0]);
        //进行验证
        map.put("code", 200);
        map.put("list", list);
        map.put("arr", arr);
        map.put("aff", aff);
        return map;
    }

    //进行支付前数据发送的赋值
    @RequestMapping("payAgo")
    public Map<String, Object> payAgo(@RequestBody zhifu zhifu) {
        map.put("code", 200);
        map.put("list", list);
        map.put("arr", arr);
        map.put("aff", aff);
        return map;
    }



}
