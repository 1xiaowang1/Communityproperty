package com.forest.communityproperty.mapper;

import com.forest.communityproperty.entity.Forest_carareatype;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Forest_carareatypeMapper {
    /**
     * 查询车位区域
     *
     * @return
     */
    List<Forest_carareatype> selectEmployee();
}
