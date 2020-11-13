package com.forest.communityproperty.mapper;

import com.forest.communityproperty.entity.Forest_xitongyonghu;
import org.apache.ibatis.annotations.Mapper;

import java.util.List;

@Mapper
public interface Forest_xitongyonghuMapper {
    /**
     * 按系统用户ID删除
     *
     * @return
     */
    int deleteByPrimaryKey(Integer id);

    /**
     * 按系统用户新增
     *
     * @return
     */
    int insertSelective(Forest_xitongyonghu record);

    /**
     * 按系统用户ID进行选择
     *
     * @return
     */
    Forest_xitongyonghu selectByPrimaryKey(Integer id);

    /**
     * 按系统用户ID进行修改系统用户表
     *
     * @return
     */
    int updateByPrimaryKeySelective(Forest_xitongyonghu record);

    /**
     * 系统用户查询全部信息
     *
     * @return
     */
    List<Forest_xitongyonghu> selectByPrimaryKeys();

    /**
     * 根据用户名和密码查询用户是否存在
     * @author soulmatee
     */
    String doesTheUserExist(Forest_xitongyonghu model);
    /**
     * 根据用户名查询
     * 判断是否存在重复的用户名
     */
    String findUserName(Forest_xitongyonghu model);
    /**
     * 根据身份证号查询
     * 判断该用户是否注册过
     */
    String findUserCredit(Forest_xitongyonghu model);

    /**
     * 根据输入的用户名和身份证号
     * 判断是否存在该用户
     */
    String findUserNameAndSFZ(Forest_xitongyonghu model);

    /**
     * 根据身份证和用户名来进行重置密码
     * @param record
     * @return
     */
    int   updateByPrimaryKey(Forest_xitongyonghu record);

    /**
     * 管理员登录
     * @param forest_xitongyonghu
     * @return
     */
    List<Forest_xitongyonghu> pageUserExist(Forest_xitongyonghu forest_xitongyonghu);

    /**2
     *物业人员数据显示
     * @param m
     * @return
     */
    List<Forest_xitongyonghu> pageUserSelect(Forest_xitongyonghu m);
    /**3
     *物业人员数据显示
     * @param m
     * @return
     */
    List<Forest_xitongyonghu> pageUserSelects(Forest_xitongyonghu m);
    /**2
     *物业人员数据统计
     * @return
     */
    int findSelectCount();
    /**3
     *物业人员数据统计
     * @return
     */
    int findSelectCounts();
    /**
     *物业人员申请数据显示
     * @return
     */
    List<Forest_xitongyonghu> pageUserSelectUser();

    /**
     * 删除用户信息
     * @return
     */
    int deleteByPrimaryKeys(int forest_xitongyonghu);
    /**
     * 修改状态
     * @return
     */
    int updateSelectiveUsers(Forest_xitongyonghu forest_xitongyonghu);

    /**
     * 修改用户级别
     * @param forest_xitongyonghu
     * @return
     */
    int updateSelectiveJiBie(Forest_xitongyonghu forest_xitongyonghu);

    /**2
     * 模糊查询
     * @param forest_xitongyonghu
     * @return
     */
    List<Forest_xitongyonghu>  pageUserSelectXtYongName(Forest_xitongyonghu forest_xitongyonghu);
    /**3
     * 模糊查询
     * @param forest_xitongyonghu
     * @return
     */
    List<Forest_xitongyonghu>  pageUserSelectXtYongNames(Forest_xitongyonghu forest_xitongyonghu);
}
