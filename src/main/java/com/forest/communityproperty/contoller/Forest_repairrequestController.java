package com.forest.communityproperty.contoller;

import com.forest.communityproperty.entity.Forest_currentEntry;
import com.forest.communityproperty.entity.Forest_repairrequest;
import com.forest.communityproperty.global.Forest_variable;
import com.forest.communityproperty.service.Forest_currentEntryService;
import com.forest.communityproperty.service.Forest_repairrequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class Forest_repairrequestController {
    private Map<String, Object> map = new HashMap<>();
    //设置日常信息
    public Forest_currentEntry f = new Forest_currentEntry();
    @Autowired
    Forest_repairrequestService forest_repairrequestService;
    //日常信息service层映射
    @Autowired
    private Forest_currentEntryService forest_currentEntryService;

    /**
     * 查询维修记录信息
     *
     * @param model
     * @return
     */
    @PostMapping("/repairSelectEmployee")
    public Map<String, Object> selectEmployee(@RequestBody Forest_repairrequest model, HttpServletRequest request) {
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
        //查询维修记录信息
        List<Forest_repairrequest> list = forest_repairrequestService.selectEmployee(model);
        //存储已缴费数据
        map.put("repair", list);
        //正确返回值为200
        map.put("code", 200);
        //页数
        map.put("num", lNum);
        return map;
    }

    /**
     * 求出统计维修记录信息
     */
    public int count(Forest_repairrequest model) {
        int lNum;
        //查询统计的数据
        int count = forest_repairrequestService.findSelectCount();
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
     * 修改维修记录信息
     *
     * @param model
     * @return
     */
    @PostMapping("/repairUpdatePrimarySelective")
    public Map<String, Object> updateByPrimaryKeySelective(@RequestBody Forest_repairrequest model,HttpSession session) {
        //设置时间的格式
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获取时间的方法
        Date d = new Date();
        //将获取的时间转换成设置的时间格式进行存储
        model.setShenQingShiJian(sf.format(d));
        //修改维修状态
        int rNum = forest_repairrequestService.updateByPrimaryKeySelective(model);
        //判断维修是否成功
        if (rNum == 1) {
            //存储业主编号
            f.setYeZhuID(model.getYeZhuID());
            //设置日常类型
            f.setStyleID(3);
            //获取登录名
            String name = (String) session.getAttribute("name");
            //获取登录编号
            int id = (int) session.getAttribute("id");
            //设置登录名
            f.setCurrentEntryName(name);
            //设置登录编号
            f.setXtYongHuID(id);
            //新增日常信息
            int list = forest_currentEntryService.insertSelectiveS(f);
            map.put("code", 200);
            return map;
        }
        map.put("code", 500);
        return map;
    }


    /**
     * 查询维修记录信息
     *
     * @param model
     * @return
     */
    @PostMapping("/repairSouSuoSelectEmployee")
    public Map<String, Object> selectSouSuoEmployee(@RequestBody Forest_repairrequest model, HttpServletRequest request) {
        //求出统计的数据
        int lNum = counts(model);
        //判断是否是首页
        if (model.getNum() != 0) {
            //若不是首页，则获取起始值
            int ss = model.getNum() * model.getSize();
            //存储起始值
            model.setNum(ss);
        }
        //查询维修记录信息
        List<Forest_repairrequest> list = forest_repairrequestService.selectRepairEmployee(model);
        //存储已缴费数据
        map.put("repair", list);
        //正确返回值为200
        map.put("code", 200);
        //页数
        map.put("num", lNum);
        return map;
    }

    /**
     * 求出统计维修记录信息
     */
    public int counts(Forest_repairrequest model) {
        int lNum;
        //查询统计的数据
        int count = forest_repairrequestService.findRepairSelectCount();
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
     * 前台—查询维修记录信息
     *
     * @param model
     * @return
     */
    @PostMapping("/updateSelective")
    public Map<String, Object> updateSelective(@RequestBody Forest_repairrequest model, HttpServletRequest request) {
        //设置时间的格式
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获取时间的方法
        Date d = new Date();
        //将获取的时间转换成设置的时间格式进行存储
        model.setShenQingShiJian(sf.format(d));
         int list = forest_repairrequestService.updateSelective(model);
        //存储已缴费数据
        map.put("repair", list);
        //正确返回值为200
        map.put("code", 200);
        return map;
    }
    /**
     * 前台—登录
     *
     * @param model
     * @return
     */
    @PostMapping("/Selective")
    public Map<String, Object> Selective(@RequestBody Forest_repairrequest model, HttpServletRequest request) {
        //判断用户是否存在登录了
        if (new Forest_variable().variableYeZhuNameSession(request) == 500) {
            //状态码  500错误
            map.put("code", 500);
            return map;
        }
        //正确返回值为200
        map.put("code", 200);
        return map;
    }

}
