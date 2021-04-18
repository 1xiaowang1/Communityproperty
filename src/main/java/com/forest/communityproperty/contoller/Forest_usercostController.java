package com.forest.communityproperty.contoller;

import com.forest.communityproperty.entity.Forest_usercost;
import com.forest.communityproperty.global.Forest_variable;
import com.forest.communityproperty.service.Forest_usercostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.text.DecimalFormat;
import java.util.*;

/**
 * 前端缴费
 */
@RestController
public class Forest_usercostController {
    private Map<String, Object> map = new HashMap<>();
    @Autowired
    Forest_usercostService forest_usercostService;
    //随机一个费用度数
    private int number;
    //单价
    private double s;
    //总价的计算
    private double sumNum = 0;
    //存储每项费用的总价
    private String sum[] = new String[3];
    //存储每项度数
    private int usercost[] = new int[3];
    //限制时间的限制
    private DecimalFormat df = new DecimalFormat("######0.00");

    /**
     * 缴费前进入
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("userCostIndex")
    public Map<String, Object> carDelete(@RequestBody Forest_usercost model, HttpServletRequest request) {
        //判断用户是否存在登录了
        if (new Forest_variable().variableYeZhuNameSession(request) == 500) {
            //状态码  500错误
            map.put("code", 500);
            return map;
        }
        //业主人员的账号名称
        map.put("username", new Forest_variable().variableYeZhuNameSession(request));
        //查询业主的编号
        int yeZhuID = new Forest_variable().sessionID(request);
        //连续三项数据
        for (int i = 0; i < 3; i++) {
            //获取Java的时间
            Calendar now = Calendar.getInstance();
            //存储年份
            model.setUserCostYear(now.get(Calendar.YEAR));
            //存储月份
            model.setUserCostMonth(now.get(Calendar.MONTH) + 1);
            //存储业主编号
            model.setYeZhuID(yeZhuID);
            //存储对应的三种不同费用类型
            model.setProjectID(i + 1);
            //查询本月的费用是否存储
            List<Forest_usercost> list = forest_usercostService.selectByPrimaryKey(model);
            //如果存储则进行直接取值
            if (list.size() == 1) {
                //取出对应的度数
                usercost[i] = list.get(0).getUserCostName();
            } else {
                //否则将数据往数据库存储
                //随机数
                Random random = new Random();
                // random得到的是一个double型的值
                number = random.nextInt(950) + 50;
                //新增数据的度数
                model.setUserCostName(number);
                //新增本月的费用信息
                int num = forest_usercostService.insertSelective(model);
                //将本月的度数进行存储
                usercost[i] = number;
            }
            //进行计算本月的的各项费用
            if (i == 0) {
                //电费
                s = 0.5;
            } else if (i == 1) {
                //水费
                s = 0.6;
            } else {
                //服务费
                s = 1.2;
            }
            //计算每个月的各项费用的价格
            sum[i] = df.format(usercost[i] * s + 12);
            //计算本月一共需要支付的价钱
            sumNum = 0;
            sumNum += Double.parseDouble(sum[i]);
        }
        //进行double类型的数据限制取值
        String sumNums = df.format(sumNum);
        //使用map存储数据
        //提示信息
        //成功返回200
        map.put("code", 200);
        //每个月的各项费用的价格
        map.put("sum", sum);
        //本月的度数进行存储
        map.put("user", usercost);
        //业主编号
        map.put("yeZhuID", yeZhuID);
        //总价钱
        map.put("sumNum", sumNums);
        return map;
    }

}
