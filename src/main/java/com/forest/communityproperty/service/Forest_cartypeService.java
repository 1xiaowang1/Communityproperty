package com.forest.communityproperty.service;

import com.forest.communityproperty.entity.Forest_cartype;
import com.forest.communityproperty.mapper.Forest_cartypeMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class Forest_cartypeService {
    @Resource
    Forest_cartypeMapper forest_cartypeMapper;
    /**
     * 查询车位区域
     * @return
     */
    public List<Forest_cartype> selectEmployee(){

        return forest_cartypeMapper.selectEmployee();
    }
}
