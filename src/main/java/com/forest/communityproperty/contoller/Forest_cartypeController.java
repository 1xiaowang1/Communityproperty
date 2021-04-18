package com.forest.communityproperty.contoller;

import com.forest.communityproperty.entity.Forest_cartype;
import com.forest.communityproperty.service.Forest_cartypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class Forest_cartypeController {
    private Map<String, Object> map = new HashMap<>();
    @Autowired
    Forest_cartypeService forest_cartypeService;

    /**
     * 查询车位区域
     */
    public List<Forest_cartype> selectEmployee() {
        //查询车位区域
        List<Forest_cartype> list = forest_cartypeService.selectEmployee();
        return list;
    }
}
