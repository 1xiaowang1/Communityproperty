package com.forest.communityproperty.contoller;

import com.forest.communityproperty.entity.*;
import com.forest.communityproperty.global.Forest_variable;
import com.forest.communityproperty.service.Forest_carareaService;
import com.forest.communityproperty.service.Forest_currentEntryService;
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
public class Forest_carareaController {
    //map存储数据
    private Map<String, Object> map = new HashMap<>();
    //使用数据存储房间编号，楼房编号，业主编号
    private int[] arr = new int[3];
    //统计数据的页数和单页数据
    private int count, num;
    //日常统计信息
    public Forest_currentEntry f = new Forest_currentEntry();
    //车辆信息实例化
    public Forest_carmessage forest_carmessage = new Forest_carmessage();
    //车位信息
    @Autowired
    Forest_cartypeController forest_cartypeController;
    //车辆信息
    @Autowired
    Forest_carmessageController forest_carmessageController;
    //车辆信息和车位信息关联表
    @Autowired
    Forest_carareaService forest_carareaService;
    //车辆信息区域
    @Autowired
    Forest_carareatypeController forest_carareatypeController;
    //日常信息的service层
    @Autowired
    private Forest_currentEntryService forest_currentEntryService;

    /**
     * 查询车位区域
     */
    @PostMapping("/cartypeServiceEmployee")
    public Map<String, Object> selectEmployee(@RequestBody Forest_cararea model, HttpServletRequest request) {
        //判断用户是否存在登录了
        if (new Forest_variable().variableNameSession(request) == 500) {
            //状态码  500错误
            map.put("code", 500);
            return map;
        }
        //系统物业人员的账号名称
        map.put("name", new Forest_variable().sessionName(request));
        //求出统计的数据
        num = count(model);
        //判断是否是首页
        if (model.getNum() != 0) {
            //若不是首页，则获取起始值
            int ss = model.getNum() * model.getSize();
            //存储起始值
            model.setNum(ss);
        }
        //查询车位关联信息
        List<Forest_cararea> list2 = forest_carareaService.selectEmployee(model);
        //存储车位关联信息
        map.put("areaList", list2);
        //统计出来的页数
        map.put("num", num);
        //状态码  200正确
        map.put("code", 200);
        return map;
    }

