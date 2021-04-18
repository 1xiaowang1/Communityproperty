package com.forest.communityproperty.contoller;

import com.forest.communityproperty.entity.Forest_ordermanage;
import com.forest.communityproperty.service.Forest_ordermanageService;
import com.forest.communityproperty.service.Forest_yezhumessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.*;

@RestController
public class Forest_indexController {
    private Map<String, Object> map = new HashMap<>();
    private int num, count;
    @Autowired
    Forest_ordermanageService forest_ordermanageService;
    @Autowired
    Forest_yezhumessageService forest_yezhumessageService;

    @RequestMapping("/selectSumOrderName")
    public Map<String, Object> selectSumOrderName() {
        double sum = 0;
        //单收益
        List<Forest_ordermanage> list = forest_ordermanageService.selectsumOrderKey();
        //总收益
        /* Object object=map.get()*/
        ArrayList<Map<String, Object>> sumList = forest_ordermanageService.selectSumOrderKey();
        for (Map<String, Object> map : sumList) {
            sum = Double.parseDouble(map.get("sum(orderPrice)").toString());
        }
        //统计业主数据
        int count = forest_yezhumessageService.findSelectCount();
        //赋值年月
        Forest_ordermanage model = new Forest_ordermanage();
        Calendar calendar = Calendar.getInstance();
        model.setOrderYear(calendar.get(Calendar.YEAR));
        model.setOrderMonth(calendar.get(calendar.MONTH) + 1);
        //统计未缴费数字
        int list1 = forest_ordermanageService.findOrderSelectCount(model);
        //map传递数字
        map.put("sum", sum);
        map.put("count", count);
        map.put("orderList", list1);
        map.put("price", list.get(0).getOrderPrice());
        map.put("code", 200);
        return map;
    }
}
