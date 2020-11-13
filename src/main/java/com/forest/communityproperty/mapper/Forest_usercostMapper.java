package com.forest.communityproperty.mapper;

import com.forest.communityproperty.entity.Forest_usercost;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Forest_usercostMapper {
    /**
     * 查询商业信息和用费
     * @param forest_usercost
     * @return
     */
   int insertSelective(Forest_usercost forest_usercost);

    /**
     * 查询是否这个月添加了信息
     * @param forest_usercost
     * @return
     */
   List<Forest_usercost> selectByPrimaryKey(Forest_usercost forest_usercost);
}
