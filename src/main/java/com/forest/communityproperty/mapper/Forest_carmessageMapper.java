package com.forest.communityproperty.mapper;

import com.forest.communityproperty.entity.Forest_carmessage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Forest_carmessageMapper {
    /**
     * 查询车辆信息
     */
    List<Forest_carmessage> selectEmployee();

    /**
     * 新增车辆信息
     * @param forest_carmessage
     * @return
     */
    int insertSelective(Forest_carmessage forest_carmessage);
    /**
     * 查询车辆统计数据
     * @return
     */
    int findSelectCount();
    /**
     * 上传图片
     */
    int updateByPrimaryImage(Forest_carmessage forest_carmessage);

    /**
     *     删除车辆信息
     *     @return
     */
    int deleteByPrimaryKey(int model);

    /**
     * 修改车辆信息
     * @param forest_carmessage
     * @return
     */
    int updateByPrimaryKeySelective(Forest_carmessage forest_carmessage);
}
