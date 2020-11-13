package com.forest.communityproperty.mapper;

import com.forest.communityproperty.entity.Forest_yezhumessage;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Forest_yezhumessageMapper {
    /**
     * 按业主信息新增
     *
     * @return
     */
    int insertSelective(Forest_yezhumessage record);
    /**
     * 按业主信息查询
     *分页查询
     * @return
     */
    List<Forest_yezhumessage> selectByPrimaryKeys(Forest_yezhumessage record);
    /**
     * 统计数据
     *
     * @return
     */
    int findSelectCount();

    /**
     * 删除业主信息
     * @param id
     * @return
     */
    int deleteByPrimaryKey(int id);

    /**
     * 按业主身份信息
     * @return
     */
    String findUserCredit(String yeZhuSFZ);

    /**
     * 按业主id查询
     * @param id
     * @return
     */
    List<Forest_yezhumessage> selectByPrimaryKey(int id);

    /**
     * 修改业主信息
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(Forest_yezhumessage record);

    /**
     * 查询按姓名业主的统计数据
     * @param yeZhuName
     * @return
     */
      int findSelectCountName(String yeZhuName);
    /**
     * 按业主信息查询 按姓名
     *分页查询
     * @return
     */
    List<Forest_yezhumessage> selectByPrimaryKeysName(Forest_yezhumessage record);

    /**
     * 查询业主的身份证
     * @param yeZhuSFZ
     * @return
     */
    int findYeZhuCredit(String yeZhuSFZ);

    /**
     * 修改业主信息
     * @param forest_yezhumessage
     * @return
     */
    int updateRegisterSelective(Forest_yezhumessage forest_yezhumessage);

    /**
     * 检测业主的登录名和密码
     * @param forest_yezhumessage
     * @return
     */
    List<Forest_yezhumessage> findYeZhuUserName(Forest_yezhumessage forest_yezhumessage);
}
