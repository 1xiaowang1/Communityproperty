package com.forest.communityproperty.contoller;

import com.forest.communityproperty.entity.Forest_roommessage;
import com.forest.communityproperty.global.Forest_variable;
import com.forest.communityproperty.service.Forest_roommessageService;
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
public class Forest_roommessageController {
    private Map<String, Object> map = new HashMap<>();
    @Autowired
    Forest_roommessageService forest_roommessageService;

    /**
     *查询楼房信息
     */
    @PostMapping("/floorInsertServiceEmployee")
    public Map<String, Object> selectEmployee(@RequestBody Forest_roommessage model, HttpServletRequest request, HttpSession session )
    {
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


        //查询楼房信息
        List<Forest_roommessage> list=forest_roommessageService.selectEmployee();
        map.put("name", new Forest_variable().sessionName(request));
        map.put("userList",list);
        map.put("code",200);
        return map;
    }
    @PostMapping("/floorDeleteByPrimaryKeys")
    public Map<String, Object> deleteByPrimaryKeys(@RequestBody Forest_roommessage model )
    {
        //查询楼房信息
        int  list=forest_roommessageService.deleteByPrimaryKeys(model);
        map.put("code",200);
        return map;
    }
}