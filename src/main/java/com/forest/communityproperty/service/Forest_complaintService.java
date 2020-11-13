package com.forest.communityproperty.service;

import com.forest.communityproperty.entity.Forest_complaint;
import com.forest.communityproperty.mapper.Forest_complaintMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class Forest_complaintService {
    @Resource
    Forest_complaintMapper forest_complaintMapper;
    /**
     * 新增投诉信息
     * @param model
     * @return
     */
    public int insertComplaint(Forest_complaint model){
        return forest_complaintMapper.insertComplaint(model);
    }

    /**
     * 查询信息
     * @param model
     * @return
     */
    public List<Forest_complaint> selectEmployee(Forest_complaint model){
        return forest_complaintMapper.selectEmployee(model);
    }

    /**
     * 统计数据
     * @return
     */
    public int findSelectCount(){
        return forest_complaintMapper.findSelectCount();
    }

    /**
     * 更改数据
     * @param model
     * @return
     */
    public int updateByPrimaryKeySelective(Forest_complaint model){
        return forest_complaintMapper.updateByPrimaryKeySelective(model);
    }
    /**
     * 查询信息 姓名
     * @param model
     * @return
     */
    public List<Forest_complaint> userEmployeeComplaint(Forest_complaint model){
        return forest_complaintMapper.userEmployeeComplaint(model);
    }

    /**
     * 统计数据 姓名
     * @param model
     * @return
     */
    public int findUserCountComplaint(Forest_complaint model){
        return forest_complaintMapper.findUserCountComplaint(model);
    }
}
