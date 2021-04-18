package com.forest.communityproperty.contoller;

import com.forest.communityproperty.entity.Forest_flower;
import com.forest.communityproperty.entity.Forest_roommessage;
import com.forest.communityproperty.entity.Forest_roomname;
import com.forest.communityproperty.service.Forest_roommessageService;
import com.forest.communityproperty.service.Forest_roomnameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@RestController
public class Forest_flowerController {
    private Map<String, Object> map = new HashMap<>();
    @Autowired
    Forest_roomnameService forest_roomnameService;
    @Autowired
    Forest_roommessageService forest_roommessageService;

    /**
     * 新增信息
     */
    @PostMapping("/serviceEmployeeInsert")
    public Map<String, Object> selectEmployee(@RequestBody Forest_flower model, HttpServletRequest request, HttpSession session) {
        int num = model.getNum();
        //设置时间的格式
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获取时间的方法
        Date d = new Date();
        //将获取的时间转换成设置的时间格式进行存储
        if (num == 1) {
            Forest_roommessage forest_roommessage = new Forest_roommessage();
            forest_roommessage.setFloorDate(sf.format(d));
            forest_roommessage.setFloorBeiZhu("1");
            System.out.println(model.getName());
            forest_roommessage.setFloorName(model.getName());
            forest_roommessageService.insertSelective(forest_roommessage);
            map.put("code", 400);
            return map;
        } else {
            Forest_roomname forest_roomname = new Forest_roomname();
            forest_roomname.setRoomBeiZhu("1");
            forest_roomname.setRoomDate(sf.format(d));
            forest_roomname.setRoomName(model.getName());
            forest_roomnameService.insertSelective(forest_roomname);
            map.put("code", 200);
        }
        return map;
    }


}
