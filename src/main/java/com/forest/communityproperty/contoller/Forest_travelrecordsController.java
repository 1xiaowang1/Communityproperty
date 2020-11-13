package com.forest.communityproperty.contoller;

import com.forest.communityproperty.entity.Forest_travelrecords;
import com.forest.communityproperty.global.Forest_variable;
import com.forest.communityproperty.service.Forest_travelrecordsService;
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
public class Forest_travelrecordsController {
    @Autowired
    Forest_travelrecordsService forest_travelrecordsService;

    //统计数据的页数和单页数据
    private int count, num;
    //map存储数据
    private Map<String, Object> map = new HashMap<>();

    /**
     * 查询临时信息
     *
     * @param model
     * @return
     */
    @PostMapping("/travelSelectEmployee")
    public Map<String, Object> selectEmployee(@RequestBody Forest_travelrecords model,HttpServletRequest request) {
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
        List<Forest_travelrecords> list2=forest_travelrecordsService.selectEmployee(model);
        //存储车位关联信息
        map.put("travel",list2);
        //统计出来的页数
        map.put("num", num);
        //状态码  200正确
        map.put("code", 200);
        return map;
    }

    /**
     * 新增临时信息
     *
     * @param model
     * @return
     */
    @PostMapping("/travelInsertSelective")
    public Map<String, Object> insertSelective(@RequestBody Forest_travelrecords model, HttpSession session) {
        //设置时间的格式
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获取时间的方法
        Date d = new Date();
        //将获取的时间转换成设置的时间格式进行存储
        model.setTravelRecoDate(sf.format(d));
        //回来状态
        model.setTravelRecoStates(1);
        //物业编号
        model.setXtYongHuID((int)session.getAttribute("id"));
        //新增出行记录信息
        int nNum=forest_travelrecordsService.insertSelective(model);
        if(nNum==1)
        {
            map.put("code",200);
            return map;
        }
        map.put("code",500);
        return map;
    }

    /**
     * 出入状态的临时表
     *
     * @param model
     * @return
     */
    @PostMapping("/travelUpdateByPrimaryKeySelective")
    public Map<String, Object> updateByPrimaryKeySelective(@RequestBody Forest_travelrecords model) {
        //设置时间的格式
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获取时间的方法
        Date d = new Date();
        //将获取的时间转换成设置的时间格式进行存储
        model.setTravelRecoDate(sf.format(d));
       int mNum=forest_travelrecordsService.updateByPrimaryKeySelective(model);
       if(mNum==1){
           map.put("code",200);
           return map;
       }
        map.put("code",500);
        return map;
    }
    /**
     * 求出统计的数据
     */
    public int count(Forest_travelrecords model) {
        //查询统计的数据
        count = forest_travelrecordsService.findSelectCount();
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
