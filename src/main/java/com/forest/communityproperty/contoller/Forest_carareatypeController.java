package com.forest.communityproperty.contoller;

import com.forest.communityproperty.entity.Forest_carareatype;
import com.forest.communityproperty.service.Forest_carareatypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class Forest_carareatypeController {
    private Map<String, Object> map = new HashMap<>();
    @Autowired
    Forest_carareatypeService forest_carareatypeService;

    /**
     * 查询车位信息
     */
    public List<Forest_carareatype> selectEmployee() {
        //查询车位信息
        List<Forest_carareatype> list = forest_carareatypeService.selectEmployee();
        return list;
    }

}
