package com.forest.communityproperty.service;

import com.forest.communityproperty.entity.Forest_travelrecords;
import com.forest.communityproperty.mapper.Forest_travelrecordsMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class Forest_travelrecordsService {
    @Resource
    Forest_travelrecordsMapper forest_travelrecordsMapper;

    /**
     * 查询临时信息
     *
     * @param forest_travelrecords
     * @return
     */
    public List<Forest_travelrecords> selectEmployee(Forest_travelrecords forest_travelrecords) {
        return forest_travelrecordsMapper.selectEmployee(forest_travelrecords);
    }

    /**
     * 新增临时信息
     *
     * @param forest_travelrecords
     * @return
     */
    public int insertSelective(Forest_travelrecords forest_travelrecords) {
        return forest_travelrecordsMapper.insertSelective(forest_travelrecords);
    }

    /**
     * 出入状态的临时表
     *
     * @param forest_travelrecords
     * @return
     */
    public int updateByPrimaryKeySelective(Forest_travelrecords forest_travelrecords) {
        return forest_travelrecordsMapper.updateByPrimaryKeySelective(forest_travelrecords);
    }
    /**
     * 查询车辆关联信息的统计数据
     * @return
     */
    public int findSelectCount(){
        return forest_travelrecordsMapper.findSelectCount();
    }
}
