package com.forest.communityproperty.contoller;

import com.forest.communityproperty.entity.Forest_complaint;
import com.forest.communityproperty.global.Forest_variable;
import com.forest.communityproperty.service.Forest_complaintService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class Forest_complaintController {
    @Autowired
    Forest_complaintService forest_complaintService;
    private int num;
    private Map<String, Object> map = new HashMap<>();

    @RequestMapping("insertComplaint")
    public Map<String, Object> insertComplaint(@RequestBody Forest_complaint model, HttpServletRequest request) {
        //查询业主的编号
        int yeZhuID = new Forest_variable().sessionID(request);
        //增加业主编号
        model.setYeZhuID(yeZhuID);
        //投诉状态
        model.setComState(1);
        //设置时间的格式
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获取时间的方法
        Date d = new Date();
        //将获取的时间转换成设置的时间格式进行存储
        model.setComDate(sf.format(d));
        int num = forest_complaintService.insertComplaint(model);
        if (num == 1) {
            map.put("code", 200);
            return map;
        }
        map.put("code", 500);
        return map;
    }

    @RequestMapping("/selectEmployeeComplaint")
    public Map<String, Object> selectEmployee(@RequestBody Forest_complaint model, HttpServletRequest request, HttpSession session) {
        //判断是否登录
        if (new Forest_variable().variableNameSession(request) == 500) {
            map.put("code", 500);
            return map;
        }
        String nums = String.valueOf(session.getAttribute("num"));
        int tt = Integer.parseInt(nums);
        if (tt != 2 && tt != 3) {
            map.put("code", 500);
            return map;
        }
        //求出统计的数
        num = countUser(model);
        //判断是否是首页
        if (model.getNum() != 0) {
            //若不是首页，则获取起始值
            int ss = model.getNum() * model.getSize();
            //存储起始值
            model.setNum(ss);
        }
        //查询业主的起始页
        List<Forest_complaint> list = forest_complaintService.selectEmployee(model);
        System.out.println(nums);
        //存储物业的登录名
        map.put("name", new Forest_variable().sessionName(request));
        //存储业主的数据
        map.put("user", list);
        //存储页数
        map.put("num", num);
        //状态码 200
        map.put("code", 200);
        return map;
    }

    /**
     * 求出统计的数据
     */
    public int countUser(Forest_complaint model) {
        int num;
        //查询业主的统计数据
        int count = forest_complaintService.findSelectCount();
        //判断求出页数
        if (count % model.getSize() == 0) {
            num = count / model.getSize();
        } else {
            num = count / model.getSize() + 1;
        }
        //如果业主信息大于8页  只显示8页
        if (num >= 8) {
            return 8;
        }
        return num;
    }



    @RequestMapping("updateByPrimaryKeyComplaint")
    public Map<String, Object> updateByPrimaryKeySelective(@RequestBody Forest_complaint model, HttpServletRequest request) {
        //设置时间的格式
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获取时间的方法
        Date d = new Date();
        //将获取的时间转换成设置的时间格式进行存储
        model.setComDate(sf.format(d));
        int num = forest_complaintService.updateByPrimaryKeySelective(model);
        if (num == 1) {
            map.put("code", 200);
            return map;
        }
        map.put("code", 500);
        return map;
    }


    @RequestMapping("/userEmployeeComplaint")
    public Map<String, Object> userEmployeeComplaint(@RequestBody Forest_complaint model, HttpServletRequest request) {
        //求出统计的数
        num = count(model);
        //判断是否是首页
        if (model.getNum() != 0) {
            //若不是首页，则获取起始值
            int ss = model.getNum() * model.getSize();
            //存储起始值
            model.setNum(ss);
        }
        //查询业主的起始页
        List<Forest_complaint> list = forest_complaintService.userEmployeeComplaint(model);
        //存储物业的登录名
        map.put("name", new Forest_variable().sessionName(request));
        //存储业主的数据
        map.put("user", list);
        //存储页数
        map.put("num", num);
        //状态码 200
        map.put("code", 200);
        return map;
    }

    /**
     * 求出统计的数据
     */
    public int count(Forest_complaint model) {
        int num;
        //查询业主的统计数据
        int count = forest_complaintService.findUserCountComplaint(model);
        //判断求出页数
        if (count % model.getSize() == 0) {
            num = count / model.getSize();
        } else {
            num = count / model.getSize() + 1;
        }
        //如果业主信息大于8页  只显示8页
        if (num >= 8) {
            return 8;
        }
        return num;
    }
}
