package com.forest.communityproperty.contoller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.Map;

@RestController
public class Forest_tuiChuController {
    private Map<String, Object> map = new HashMap<>();

    @RequestMapping("tuiChu")
    public Map<String, Object> tuiChu(HttpSession session) {
        session.removeAttribute("name");
        session.removeAttribute("id");
        map.put("code", 200);
        return map;
    }
}
