package com.forest.communityproperty.service;

import com.forest.communityproperty.entity.Forest_carareatype;
import com.forest.communityproperty.mapper.Forest_carareatypeMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class Forest_carareatypeService {
    @Resource
    Forest_carareatypeMapper forest_carareatypeMapper;

    /**
     * 查询车位区域
     * @return
     */
   public List<Forest_carareatype> selectEmployee()
   {
       return forest_carareatypeMapper.selectEmployee();
   }
}
