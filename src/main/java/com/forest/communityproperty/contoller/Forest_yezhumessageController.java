package com.forest.communityproperty.contoller;

import com.forest.communityproperty.entity.Forest_currentEntry;
import com.forest.communityproperty.entity.Forest_yezhumessage;
import com.forest.communityproperty.global.Forest_variable;
import com.forest.communityproperty.service.Forest_currentEntryService;
import com.forest.communityproperty.service.Forest_yezhumessageService;
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
public class Forest_yezhumessageController {
    //使用map存储数据
    private Map<String, Object> map = new HashMap<>();
    //数据页数和起始值
    private int count, num;
    //设置日常信息
    public Forest_currentEntry f = new Forest_currentEntry();
    //全局访问类
    private Forest_variable Forest_variable =new Forest_variable();
    /**
     * 系统管理业务层
     */
    //业主service层映射
    @Autowired
    private Forest_yezhumessageService forest_yezhumessageService;
    //日常信息service层映射
    @Autowired
    private Forest_currentEntryService forest_currentEntryService;

    /**
     * insertSelective
     * 首次加载页面
     */
    @RequestMapping("/yeZhuSelect")
    public Map<String, Object> insertSelective(@RequestBody Forest_yezhumessage model, HttpServletRequest request) {
        //求出统计的数据
        num = count(model);
        //判断是否登录
        if (new Forest_variable().variableNameSession(request) == 500) {
            map.put("code", 500);
            return map;
        }
        //查询业主的起始页
        List<Forest_yezhumessage> list = forest_yezhumessageService.selectByPrimaryKeys(model);
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
    public int count(Forest_yezhumessage model) {
        //查询业主的统计数据
        count = forest_yezhumessageService.findSelectCount();
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

    /**
     * yeZhuSelectFenYe
     * 分页查询
     */
    @RequestMapping("/yeZhuSelectFenYe")
    public Map<String, Object> yeZhuSelectFenYe(@RequestBody Forest_yezhumessage model, HttpServletRequest request) {
        //求出统计的数据
        num = count(model);
        //求出起始页
        int ss = model.getNum() * model.getSize();
        //设置起始列数
        model.setNum(ss);
        //查询对应的数据
        List<Forest_yezhumessage> list = forest_yezhumessageService.selectByPrimaryKeys(model);
        //存储业主的数据
        map.put("user", list);
        //存储页数
        map.put("num", num);
        //状态码 200
        map.put("code", 200);
        //存储物业的登录名
        map.put("name", new Forest_variable().sessionName(request));
        return map;
    }

    /**
     * yeZhuDelete
     * 删除业主信息
     */
    @RequestMapping("/yeZhuDelete")
    public Map<String, Object> yeZhuDelete(@RequestParam("yeZhuID") int model, HttpServletRequest request, HttpSession session) {
          //删除业主信息
        int shanchu = forest_yezhumessageService.deleteByPrimaryKey(model);
        //存储物业的登录名
        map.put("name", new Forest_variable().sessionName(request));
        //判断删除是否成功
        if (shanchu == 1) {
            //存储业主编号
            f.setYeZhuID(model);
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
            //状态码  200
            map.put("code", 200);
            return map;
        }
        //状态码 400
        map.put("code", 400);
        return map;
    }

    /**
     * yeZhuFindUserCredit
     * 按业主身份证来查询
     */
    @RequestMapping("/yeZhuFindUserCredit")
    public Map<String, Object> findUserCredit(@RequestParam("yeZhuSFZ") String model, HttpServletRequest request) {
        //按业主身份证来查询
        String findUserCredit = forest_yezhumessageService.findUserCredit(model);
        //判断业主身份证是否为空
        if (findUserCredit == null|| findUserCredit == "" ) {
            //状态码  200
            map.put("code", 200);
            return map;
        }
        //返回业主编号
        map.put("yeZhuID",Integer.parseInt(findUserCredit));
        //状态码 500
        map.put("code", 500);
        return map;
    }

    /**
     * yeZhuInsert
     * 新增业主信息
     */
    @RequestMapping("/yeZhuInsert")
    public Map<String, Object> yeZhuInsert(@RequestBody Forest_yezhumessage model, HttpServletRequest request, HttpSession session) {
        //设置时间的格式
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获取时间的方法
        Date d = new Date();
        //设置业主登录名
        model.setYeZhuName("");
        //将获取的时间转换成设置的时间格式进行存储
        model.setYeZhuTime(sf.format(d));
        //设置业主登录密码
        model.setYeZhuPassword("");
        //设置业主姓名
        model.setYeZhuName(model.getName());
        //新增加业主信息
        int add = forest_yezhumessageService.insertSelective(model);
        //存储物业的登录名
        map.put("name", new Forest_variable().sessionName(request));
        //进行评判
        if (add == 1) {
            //获取业主编号
            int findYeZhuCredit = forest_yezhumessageService.findYeZhuCredit(model.getYeZhuSFZ());
            //设置业主编号
            f.setYeZhuID(findYeZhuCredit);
            //设置日常信息类型
            f.setStyleID(1);
            //获取session中的登录名
            String name = (String) session.getAttribute("name");
            //获取session 中的物业编号
            int id = (int) session.getAttribute("id");
            //设置登录名
            f.setCurrentEntryName(name);
            //设置物业编号
            f.setXtYongHuID(id);
            //存储日常信息
            int list = forest_currentEntryService.insertSelectiveS(f);
            //状态码 200
            map.put("code", 200);
            return map;
        }
        //状态码  500
        map.put("code", 500);
        return map;
    }

    /**
     * yeZhuselectByPrimaryKey
     * 业主id 查询信息
     * selectByPrimaryKey
     *
     * @RequestParam("yeZhuID") int model
     */
    @RequestMapping("/yeZhuselectByPrimaryKey")
    public Map<String, Object> yeZhuselectByPrimaryKey(@RequestParam("yeZhuID") int model, HttpServletRequest request) {
        //通过业主编号查询信息
        List<Forest_yezhumessage> selectByPrimaryKey = forest_yezhumessageService.selectByPrimaryKey(model);
        //存储物业的登录名
        map.put("name", new Forest_variable().sessionName(request));
        //存储业主信息
        map.put("user", selectByPrimaryKey);
        //状态码 200
        map.put("code", 200);
        return map;
    }

    /**
     * yeZhuupdateByPrimaryKeySelective
     * 业主id 修改信息
     * updateByPrimaryKeySelective
     *
     * @RequestParam("yeZhuID") int model
     */
    @RequestMapping("/yeZhuupdateByPrimaryKeySelective")
    public Map<String, Object> updateByPrimaryKeySelective(@RequestBody Forest_yezhumessage model, HttpServletRequest request, HttpSession session) {

        //设置时间的格式
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获取时间的方法
        Date d = new Date();
        //将获取的时间转换成设置的时间格式进行存储
        model.setYeZhuTime(sf.format(d));
        //存储业主姓名
        model.setYeZhuName(model.getName());
        //修改业主信息
        int updateByPrimaryKeySelective = forest_yezhumessageService.updateByPrimaryKeySelective(model);
        //存储物业的登录名
        map.put("name", new Forest_variable().sessionName(request));

        if (updateByPrimaryKeySelective == 1) {
            //设置业主编号
            f.setYeZhuID(model.getYeZhuID());
            //设置日常信息类型
            f.setStyleID(2);
            //获取session中的登录名
            String name = (String) session.getAttribute("name");
            //获取session 中的物业编号
            int id = (int) session.getAttribute("id");
            //设置登录名
            f.setCurrentEntryName(name);
            //设置物业编号
            f.setXtYongHuID(id);
            //存储日常信息
            int list = forest_currentEntryService.insertSelectiveS(f);
            //状态码 200
            map.put("code", 200);
            return map;
        }
        //状态码 500
        map.put("code", 500);
        return map;
    }

    /**
     * 查询按姓名业主的统计数据
     * findSelectCountName
     */
    public int findSelectCountName(Forest_yezhumessage model) {
        //查询对应的数据
        count = forest_yezhumessageService.findSelectCountName(model.getName());
        //判断页数
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

    /**
     * yeZhuSelectFenYe
     * 分页查询
     */
    @RequestMapping("/selectByPrimaryKeysName")
    public Map<String, Object> selectByPrimaryKeysName(@RequestBody Forest_yezhumessage model, HttpServletRequest request) {
        //求出统计的数据 页数
        num = findSelectCountName(model);
        //起始值
        int ss = model.getNum() * model.getSize();
        //设置起始页数
        model.setNum(ss);
        //设置业主姓名
        model.setYeZhuName(model.getName());
        //模糊查询
        List<Forest_yezhumessage> list = forest_yezhumessageService.selectByPrimaryKeysName(model);

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
     * 按业主ID来查询数据
     */
    public List<Forest_yezhumessage> selectByPrimaryKeyID(int id)
    {
        List<Forest_yezhumessage>  list=forest_yezhumessageService.selectByPrimaryKey(id);
        return list;
    }


    /**
     * updateRegisterSelective
     * 修改业主信息
     */
    @RequestMapping("/registerYeZhu")
    public Map<String, Object> updateRegisterSelective(@RequestBody Forest_yezhumessage model, HttpServletRequest request) {
        if(model.getName().equals(model.getEmailNum()))
        {
           int size= forest_yezhumessageService.updateRegisterSelective(model);
           if(size==1)
           {
               //状态码 200
               map.put("code", 200);
               return map;
           }
            //状态码 400
            map.put("code", 400);
            return map;
        }
        //状态码 500
        map.put("code", 500);
        return map;
    }
    /**
     * updateRegisterSelective
     * 修改业主信息
     */
    @RequestMapping("/updateYeZhu")
    public Map<String, Object> updateYeZhu(@RequestBody Forest_yezhumessage model, HttpServletRequest request) {
            int size= forest_yezhumessageService.updateRegisterSelective(model);
            if(size==1)
            {
                //状态码 200
                map.put("code", 200);
                return map;
            }
            //状态码 400
            map.put("code", 400);
            return map;
    }
    /**
     * updateRegisterSelective
     * 查询业主信息  登录
     */
    @RequestMapping("/selectYeZhu")
    public Map<String, Object> selectYeZhu(@RequestBody Forest_yezhumessage model, HttpServletRequest request,HttpSession session) {
        List<Forest_yezhumessage> list= forest_yezhumessageService.findYeZhuUserName(model);
        if(list.size()==0)
        {
            //状态码 400
            map.put("code",500);
            return map;
        }
        model.setYeZhuID(list.get(0).getYeZhuID());
        model.setYeZhuName(list.get(0).getYeZhuName());
        Forest_variable.variableYeZhuName(session,model);
        //状态码 200
        map.put("code", 200);
        return map;
    }
    /**
     *前端首页判断是否登录
     * @param model
     * @param request
     * @return
     */
    @RequestMapping("indexZhu")
    public Map<String, Object> indexZhu(@RequestBody Forest_yezhumessage model, HttpServletRequest request) {
        //判断用户是否存在登录了
        if (new Forest_variable().variableYeZhuNameSession(request) == 500) {
            //状态码  500错误
            map.put("code", 200);
            return map;
        }
        map.put("code", 500);
        return map;
    }
}
