package com.forest.communityproperty.mapper;

import com.forest.communityproperty.entity.Forest_roomnamemessage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Forest_roomnamemessageMapper {
    /**
     * 查询楼房信息
     *
     * @param forest_roomnamemessage
     * @return
     */
    List<Forest_roomnamemessage> selectEmployee(Forest_roomnamemessage forest_roomnamemessage);

    /**
     * 查询总和数据
     *
     * @return
     */
    int findSelectCount();

    /**
     * 删除楼房信息
     *
     * @param id
     * @return
     */
    int deleteByPrimaryKey(int id);

    /**
     * 新增楼房信息
     *
     * @param forest_roomnamemessage
     * @return
     */
    int insertSelective(Forest_roomnamemessage forest_roomnamemessage);

    /**
     * 通过楼房和房间进行查询是否存在
     *
     * @param forest_roomnamemessage
     * @return
     */
    List<Forest_roomnamemessage> selectByPrimaryKeys(Forest_roomnamemessage forest_roomnamemessage);

    /**
     * 通过关联id来查询对应信息
     *
     * @param id
     * @return
     */
    Forest_roomnamemessage selectByPrimaryKey(int id);

    /**
     * 修改信息
     *
     * @param forest_roomnamemessage
     * @return
     */
    int updateByPrimaryKeySelective(Forest_roomnamemessage forest_roomnamemessage);

    /**
     * 查询楼房信息(按楼号排序)
     *
     * @param forest_roomnamemessage
     * @return
     */
    List<Forest_roomnamemessage> selectEmploy(Forest_roomnamemessage forest_roomnamemessage);

    /**
     * 查询楼房信息(按房间号排序)
     *
     * @param forest_roomnamemessage
     * @return
     */
    List<Forest_roomnamemessage> floorSelectRoom(Forest_roomnamemessage forest_roomnamemessage);

    /**
     * 信息查询(按房间号排序)
     *
     * @param forest_roomnamemessage
     * @return
     */
    List<Forest_roomnamemessage> selectByPrimaryKeysName(Forest_roomnamemessage forest_roomnamemessage);

    /**
     * 查询总和数据
     *
     * @return
     */
    int findSelectCountEnter(Forest_roomnamemessage forest_roomnamemessage);

    /**
     * 按id查询
     *
     * @param forest_roomnamemessage
     * @return
     */
    Forest_roomnamemessage findSelectCountID(Forest_roomnamemessage forest_roomnamemessage);

}
