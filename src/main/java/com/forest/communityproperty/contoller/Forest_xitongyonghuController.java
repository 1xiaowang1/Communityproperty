package com.forest.communityproperty.contoller;

import com.forest.communityproperty.entity.Forest_xitongyonghu;
import com.forest.communityproperty.global.Forest_variable;
import com.forest.communityproperty.service.Forest_xitongyonghuService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
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
public class Forest_xitongyonghuController {
    //map存储数据
    private Map<String, Object> map = new HashMap<>();
    //存储
    private int[] arr = new int[2];
    //全局访问类
    private Forest_variable Forest_variable = new Forest_variable();
    private List<Forest_xitongyonghu> list;
    private int num;
    /**
     * 系统管理业务层
     */
    @Autowired
    Forest_xitongyonghuService forest_xitongyonghuService;


    /**
     * 用户注册
     */
    @PostMapping("/register")
    public Map<String, Object> register(@RequestBody Forest_xitongyonghu forest_xitongyonghu) {
        //用户状态
        forest_xitongyonghu.setXtYongHuState(2);
        //用户楼层
        forest_xitongyonghu.setXtYongHuFlower(0);
        //用户的级别
        forest_xitongyonghu.setXtYongHuJiBie(1);

        //设置时间的格式
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获取时间的方法
        Date d = new Date();
        //将获取的时间转换成设置的时间格式进行存储
        forest_xitongyonghu.setXtDate(sf.format(d));
        //判断用户输入的用户名和身份证号是独一无二的
        if (forest_xitongyonghu.getXtYongUserName().equals("") || forest_xitongyonghu.getXtYongHuSFZ().equals("") || arr[0] == 1 || arr[1] == 1) {
            //错误 状态码
            map.put("code", 400);
        } else {
            //插入物业信息
            int id = forest_xitongyonghuService.insertSelective(forest_xitongyonghu);
            //判断是否插入成功  返回对应状态码
            if (id == 1) {
                map.put("code", 200);
            } else {
                map.put("code", 500);
            }
        }
        return map;
    }

    /**
     * 用户注册前判断该用户名是否注册过
     */
    @PostMapping("/findUserName")
    public Map<String, Object> findUsrName(@RequestBody Forest_xitongyonghu model) {
        //查询用户名是否注册过
        String userNameById = forest_xitongyonghuService.findUserName(model);
        if (userNameById == null) {
            map.put("code", 200);
            arr[0] = 0;
        } else {
            arr[0] = 1;
            map.put("code", 500);
        }
        return map;
    }

    /**
     * 用户注册前判断该用户是否注册过
     *
     * @param model
     * @return
     */
    @PostMapping("/findUsrCredit")
    public Map<String, Object> findUsrCredit(@RequestBody Forest_xitongyonghu model) {
        //查询身份证是否注册过
        String userNameById = forest_xitongyonghuService.findUserCredit(model);
        if (userNameById == null) {
            map.put("code", 200);
            arr[1] = 0;
        } else {
            arr[1] = 1;
            map.put("code", 500);
        }
        return map;
    }

    /**
     * 用户登录
     *
     * @param forest_xitongyonghu
     * @param session
     * @return
     */
    @PostMapping("/login")
    public Map<String, Object> login(@RequestBody Forest_xitongyonghu forest_xitongyonghu, HttpSession session) {
        forest_xitongyonghu.setXtYongHuState(1);
        forest_xitongyonghu.setXtYongHuJiBie(1);
        //以用户名和密码为条件来查询数据
        String s = forest_xitongyonghuService.doesTheUserExist(forest_xitongyonghu);
        //如果返回为空的话，表明数据库不存在该数据
        if (s == "" || s == null) {
            //状态码 返回500
            map.put("code", 500);
            return map;
        } else {
            //返回存在值  进行整型数据转换
            int ints = Integer.parseInt(s);
            //将转换数据进行存储  业主编号
            forest_xitongyonghu.setXtYongHuID(ints);
            //判断是否免登录
            if (forest_xitongyonghu.getResult() == 0) {
                //使用全局变量存储  将业主编号 和 业主登录名存储到session中
                Forest_variable.variableName(session, forest_xitongyonghu);
            } else {
                Forest_variable.variableNameTime(session, forest_xitongyonghu);
            }
            session.setAttribute("num", 1);
            map.put("code", 200);
        }

        return map;
    }

    //重置密码
    @PostMapping("/update")
    public Map<String, Object> updateByPrimaryKey(@RequestBody Forest_xitongyonghu model) {
        //查询身份证信息
        String findUserNameAndSFZ = forest_xitongyonghuService.findUserNameAndSFZ(model);
        //查询身份信息是否为空
        if (findUserNameAndSFZ == null) {
            //状态码  返回400
            map.put("code", 400);
            return map;
        }
        //设置时间的格式
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获取时间的方法
        Date d = new Date();
        //将获取的时间转换成设置的时间格式进行存储
        model.setXtDate(sf.format(d));
        //修改业主信息
        int s = forest_xitongyonghuService.updateByPrimaryKey(model);
        //判断修改是否成功  返回对应状态码
        if (s == 0) {
            map.put("code", 500);
        } else {
            map.put("code", 200);
        }
        return map;
    }

