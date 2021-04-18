package com.forest.communityproperty.mapper;

import com.forest.communityproperty.entity.Forest_repairrequest;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Forest_repairrequestMapper {
    /**
     * 查询维修记录信息
     *
     * @param model
     * @return
     */
    List<Forest_repairrequest> selectEmployee(Forest_repairrequest model);

    /**
     * 查询记录信息总和
     *
     * @return
     */
    int findSelectCount();

    /**
     * 新增维修记录信息
     *
     * @param model
     * @return
     */
    int insertSelective(Forest_repairrequest model);

    /**
     * 修改维修记录信息
     *
     * @param model
     * @return
     */
    int updateByPrimaryKeySelective(Forest_repairrequest model);

    /**
     * 查询维修记录信息 搜索
     *
     * @param model
     * @return
     */
    List<Forest_repairrequest> selectRepairEmployee(Forest_repairrequest model);

    /**
     * 查询记录信息总和 搜索
     *
     * @return
     */
    int findRepairSelectCount();

    /**
     * 前端—修改信息
     *
     * @param model
     * @return
     */
    int updateSelective(Forest_repairrequest model);

    /**
     * 前台—查询信息
     *
     * @param model
     * @return
     */
    List<Forest_repairrequest> selectsEmployee(Forest_repairrequest model);
}
