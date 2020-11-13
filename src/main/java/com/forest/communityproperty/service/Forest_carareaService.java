package com.forest.communityproperty.service;

import com.forest.communityproperty.entity.Forest_cararea;
import com.forest.communityproperty.mapper.Forest_carareaMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class Forest_carareaService {
    @Resource
    Forest_carareaMapper forest_carareaMapper;

    /**
     * 查询车辆关联
     *
     * @return
     */
    public List<Forest_cararea> selectEmployee(Forest_cararea model) {
        return forest_carareaMapper.selectEmployee(model);
    }

    /**
     * 查询车辆关联信息的统计数据
     *
     * @return
     */
    public int findSelectCount() {
        return forest_carareaMapper.findSelectCount();
    }

    /**
     * 新增车辆关联信息
     *
     * @return
     */
    public int insertSelective(Forest_cararea model) {
        return forest_carareaMapper.insertSelective(model);
    }

    /**
     * 查询该车位是否已经被使用
     *
     * @return
     */
    public List<Forest_cararea> selectCarSelective(Forest_cararea model) {
        return forest_carareaMapper.selectCarSelective(model);
    }

    /**
     * 删除车位关联信息
     *
     * @return
     */
    public int deleteByPrimaryKey(int model) {
        return forest_carareaMapper.deleteByPrimaryKey(model);
    }

    /**
     * 查询车辆关联 修改
     *
     * @return
     */
    public List<Forest_cararea> selectEmployees(Forest_cararea model) {
        return forest_carareaMapper.selectEmployees(model);
    }

    /**
     * 修改车辆信息
     *
     * @param model
     * @return
     */
    public int updateByPrimaryKeySelective(Forest_cararea model) {
        return forest_carareaMapper.updateByPrimaryKeySelective(model);
    }

    /**
     * 查询车辆关联 车位信息
     *
     * @return
     */
    public List<Forest_cararea> carTypeEmployee(Forest_cararea model) {
        return forest_carareaMapper.carTypeEmployee(model);
    }

    /**
     * 查询车辆关联 车位区域
     *
     * @return
     */
    public List<Forest_cararea> carAreaTypeEmployee(Forest_cararea model) {
        return forest_carareaMapper.carAreaTypeEmployee(model);
    }

    /**
     * 搜素
     *
     * @param model
     * @return
     */
    public List<Forest_cararea> floorSelectByPrimaryKeysName(Forest_cararea model) {
        return forest_carareaMapper.floorSelectByPrimaryKeysName(model);
    }

    /**
     * 统计数据
     *
     * @param model
     * @return
     */
    public int findSelectCountEnter(Forest_cararea model) {
        return forest_carareaMapper.findSelectCountEnter(model);
    }

    /**
     * 查询业主编号
     *
     * @param model
     * @return
     */
    public int findSelectCountYeZhuID(int model) {
        return forest_carareaMapper.findSelectCountYeZhuID(model);
    }

    /**
     * 出去回来状态
     *
     * @param model
     * @return
     */
    public int goComeEmployee(Forest_cararea model) {
        return forest_carareaMapper.goComeEmployee(model);
    }

    /**
     * 查询是否状态相反
     *
     * @param model
     * @return
     */
    public List<Forest_cararea> findCountEnter(Forest_cararea model) {
        return forest_carareaMapper.findCountEnter(model);
    }
}
