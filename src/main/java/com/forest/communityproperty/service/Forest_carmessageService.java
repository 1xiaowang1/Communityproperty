package com.forest.communityproperty.service;

import com.forest.communityproperty.entity.Forest_carmessage;
import com.forest.communityproperty.mapper.Forest_carmessageMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class Forest_carmessageService {
    @Resource
    Forest_carmessageMapper forest_carmessageMapper;

    /**
     * 查询车辆信息
     */
    public List<Forest_carmessage> selectEmployee() {
        return forest_carmessageMapper.selectEmployee();
    }

    /**
     * 新增车辆信息
     *
     * @param forest_carmessage
     * @return
     */
    public int insertSelective(Forest_carmessage forest_carmessage) {
        return forest_carmessageMapper.insertSelective(forest_carmessage);
    }

    /**
     * 上传图片
     */
    public int updateByPrimaryImage(Forest_carmessage forest_carmessage) {
        return forest_carmessageMapper.updateByPrimaryImage(forest_carmessage);
    }

    /**
     * 删除车辆信息
     *
     * @return
     */
    public int deleteByPrimaryKey(int model) {
        return forest_carmessageMapper.deleteByPrimaryKey(model);
    }

    /**
     * 修改车辆信息
     *
     * @param forest_carmessage
     * @return
     */
    public int updateByPrimaryKeySelective(Forest_carmessage forest_carmessage) {
        return forest_carmessageMapper.updateByPrimaryKeySelective(forest_carmessage);
    }
}
