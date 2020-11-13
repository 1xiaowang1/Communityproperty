package com.forest.communityproperty.mapper;

import com.forest.communityproperty.entity.Forest_roomname;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Forest_roomnameMapper {
    /**
     * 查询楼房信息
     * @return
     */
    List<Forest_roomname> selectEmployee();

    /**
     * 新增楼房信息
     * @param forest_roomname
     * @return
     */
    int insertSelective(Forest_roomname forest_roomname);

    /**
     * 删除楼房信息
     * @param forest_roomname
     * @return
     */
    int deleteByPrimaryKeys(Forest_roomname forest_roomname );
}
