package com.forest.communityproperty.mapper;

import com.forest.communityproperty.entity.Forest_complaint;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Forest_complaintMapper {
    /**
     * 新增投诉信息
     * @param model
     * @return
     */
    int insertComplaint(Forest_complaint model);

    /**
     * 查询信息
     * @param model
     * @return
     */
    List<Forest_complaint> selectEmployee(Forest_complaint model);

    /**
     * 统计数据
     * @return
     */
    int findSelectCount();

    /**
     * 更改数据
     * @param model
     * @return
     */
    int updateByPrimaryKeySelective(Forest_complaint model);

    /**
     * 查询信息 姓名
     * @param model
     * @return
     */
    List<Forest_complaint> userEmployeeComplaint(Forest_complaint model);

    /**
     * 统计数据 姓名
     * @param model
     * @return
     */
    int findUserCountComplaint(Forest_complaint model);




}
