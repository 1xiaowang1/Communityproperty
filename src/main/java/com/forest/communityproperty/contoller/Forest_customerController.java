package com.forest.communityproperty.contoller;

import com.forest.communityproperty.entity.Forest_yezhumessage;
import com.forest.communityproperty.global.Forest_variable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;
import java.util.Map;

/**
 * 客服
 */
@RestController
public class Forest_customerController {
    private Map<String,Object> map= new HashMap<>();
    @RequestMapping("customer")
    public Map<String, Object> customer(@RequestBody Forest_yezhumessage model, HttpServletRequest request){
        //判断用户是否存在登录了
        if (new Forest_variable().variableNameSession(request) == 500) {
            //状态码  500错误
            map.put("code", 500);
            return map;
        }
        //系统物业人员的账号名称
        map.put("name", new Forest_variable().sessionName(request));
        //使用map存储数据
        //提示信息
        map.put("code",200);

        return map;

    }
    @RequestMapping("webCustomer")
    public Map<String, Object> webCustomer(@RequestBody Forest_yezhumessage model, HttpServletRequest request){
        //判断用户是否存在登录了
        if (new Forest_variable().variableYeZhuNameSession(request) == 500) {
            //状态码  500错误
            map.put("code", 500);
            return map;
        }
        //系统物业人员的账号名称
        map.put("name", new Forest_variable().variableYeZhuName(request));
        //使用map存储数据
        //提示信息
        map.put("code",200);

        return map;

    }
}