    /**
     * 求出统计的数据
     */
    public int count(Forest_cararea model) {
        //查询统计的数据
        count = forest_carareaService.findSelectCount();
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

    /**
     * 新增信息之前
     *
     * @return
     */
    @PostMapping("/carSelectAgo")
    public Map<String, Object> carSelectAgo() {
        //查询车位信息
        List<Forest_cartype> list = forest_cartypeController.selectEmployee();
        //查询车位区域
        List<Forest_carareatype> list1 = forest_carareatypeController.selectEmployee();
        //使用map存储数据
        //存储车位区域信息
        map.put("typeList", list);
        //存储车位信息
        map.put("areaTypeList", list1);
        //提示信息
        map.put("code", 200);
        return map;
    }

    /**
     * 车辆信息和关联信息登记
     *
     * @param model
     * @return
     */
    @PostMapping("/carInsert")
    public Map<String, Object> carInsert(@RequestBody Forest_cararea model) {
        //查询该车位是否已经被使用
        int lNum = selectCarSelective(model);
        if (lNum == 1) {
            //提示信息
            map.put("code", 400);
            return map;
        }
        /**
         *         车辆信息
         */
        //车辆号码
        forest_carmessage.setCarPlates(model.getMessage().getCarPlates());
        //车位使用状态
        forest_carmessage.setCarStates(model.getMessage().getCarStates());
        //新增车辆信息
        int mNum = forest_carmessageController.insertSelective(forest_carmessage);
        //判断车辆信息是否登记成功
        if (mNum == 0) {
            //提示信息
            map.put("code", 500);
            return map;
        }
        //设置时间的格式
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获取时间的方法
        Date d = new Date();
        //将获取的时间转换成设置的时间格式进行存储
        model.setCarDate(sf.format(d));
        //输入车辆编号
        model.setCarID(mNum);
        //新增关联表信息
        int mm = forest_carareaService.insertSelective(model);
        if (mm == 0) {
            //提示信息
            map.put("code", 500);
            return map;
        }
        //提示信息
        map.put("code", 200);
        return map;
    }

    /**
     * 查询该车位是否已经被使用
     *
     * @return
     */
    public int selectCarSelective(Forest_cararea model) {
        int lNum = 0;
        //查询该车位是否已经被使用的信息
        List<Forest_cararea> list = forest_carareaService.selectCarSelective(model);
        if (list.size() == 1) {
            lNum = 1;
        }
        return lNum;
    }

    /**
     * 删除车辆信息
     *
     * @return
     */
    @PostMapping("/carDelete")
    public Map<String, Object> carDelete(@RequestBody Forest_cararea model, HttpSession session) {
        int carDelete = 0, Car = 0;
        //查询业主编号
        int YuZhuID = forest_carareaService.findSelectCountYeZhuID(model.getCarAreaID());
        //删除车位信息
        carDelete = forest_carareaService.deleteByPrimaryKey(model.getCarAreaID());
        //删除车辆信息
        Car = forest_carmessageController.deleteByPrimaryKey(model.getCarID());
        if (carDelete == 1 && Car == 1) {
            /**
             * 添加操作信息
             * */
            //设置业主编号
            f.setYeZhuID(YuZhuID);
            //设置操作类型
            f.setStyleID(3);
            //获取session中系统管理员姓名name值
            String name = (String) session.getAttribute("name");
            //获取session中系统管理员的编号id值
            int id = (int) session.getAttribute("id");
            //赋值给entity的方法
            f.setCurrentEntryName(name);
            f.setXtYongHuID(id);
            //新增日常信息
            int list = forest_currentEntryService.insertSelectiveS(f);


            //使用map存储数据
            //提示信息
            map.put("code", 200);
            return map;
        }
        //使用map存储数据
        //提示信息
        map.put("code", 500);
        return map;

    }

    /**
     * 新增信息之前
     *
     * @return
     */
    @PostMapping("/carSelectUpdate")
    public Map<String, Object> carSelectUpdate(@RequestBody Forest_cararea model) {
        //查询车位关联信息
        List<Forest_cararea> list2 = forest_carareaService.selectEmployees(model);
        //查询车位信息
        List<Forest_cartype> list = forest_cartypeController.selectEmployee();
        //查询车位区域
        List<Forest_carareatype> list1 = forest_carareatypeController.selectEmployee();
        //使用map存储数据
        //存储车位区域信息
        map.put("typeList", list);
        //存储车位信息
        map.put("areaTypeList", list1);
        //存储车位关联信息
        map.put("areaList", list2);
        //提示信息
        map.put("code", 200);
        return map;
    }

    /**
     * 修改信息
     *
     * @param model
     * @return
     */
    @PostMapping("/carUpdateAfter")
    public Map<String, Object> carUpdateAfter(@RequestBody Forest_cararea model) {
        //车辆的信息
        forest_carmessage.setCarStates(model.getMessage().getCarStates());
        forest_carmessage.setCarPlates(model.getMessage().getCarPlates());
        forest_carmessage.setCarID(model.getCarID());
        //修改车辆信息
        int mNum = forest_carmessageController.updateByPrimaryKeySelective(forest_carmessage);
        //设置时间的格式
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获取时间的方法
        Date d = new Date();
        //将获取的时间转换成设置的时间格式进行存储
        model.setCarDate(sf.format(d));
        //修改车位信息关联表
        int nNum = forest_carareaService.updateByPrimaryKeySelective(model);
        if (mNum == 1 && nNum == 1) {
            //提示信息
            map.put("code", 200);
            return map;
        }
        //提示信息
        map.put("code", 500);
        return map;
    }

    /**
     * 查询车位信息
     */
    @PostMapping("/carTypeEmployee")
    public Map<String, Object> carTypeEmployee(@RequestBody Forest_cararea model) {
        //求出统计的数据
        num = count(model);
        //判断是否是首页
        if (model.getNum() != 0) {
            //若不是首页，则获取起始值
            int ss = model.getNum() * model.getSize();
            //存储起始值
            model.setNum(ss);
        }
        //查询车位关联信息
        List<Forest_cararea> list2 = forest_carareaService.carTypeEmployee(model);
        //存储车位关联信息
        map.put("areaList", list2);
        //统计出来的页数
        map.put("num", num);
        //状态码  200正确
        map.put("code", 200);
        return map;
    }

    /**
     * 查询车位区域
     */
    @PostMapping("/carAreaTypeEmployee")
    public Map<String, Object> carAreaTypeEmployee(@RequestBody Forest_cararea model) {
        //求出统计的数据
        num = count(model);
        //判断是否是首页
        if (model.getNum() != 0) {
            //若不是首页，则获取起始值
            int ss = model.getNum() * model.getSize();
            //存储起始值
            model.setNum(ss);
        }
        //查询车位关联信息
        List<Forest_cararea> list2 = forest_carareaService.carAreaTypeEmployee(model);
        //存储车位关联信息
        map.put("areaList", list2);
        //统计出来的页数
        map.put("num", num);
        //状态码  200正确
        map.put("code", 200);
        return map;
    }

    /**
     * 搜素
     *
     * @param model
     * @return
     */
    @PostMapping("/floorSelectByPrimaryKeysName")
    public Map<String, Object> floorSelectByPrimaryKeysName(@RequestBody Forest_cararea model) {
        //求出统计的数据
        num = findSelectCountEnter(model);
        //判断是否是首页
        if (model.getNum() != 0) {
            //若不是首页，则获取起始值
            int ss = model.getNum() * model.getSize();
            //存储起始值
            model.setNum(ss);
        }
        //查询车位关联信息
        List<Forest_cararea> list2 = forest_carareaService.floorSelectByPrimaryKeysName(model);
        //存储车位关联信息
        map.put("areaList", list2);
        //统计出来的页数
        map.put("num", num);
        //状态码  200正确
        map.put("code", 200);
        return map;
    }

    /**
     * 查询统计的数据
     *
     * @param model
     * @return
     */
    public int findSelectCountEnter(Forest_cararea model) {
        //查询统计的数据
        count = forest_carareaService.findSelectCountEnter(model);
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

    /**
     * 出去状态
     *
     * @param model
     * @return
     */
    @PostMapping("/goComeEmployee")
    public Map<String, Object> goComeEmployee(@RequestBody Forest_cararea model) {
        List<Forest_cararea> findCountEnter = forest_carareaService.findCountEnter(model);
        if (findCountEnter.size() == 1) {
            map.put("code", 500);
            return map;
        }
        int goComeEmployee = forest_carareaService.goComeEmployee(model);
        if (goComeEmployee == 1) {
            map.put("code", 200);
            return map;
        }
        map.put("code", 400);
        return map;
    }
}
