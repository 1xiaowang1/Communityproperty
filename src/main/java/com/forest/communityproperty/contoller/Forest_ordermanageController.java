package com.forest.communityproperty.contoller;

import com.forest.communityproperty.entity.Forest_ordermanage;
import com.forest.communityproperty.global.Forest_variable;
import com.forest.communityproperty.service.Forest_ordermanageService;
import com.forest.communityproperty.service.Forest_yezhumessageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.Calendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class Forest_ordermanageController {
    private Map<String, Object> map = new HashMap<>();
    private int num, count;
    @Autowired
    Forest_ordermanageService forest_ordermanageService;
    @Autowired
    Forest_yezhumessageService forest_yezhumessageService;

    /**
     * 查询已缴费
     *
     * @return
     */
    @PostMapping("/selectEmployeeState")
    public Map<String, Object> selectEmployeeState(@RequestBody Forest_ordermanage model, HttpServletRequest request) {
        //判断用户是否存在登录了
        if (new Forest_variable().variableNameSession(request) == 500) {
            //状态码  500错误
            map.put("code", 500);
            return map;
        }
        //系统物业人员的账号名称
        map.put("name", new Forest_variable().sessionName(request));
        //求出统计的数据
        int lNum = count(model);
        //判断是否是首页
        if (model.getNum() != 0) {
            //若不是首页，则获取起始值
            int ss = model.getNum() * model.getSize();
            //存储起始值
            model.setNum(ss);
        }
        //查询已缴费信息
        List<Forest_ordermanage> list = forest_ordermanageService.selectEmployeeState(model);
        //存储数据
        //存储已缴费数据
        map.put("order", list);
        //正确返回值为200
        map.put("code", 200);
        //页数
        map.put("lNum", lNum);
        return map;
    }

    /**
     * 查询未缴费
     *
     * @return
     */
    @PostMapping("/selectOrderState")
    public Map<String, Object> selectOrderState(@RequestBody Forest_ordermanage model, HttpServletRequest request) {
        if (new Forest_variable().variableNameSession(request) == 500) {
            //状态码  500错误
            map.put("code", 500);
            System.out.println(111);
            return map;
        }
        //系统物业人员的账号名称
        System.out.println(new Forest_variable().sessionName(request));
        //获取年和月
        Calendar calendar = Calendar.getInstance();
        model.setOrderYear(calendar.get(Calendar.YEAR));
        model.setOrderMonth(calendar.get(calendar.MONTH) + 1);
        //求出统计的数据
        int nums = countOrder(model);
        //判断是否是首页
        if (model.getNum() != 0) {
            //若不是首页，则获取起始值
            int ss = model.getNum() * model.getSize();
            //存储起始值
            model.setNum(ss);
        }
        //查询未缴费信息
        List<Forest_ordermanage> list = forest_ordermanageService.selectOrderState(model);
        //存储数据
        //存储已缴费数据
        map.put("orderList", list);
        //正确返回值为200
        map.put("code", 200);
        //页数
        map.put("nums", nums);
        map.put("names", new Forest_variable().sessionName(request));
        return map;
    }

    /**
     * 求出统计的已缴费数据
     */
    public int count(Forest_ordermanage model) {
        int lNum;
        //查询统计的数据
        int count = forest_ordermanageService.findSelectCount();
        //通过计算判断页数
        if (count % model.getSize() == 0) {
            lNum = count / model.getSize();
        } else {
            lNum = count / model.getSize() + 1;
        }
        //如果大于8时只能返回8
        if (lNum >= 8) {
            return 8;
        }
        return lNum;
    }

    /**
     * 求出统计的未缴费数据
     */
    public int countOrder(Forest_ordermanage model) {
        //查询统计的数据
        count = forest_ordermanageService.findOrderSelectCount(model);
        System.out.println(model.getSize());        //通过计算判断页数
        if (count % model.getSize() == 0) {
            num = count / model.getSize();
        } else {
            num = count / model.getSize() + 1;
        }
        //如果大于8时只能返回8
        if (num >= 8) {
            return 8;
        }
        return num;
    }

    /**
     * 查询未缴费搜索
     *
     * @return
     */
    @PostMapping("/selectByPrimaryOrderName")
    public Map<String, Object> selectByPrimaryKeysName(@RequestBody Forest_ordermanage model) {
        System.out.println(model.getSize());
        //获取年和月
        Calendar calendar = Calendar.getInstance();
        model.setOrderYear(calendar.get(Calendar.YEAR));
        model.setOrderMonth(calendar.get(calendar.MONTH) + 1);
        //求出统计的数据
        int nums = findMessageSelectCount(model);
        //判断是否是首页
        if (model.getNum() != 0) {
            //若不是首页，则获取起始值
            int ss = model.getNum() * model.getSize();
            //存储起始值
            model.setNum(ss);
        }
        //查询未缴费信息
        List<Forest_ordermanage> list = forest_ordermanageService.selectByPrimaryKeysName(model);
        //存储数据
        //存储已缴费数据
        map.put("orderList", list);
        //正确返回值为200
        map.put("code", 200);
        //页数
        map.put("nums", nums);
        return map;
    }


    /**
     * 求出统计的未缴费数据搜索
     */
    public int findMessageSelectCount(Forest_ordermanage model) {
        //查询统计的数据
        count = forest_ordermanageService.findMessageSelectCount(model);
        //通过计算判断页数
        if (count % model.getSize() == 0) {
            num = count / model.getSize();
        } else {
            num = count / model.getSize() + 1;
        }
        //如果大于8时只能返回8
        if (num >= 8) {
            return 8;
        }
        return num;
    }


}
