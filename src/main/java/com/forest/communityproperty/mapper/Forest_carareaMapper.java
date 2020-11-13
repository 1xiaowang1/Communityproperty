package com.forest.communityproperty.mapper;

import com.forest.communityproperty.entity.Forest_cararea;
import lombok.Data;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;
@Mapper
public interface Forest_carareaMapper {
    /**
     * 查询车辆关联
     * @return
     */
    List<Forest_cararea> selectEmployee(Forest_cararea model);

    /**
     * 查询车辆关联信息的统计数据
     * @return
     */
    int findSelectCount();
    /**
     *     新增车辆关联信息
     *     @return
     */
    int insertSelective(Forest_cararea model);
    /**
     * 查询该车位是否已经被使用
     * @return
     */
    List<Forest_cararea> selectCarSelective(Forest_cararea model);
    /**
     *     删除车位关联信息
     *     @return
     */
    int deleteByPrimaryKey(int model);
    /**
     * 查询车辆关联 修改
     * @return
     */
    List<Forest_cararea> selectEmployees(Forest_cararea model);

    /**
     * 修改车辆信息
     * @param model
     * @return
     */
    int updateByPrimaryKeySelective(Forest_cararea model);
    /**
     * 查询车辆关联 车位信息
     * @return
     */
    List<Forest_cararea> carTypeEmployee(Forest_cararea model);
    /**
     * 查询车辆关联 车位区域
     * @return
     */
    List<Forest_cararea> carAreaTypeEmployee(Forest_cararea model);

    /**
     * 搜素
     * @param model
     * @return
     */
    List<Forest_cararea> floorSelectByPrimaryKeysName(Forest_cararea model);

    /**
     * 统计数据
     * @param model
     * @return
     */
    int findSelectCountEnter(Forest_cararea model);

    /**
     * 查询业主编号
     * @param model
     * @return
     */
    int findSelectCountYeZhuID(int model);

    /**
     * 出去回来状态
     * @param model
     * @return
     */
    int goComeEmployee(Forest_cararea model);
    /**
     * 查询是否状态相反
     * @param model
     * @return
     */
    List<Forest_cararea> findCountEnter(Forest_cararea model);
}
