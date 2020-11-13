package com.forest.communityproperty.service;

import com.forest.communityproperty.entity.Forest_repairrequest;
import com.forest.communityproperty.mapper.Forest_repairrequestMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class Forest_repairrequestService {
    @Resource
    Forest_repairrequestMapper forest_repairrequestMapper;
    /**
     * 查询维修记录信息
     * @param model
     * @return
     */
    public List<Forest_repairrequest> selectEmployee(Forest_repairrequest model){
        return forest_repairrequestMapper.selectEmployee(model);
    }

    /**
     * 查询记录信息总和
     * @return
     */
    public int findSelectCount(){
        return forest_repairrequestMapper.findSelectCount();
    }

    /**
     * 新增维修记录信息
     * @param model
     * @return
     */
    public int insertSelective(Forest_repairrequest model){
        return forest_repairrequestMapper.insertSelective(model);
    }

    /**
     * 修改维修记录信息
     * @param model
     * @return
     */
    public int updateByPrimaryKeySelective(Forest_repairrequest model){
        return forest_repairrequestMapper.updateByPrimaryKeySelective(model);
    }

    /**
     * 查询维修记录信息 搜索
     * @param model
     * @return
     */
   public List<Forest_repairrequest> selectRepairEmployee(Forest_repairrequest model){
       return forest_repairrequestMapper.selectRepairEmployee(model);
   }

    /**
     * 查询记录信息总和 搜索
     * @return
     */
    public int findRepairSelectCount(){
        return forest_repairrequestMapper.findRepairSelectCount();
    }
    /**
     * 前端—修改信息
     * @param model
     * @return
     */
   public int updateSelective(Forest_repairrequest model){
       return forest_repairrequestMapper.updateSelective(model);
   }

    /**
     * 前台—查询信息
     * @param model
     * @return
     */
   public List<Forest_repairrequest> selectsEmployee(Forest_repairrequest model){
       return  forest_repairrequestMapper.selectsEmployee(model);
   }


}
