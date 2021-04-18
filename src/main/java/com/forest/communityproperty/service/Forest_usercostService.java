package com.forest.communityproperty.service;

import com.forest.communityproperty.entity.Forest_usercost;
import com.forest.communityproperty.mapper.Forest_usercostMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class Forest_usercostService {
    @Resource
    private Forest_usercostMapper forest_usercostMapper;

    /**
     * 查询商业信息和用费
     *
     * @param forest_usercost
     * @return
     */
    public int insertSelective(Forest_usercost forest_usercost) {
        return forest_usercostMapper.insertSelective(forest_usercost);
    }

    /**
     * 查询是否这个月添加了信息
     *
     * @param forest_usercost
     * @return
     */
    public List<Forest_usercost> selectByPrimaryKey(Forest_usercost forest_usercost) {
        return forest_usercostMapper.selectByPrimaryKey(forest_usercost);
    }


}
