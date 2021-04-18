package com.forest.communityproperty.mapper;

import com.forest.communityproperty.entity.Forest_roommessage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Forest_roommessageMapper {
    /**
     * 查询房间信息
     *
     * @return
     */
    List<Forest_roommessage> selectEmployee();

    /**
     * 新增房间信息
     *
     * @param forest_roommessage
     * @return
     */
    int insertSelective(Forest_roommessage forest_roommessage);

    /**
     * 删除房间信息
     *
     * @param forest_roommessage
     * @return
     */
    int deleteByPrimaryKeys(Forest_roommessage forest_roommessage);
}