    /**
     * 管理员登录
     *
     * @param model
     * @param session
     * @return
     */
    @PostMapping("/pageLogin")
    public Map<String, Object> pageLogin(@RequestBody Forest_xitongyonghu model, HttpSession session) {
        model.setXtYongHuState(1);
        model.setXtYongHuJiBie(2);
        session.setAttribute("num", 2);
        if (model.getXtYongUserName().equals("root")) {
            session.setAttribute("num", 3);
            model.setXtYongHuJiBie(3);
        }
        //以用户名和密码为条件来查询数据
        List<Forest_xitongyonghu> s = forest_xitongyonghuService.pageUserExist(model);
        //如果返回为空的话，表明数据库不存在该数据
        if (s.size() != 1) {
            //状态码 返回500
            map.put("code", 500);
            return map;
        } else {
            //将转换数据进行存储  业主编号
            model.setXtYongHuID(s.get(0).getXtYongHuID());

            //判断是否免登录
            if (model.getResult() == 0) {
                //使用全局变量存储  将业主编号 和 业主登录名存储到session中
                Forest_variable.variableName(session, model);
            } else {
                Forest_variable.variableNameTime(session, model);
            }
            map.put("code", 200);
        }
        return map;
    }

    @RequestMapping("/pageUserSelect")
    public Map<String, Object> pageUserSelect(@RequestBody Forest_xitongyonghu model, HttpServletRequest request, HttpSession session) {
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
        if (tt == 2) {
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
            list = forest_xitongyonghuService.pageUserSelect(model);
        } else {
            //求出统计的数
            num = countUsers(model);
            //判断是否是首页
            if (model.getNum() != 0) {
                //若不是首页，则获取起始值
                int ss = model.getNum() * model.getSize();
                //存储起始值
                model.setNum(ss);
            }
            //查询业主的起始页
            list = forest_xitongyonghuService.pageUserSelects(model);
        }
        System.out.println(nums);
        //存储物业的登录名
        map.put("name", new Forest_variable().sessionName(request));
        //存储业主的数据
        map.put("user", list);
        //存储页数
        map.put("num", num);
        //状态码 200
        map.put("code", 200);
        //map
        map.put("nums", nums);
        return map;
    }

    /**
     * 求出统计的数据
     */
    public int countUser(Forest_xitongyonghu model) {
        int num;
        //查询业主的统计数据
        int count = forest_xitongyonghuService.findSelectCount();
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
     * 3
     * 求出统计的数据
     */
    public int countUsers(Forest_xitongyonghu model) {
        int num;
        //查询业主的统计数据
        int count = forest_xitongyonghuService.findSelectCounts();
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


    /* * 删除用户信息
     * @param id
     * @return
     */
    @RequestMapping("/deleteByPrimaryKey")
    public Map<String, Object> deleteByPrimaryKey(@RequestBody Forest_xitongyonghu model) {
        int num = forest_xitongyonghuService.deleteByPrimaryKeys(model.getXtYongHuID());
        if (num == 1) {
            map.put("code", 200);
            return map;
        }
        map.put("code", 500);
        return map;
    }

    /**
     * 修改状态
     *
     * @return
     */
    @RequestMapping("/updateSelectiveUsers")
    public Map<String, Object> updateSelective(@RequestBody Forest_xitongyonghu forest_xitongyonghu) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获取时间的方法
        Date d = new Date();
        //将获取的时间转换成设置的时间格式进行存储
        forest_xitongyonghu.setXtDate(sf.format(d));
        System.out.println(forest_xitongyonghu.getXtYongHuState());
        int num = forest_xitongyonghuService.updateSelectiveUsers(forest_xitongyonghu);
        if (num == 1) {
            map.put("code", 200);
            return map;
        }
        map.put("code", 500);
        return map;
    }

    /**
     * 修改用户级别
     *
     * @param forest_xitongyonghu
     * @return
     */
    @RequestMapping("/updateSelectiveJiBie")
    public Map<String, Object> updateSelectiveJiBie(@RequestBody Forest_xitongyonghu forest_xitongyonghu) {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获取时间的方法
        Date d = new Date();
        //将获取的时间转换成设置的时间格式进行存储
        forest_xitongyonghu.setXtDate(sf.format(d));
        int num = forest_xitongyonghuService.updateSelectiveJiBie(forest_xitongyonghu);
        if (num == 1) {
            map.put("code", 200);
            return map;
        }
        map.put("code", 500);
        return map;
    }

    @RequestMapping("/pageUserSelectXtYongName")
    public Map<String, Object> pageUserSelectXtYongName(@RequestBody Forest_xitongyonghu model, HttpServletRequest request, HttpSession session) {
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
        if (tt == 2) {
            //查询业主的起始页
            list = forest_xitongyonghuService.pageUserSelectXtYongNames(model);
        } else {
            //查询业主的起始页
            list = forest_xitongyonghuService.pageUserSelectXtYongName(model);
        }
        System.out.println(nums);
        //存储物业的登录名
        map.put("name", new Forest_variable().sessionName(request));
        //存储业主的数据
        map.put("user", list);
        //状态码 200
        map.put("code", 200);
        //map
        map.put("nums", nums);
        return map;
    }

}
