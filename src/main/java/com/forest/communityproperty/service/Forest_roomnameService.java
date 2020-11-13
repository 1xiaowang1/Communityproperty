package com.forest.communityproperty.service;

import com.forest.communityproperty.entity.Forest_roomname;
import com.forest.communityproperty.mapper.Forest_roomnameMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class Forest_roomnameService {

    @Resource
    Forest_roomnameMapper forest_roomnameMapper;
    /**
     * 查询楼房信息
     * @return
     */
    public List<Forest_roomname> selectEmployee(){
        return forest_roomnameMapper.selectEmployee();
    }
    /**
     * 新增楼房信息
     * @param forest_roomname
     * @return
     */
    public int insertSelective(Forest_roomname forest_roomname){
        return forest_roomnameMapper.insertSelective(forest_roomname);
    }

    /**
     * 删除楼房信息
     * @param forest_roomname
     * @return
     */
   public int deleteByPrimaryKeys(Forest_roomname forest_roomname ){
       return forest_roomnameMapper.deleteByPrimaryKeys(forest_roomname);
   }
}
