package com.forest.communityproperty.service;

import com.forest.communityproperty.entity.Forest_roomnamemessage;
import com.forest.communityproperty.mapper.Forest_roomnamemessageMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class Forest_roomnamemessageService {

    @Resource
    Forest_roomnamemessageMapper forest_roomnamemessageMapper;

    /**
     * 查询楼房信息
     *
     * @param forest_roomnamemessage
     * @return
     */
    public List<Forest_roomnamemessage> selectEmployee(Forest_roomnamemessage forest_roomnamemessage) {
        return forest_roomnamemessageMapper.selectEmployee(forest_roomnamemessage);
    }

    /**
     * 查询总和数据
     *
     * @return
     */
    public int findSelectCount() {
        return forest_roomnamemessageMapper.findSelectCount();
    }

    /**
     * 删除楼房信息
     *
     * @param id
     * @return
     */
    public int deleteByPrimaryKey(int id) {
        return forest_roomnamemessageMapper.deleteByPrimaryKey(id);
    }

    /**
     * 新增楼房信息
     *
     * @param forest_roomnamemessage
     * @return
     */
    public int insertSelective(Forest_roomnamemessage forest_roomnamemessage) {
        return forest_roomnamemessageMapper.insertSelective(forest_roomnamemessage);
    }

    /**
     * 通过楼房和房间进行查询是否存在
     *
     * @param forest_roomnamemessage
     * @return
     */
    public List<Forest_roomnamemessage> selectByPrimaryKeys(Forest_roomnamemessage forest_roomnamemessage) {
        return forest_roomnamemessageMapper.selectByPrimaryKeys(forest_roomnamemessage);
    }

    /**
     * 通过关联id来查询对应信息
     *
     * @param id
     * @return
     */
    public Forest_roomnamemessage selectByPrimaryKey(int id) {
        return forest_roomnamemessageMapper.selectByPrimaryKey(id);
    }

    /**
     * 修改信息
     *
     * @param forest_roomnamemessage
     * @return
     */
    public int updateByPrimaryKeySelective(Forest_roomnamemessage forest_roomnamemessage) {
        return forest_roomnamemessageMapper.updateByPrimaryKeySelective(forest_roomnamemessage);
    }

    /**
     * 查询楼房信息(按楼号排序)
     *
     * @param forest_roomnamemessage
     * @return
     */
    public List<Forest_roomnamemessage> selectEmploy(Forest_roomnamemessage forest_roomnamemessage) {
        return forest_roomnamemessageMapper.selectEmploy(forest_roomnamemessage);
    }

    /**
     * 查询楼房信息(按房间号排序)
     *
     * @param forest_roomnamemessage
     * @return
     */
    public List<Forest_roomnamemessage> floorSelectRoom(Forest_roomnamemessage forest_roomnamemessage) {
        return forest_roomnamemessageMapper.floorSelectRoom(forest_roomnamemessage);
    }

    /**
     * 信息查询(按房间号排序)
     *
     * @param forest_roomnamemessage
     * @return
     */
    public List<Forest_roomnamemessage> selectByPrimaryKeysName(Forest_roomnamemessage forest_roomnamemessage) {
        return forest_roomnamemessageMapper.selectByPrimaryKeysName(forest_roomnamemessage);
    }
    /**
     * 查询总和数据
     * @return
     */
     public int findSelectCountEnter(Forest_roomnamemessage forest_roomnamemessage)
     {
         return forest_roomnamemessageMapper.findSelectCountEnter(forest_roomnamemessage);
     }

    /**
     * 按id查询
     * @param forest_roomnamemessage
     * @return
     */
    public Forest_roomnamemessage findSelectCountID(Forest_roomnamemessage forest_roomnamemessage)
    {
       return forest_roomnamemessageMapper.findSelectCountID(forest_roomnamemessage);
    }

}
