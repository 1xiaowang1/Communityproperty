package com.forest.communityproperty.mapper;

import com.forest.communityproperty.entity.Forest_travelrecords;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Forest_travelrecordsMapper {
    /**
     * 查询临时信息
     * @param forest_travelrecords
     * @return
     */
    List<Forest_travelrecords> selectEmployee(Forest_travelrecords forest_travelrecords);

    /**
     * 新增临时信息
     * @param forest_travelrecords
     * @return
     */
    int insertSelective(Forest_travelrecords forest_travelrecords);

    /**
     * 出入状态的临时表
     * @param forest_travelrecords
     * @return
     */
    int updateByPrimaryKeySelective(Forest_travelrecords forest_travelrecords);
    /**
     * 查询车辆关联信息的统计数据
     * @return
     */
    int findSelectCount();
}
