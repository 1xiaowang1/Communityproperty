package com.forest.communityproperty.contoller;

import com.forest.communityproperty.entity.Forest_roomname;
import com.forest.communityproperty.global.Forest_variable;
import com.forest.communityproperty.service.Forest_roomnameService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class Forest_roomnameController {
    private Map<String, Object> map = new HashMap<>();
    @Autowired
    Forest_roomnameService forest_roomnameService;

    /**
     *查询房间信息
     */
    @PostMapping("/roomInsertServiceEmployee")
    public Map<String, Object> selectEmployee(@RequestBody Forest_roomname model, HttpServletRequest request, HttpSession session)
    {
        if (new Forest_variable().variableNameSession(request) == 500) {
            map.put("code", 500);
            return map;
        }

        String nums = String.valueOf(session.getAttribute("num"));
        int tt = Integer.parseInt(nums);
        System.out.println(tt);
        if (tt != 2 && tt != 3) {
            map.put("code", 500);
            return map;
        }


        //查询房间信息
        List<Forest_roomname> list=forest_roomnameService.selectEmployee();
        map.put("name", new Forest_variable().sessionName(request));
        map.put("roomList",list);
        map.put("code",200);
        return map;
    }
    @PostMapping("/roomDeleteByPrimaryKeys")
    public Map<String, Object> deleteByPrimaryKeys(@RequestBody Forest_roomname model)
    {
        //查询房间信息
        int list=forest_roomnameService.deleteByPrimaryKeys(model);
        map.put("code",200);
        return map;
    }
}
