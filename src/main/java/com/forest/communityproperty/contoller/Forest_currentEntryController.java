package com.forest.communityproperty.contoller;

import com.forest.communityproperty.entity.Forest_currentEntry;
import com.forest.communityproperty.service.Forest_currentEntryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class Forest_currentEntryController {
    private Map<String, Object> map = new HashMap<>();
    @Autowired
    Forest_currentEntryService forest_currentEntryService;

    /**
     * 查询日常信息
     */
    @PostMapping("/currentEntryServiceEmployee")
    public Map<String, Object> selectEmployee() {
        //查询日常信息
        List<Forest_currentEntry> list = forest_currentEntryService.selectEmployee();
        //使用map存储数据
        map.put("list", list);
        return map;
    }

    /**
     * 增加日常信息
     */
    @PostMapping("/currentEntryInsertSelective")
    public Map<String, Object> currentEntryInsertSelective(HttpSession session, Forest_currentEntry f) {
        //获取session中系统管理员姓名name值
        String name = (String) session.getAttribute("name");
        //获取session中系统管理员的编号id值
        int id = (int) session.getAttribute("id");
        //赋值给entity的方法
        f.setCurrentEntryName(name);
        f.setXtYongHuID(id);
        //插入数据库的日常信息
        int list = forest_currentEntryService.insertSelectiveS(f);
        //使用map存储数据
        map.put("list", list);
        return map;
    }
}
