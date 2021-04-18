package com.forest.communityproperty.contoller;

import com.forest.communityproperty.entity.*;
import com.forest.communityproperty.global.Forest_variable;
import com.forest.communityproperty.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class Forest_roomnamemessageController {
    //map存储数据
    private Map<String, Object> map = new HashMap<>();
    //使用数据存储房间编号，楼房编号，业主编号
    private int[] arr = new int[3];
    //统计数据的页数和单页数据
    private int count, num;
    //日常统计信息
    public Forest_currentEntry f = new Forest_currentEntry();
    /**
     * 系统管理业务层
     */
    //楼房与房间的关联service层
    @Autowired
    private Forest_roomnamemessageService forest_roomnamemessageService;
    //日常信息的service层
    @Autowired
    private Forest_currentEntryService forest_currentEntryService;
    //房间的service层
    @Autowired
    private Forest_roomnameService forest_roomnameService;
    //楼房的service层
    @Autowired
    private Forest_roommessageService forest_roommessageService;

    /**
     * floorSelect
     * 首次加载页面
     *
     * @RequestBody Forest_roomnamemessage model, HttpServletRequest request
     */
    @RequestMapping("/floorSelect")
    public Map<String, Object> insertSelective(@RequestBody Forest_roomnamemessage model, HttpServletRequest request) {
        //判断用户是否存在登录了
        if (new Forest_variable().variableNameSession(request) == 500) {
            //状态码  500错误
            map.put("code", 500);
            return map;
        }
        //求出统计的数据
        num = count(model);
        //查询的业主、房间、楼房、关联四表的数据
        List<Forest_roomnamemessage> list = forest_roomnamemessageService.selectEmployee(model);
        //系统物业人员的账号名称
        map.put("name", new Forest_variable().sessionName(request));
        //存储的业主、房间、楼房、关联四表的数据
        map.put("user", list);
        //统计出来的页数
        map.put("num", num);
        //状态码  200正确
        map.put("code", 200);
        return map;
    }

    /**
     * 求出统计的数据
     */
    public int count(Forest_roomnamemessage model) {
        //查询统计的数据
        count = forest_roomnamemessageService.findSelectCount();
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
     * yeZhuSelectFenYe
     * 分页查询
     */
    @RequestMapping("/floorSelectFenYe")
    public Map<String, Object> yeZhuSelectFenYe(@RequestBody Forest_roomnamemessage model, HttpServletRequest request) {
        //求出统计的数据
        num = count(model);
        //计算起始的值
        int ss = model.getNum() * model.getSize();
        model.setNum(ss);
        //查询的业主、房间、楼房、关联四表的数据
        List<Forest_roomnamemessage> list = forest_roomnamemessageService.selectEmployee(model);
        //存储的业主、房间、楼房、关联四表的数据
        map.put("user", list);
        //统计出来的页数
        map.put("num", num);
        //状态码  200正确
        map.put("code", 200);
        return map;
    }

    /**
     * floorDelete
     * 删除楼房信息
     */
    @RequestMapping("/floorDelete")
    public Map<String, Object> deleteByPrimaryKey(@RequestParam("roomNameID") int model, HttpServletRequest request, HttpSession session) {
        //关联表的实例化
        Forest_roomnamemessage ds = new Forest_roomnamemessage();
        //设置关联编号
        ds.setRoomNameID(model);
        //删除关联信息
        int shanchu = forest_roomnamemessageService.deleteByPrimaryKey(model);
        //判断删除是否成功
        if (shanchu == 1) {
            //通过关联编号查询业主编号
            Forest_roomnamemessage l = forest_roomnamemessageService.findSelectCountID(ds);
            /**
             * 添加操作信息
             * */
            //设置业主编号
            f.setYeZhuID(l.getYeZhuID());
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
            //状态码  成功返回200
            map.put("code", 200);
            return map;
        }
        //状态码  失败返回400
        map.put("code", 400);
        return map;
    }

    /**
     * floorSelectAgo
     * 查询之前楼房信息
     */
    @RequestMapping("/floorSelectAgo")
    public Map<String, Object> floorSelectAgo() {
        //查询房间信息
        List<Forest_roomname> room = forest_roomnameService.selectEmployee();
        //查询楼房信息
        List<Forest_roommessage> floor = forest_roommessageService.selectEmployee();
        //存储楼房信息
        map.put("floor", floor);
        //存储房间信息
        map.put("room", room);
        //状态码  成功返回200
        map.put("code", 200);
        return map;
    }

    /**
     * floorInsert
     * 新增关联信息
     */
    @RequestMapping("/floorInsert")
    public Map<String, Object> floorInsert(@RequestBody Forest_roomnamemessage model, HttpServletRequest request, HttpSession session) {
        //设置时间的格式
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获取时间的方法
        Date d = new Date();
        //将获取的时间转换成设置的时间格式进行存储
        model.setRoomNameDate(sf.format(d));
        //查询楼房和房间是否已存储
        List<Forest_roomnamemessage> list = forest_roomnamemessageService.selectByPrimaryKeys(model);
        //如果存在
        if (list.size() == 1) {
            //状态码  返回400
            map.put("code", 400);
            return map;
        } else {
            //存储用户的数据
            map.put("list", list);
            //新增加业主信息
            int add = forest_roomnamemessageService.insertSelective(model);
            //进行评判
            if (add == 1) {
                //设置业主编号
                f.setYeZhuID(model.getYeZhuID());
                //设置日常操作类型
                f.setStyleID(1);
                //获取session的物业登录名
                String name = (String) session.getAttribute("name");
                //获取session的物业编号
                int id = (int) session.getAttribute("id");
                //设置物业登录名
                f.setCurrentEntryName(name);
                //设置物业编号
                f.setXtYongHuID(id);
                //存储日常操作信息
                int sist = forest_currentEntryService.insertSelectiveS(f);
                //状态码   返回200
                map.put("code", 200);
                return map;
            }
        }
        //状态码 返回500
        map.put("code", 500);
        return map;
    }

    /**
     * floorSelectUpdate
     * 关联信息修改之前
     *
     * @RequestParam("roomNameID") int model
     */
    @RequestMapping("/floorSelectUpdate")
    public Map<String, Object> floorSelectUpdate(@RequestParam("roomNameID") int model, HttpServletRequest request) {
        //查询的业主、房间、楼房、关联四表的数据
        Forest_roomnamemessage forest_roomnamemessage = forest_roomnamemessageService.selectByPrimaryKey(model);
        //查询房间信息
        List<Forest_roomname> room = forest_roomnameService.selectEmployee();
        //查询楼房信息
        List<Forest_roommessage> floor = forest_roommessageService.selectEmployee();
        //存储楼房编号
        arr[0] = forest_roomnamemessage.getFloorID();
        //存储房间编号
        arr[1] = forest_roomnamemessage.getRoomID();
        //存储业主编号
        arr[2] = forest_roomnamemessage.getYeZhuID();
        //存储楼房信息
        map.put("floorList", floor);
        //存储房间信息
        map.put("roomList", room);
        //存储业主、房间、楼房、关联四表的数据
        map.put("list", forest_roomnamemessage);
        //状态码 返回200
        map.put("code", 200);
        return map;
    }

    /**
     * floorSelectUpdate
     * 关联信息修改之后
     *
     * @RequestParam("roomNameID") int model
     */
    @RequestMapping("/floorUpdateAfter")
    public Map<String, Object> floorUpdateAfter(@RequestBody Forest_roomnamemessage forest_roomnamemessage, HttpSession session) {
        //设置时间的格式
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获取时间的方法
        Date d = new Date();
        //将获取的时间转换成设置的时间格式进行存储
        forest_roomnamemessage.setRoomNameDate(sf.format(d));
        //修改关联信息的
        int updateByPrimaryKeySelective = forest_roomnamemessageService.updateByPrimaryKeySelective(forest_roomnamemessage);
        //查询的业主、房间、楼房、关联四表的数据
        List<Forest_roomnamemessage> list = forest_roomnamemessageService.selectByPrimaryKeys(forest_roomnamemessage);
        //获取是否存储该信息了
        if (list.size() == 1) {
            //设置业主编号
            f.setYeZhuID(arr[2]);
            //设置日常操作类型
            f.setStyleID(2);
            //获取session的物业登录名
            String name = (String) session.getAttribute("name");
            //获取session的物业编号
            int id = (int) session.getAttribute("id");
            //设置物业登录名
            f.setCurrentEntryName(name);
            //设置物业编号
            f.setXtYongHuID(id);
            //存储日常操作信息
            int sist = forest_currentEntryService.insertSelectiveS(f);
            //状态码   返回200
            map.put("code", 200);
            return map;
        }
        //设置房间的编号
        forest_roomnamemessage.setRoomID(arr[1]);
        //设置楼房的编号
        forest_roomnamemessage.setFloorID(arr[0]);
        //设置业主编号
        forest_roomnamemessage.setYeZhuID(arr[2]);
        //修改关联表将以前的数据进行恢复
        int ss = forest_roomnamemessageService.updateByPrimaryKeySelective(forest_roomnamemessage);
        //状态码  返回400
        map.put("code", 400);
        return map;
    }

    /**
     * floorSelectEmploy
     * 按楼号排序
     */
    @RequestMapping("/floorSelectEmploy")
    public Map<String, Object> floorSelectEmploy(@RequestBody Forest_roomnamemessage model, HttpServletRequest request) {
        //求出统计的数据
        num = count(model);
        //判断是否是首页
        if (model.getNum() != 0) {
            //若不是首页，则获取起始值
            int ss = model.getNum() * model.getSize();
            //存储起始值
            model.setNum(ss);
        }
        //查询的业主、房间、楼房、关联四表的数据（按楼房信息）
        List<Forest_roomnamemessage> list = forest_roomnamemessageService.selectEmploy(model);
        //存储业主、房间、楼房、关联四表的数据
        map.put("user", list);
        //返回页数
        map.put("num", num);
        //状态码  返回200
        map.put("code", 200);
        return map;
    }

    /**
     * 按房间号排序
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/floorSelectRoom")
    public Map<String, Object> floorSelectRoom(@RequestBody Forest_roomnamemessage model, HttpServletRequest request) {
        //求出统计的数据
        num = count(model);
        //判断是否是首页
        if (model.getNum() != 0) {
            //若不是首页，则获取起始值
            int ss = model.getNum() * model.getSize();
            //存储起始值
            model.setNum(ss);
        }
        //查询的业主、房间、楼房、关联四表的数据（按房间信息）
        List<Forest_roomnamemessage> list = forest_roomnamemessageService.floorSelectRoom(model);
        //存储业主、房间、楼房、关联四表的数据
        map.put("user", list);
        //返回页数
        map.put("num", num);
        //状态码  返回200
        map.put("code", 200);
        return map;
    }

    /**
     * 按姓名排序
     *
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("/floorSelectByPrimaryKeysName")
    public Map<String, Object> floorSelectByPrimaryKeysName(@RequestBody Forest_roomnamemessage model, HttpServletRequest request) {
        //求出统计的数据
        num = counts(model);
        //判断是否是首页
        if (model.getNum() != 0) {
            //若不是首页，则获取起始值
            int ss = model.getNum() * model.getSize();
            //存储起始值
            model.setNum(ss);
        }
        //查询的业主、房间、楼房、关联四表的数据（按房间信息）
        List<Forest_roomnamemessage> list = forest_roomnamemessageService.selectByPrimaryKeysName(model);
        //存储业主、房间、楼房、关联四表的数据
        map.put("user", list);
        //返回页数
        map.put("num", num);
        //状态码  返回200
        map.put("code", 200);
        return map;
    }

    /**
     * 求出统计的数据
     */
    public int counts(Forest_roomnamemessage model) {
        //查询统计的数据
        count = forest_roomnamemessageService.findSelectCountEnter(model);
        if (count % model.getSize() == 0) {
            num = count / model.getSize();
        } else {
            num = count / model.getSize() + 1;
        }
        if (num >= 8) {
            return 8;
        }
        return num;
    }

}
