package com.forest.communityproperty.service;

import com.forest.communityproperty.entity.Forest_xitongyonghu;
import com.forest.communityproperty.mapper.Forest_xitongyonghuMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class Forest_xitongyonghuService {
    @Resource
    private Forest_xitongyonghuMapper forest_xitongyonghuMapper;

    /**
     * 按系统用户ID删除
     *
     * @return
     */
    public int deleteByPrimaryKey(Integer id) {
        return forest_xitongyonghuMapper.deleteByPrimaryKey(id);
    }

    /**
     * 按系统用户新增
     *
     * @return
     */
    public int insertSelective(Forest_xitongyonghu record) {
        return forest_xitongyonghuMapper.insertSelective(record);
    }

    /**
     * 按系统用户ID进行选择
     *
     * @return
     */
    public Forest_xitongyonghu selectByPrimaryKey(Integer id) {
        return forest_xitongyonghuMapper.selectByPrimaryKey(id);
    }

    /**
     * 按系统用户ID进行修改系统用户表
     *
     * @return
     */
    public int updateByPrimaryKeySelective(Forest_xitongyonghu record) {
        return forest_xitongyonghuMapper.updateByPrimaryKeySelective(record);
    }

    /**
     * 系统用户查询全部信息
     *
     * @return
     */
    public List<Forest_xitongyonghu> selectByPrimaryKeys() {
        return forest_xitongyonghuMapper.selectByPrimaryKeys();
    }

    /**
     * 根据用户名和密码查询用户是否存在
     *
     * @author soulmatee
     */
    public String doesTheUserExist(Forest_xitongyonghu model) {
        return forest_xitongyonghuMapper.doesTheUserExist(model);
    }
    /**
     * 根据用户名查询
     * 判断是否存在重复的用户名
     */
    public  String findUserName(Forest_xitongyonghu model){
        return  forest_xitongyonghuMapper.findUserName(model);
    }
    /**
     * 根据身份证号查询
     * 判断该用户是否注册过
     */
    public String findUserCredit(Forest_xitongyonghu model){
        return  forest_xitongyonghuMapper.findUserCredit(model);
    }

    /**
     * 根据输入的用户名和身份证号
     * 判断是否存在该用户
     */
    public String findUserNameAndSFZ(Forest_xitongyonghu model){
        return forest_xitongyonghuMapper.findUserNameAndSFZ(model);
    }

    /**
     * 根据身份证和用户名来进行重置密码
     * @param record
     * @return
     */
    public int   updateByPrimaryKey(Forest_xitongyonghu record)
    {
        return  forest_xitongyonghuMapper.updateByPrimaryKey(record);
    }
    /**
     * 管理员登录
     * @param forest_xitongyonghu
     * @return
     */
    public List<Forest_xitongyonghu> pageUserExist(Forest_xitongyonghu forest_xitongyonghu){
        return forest_xitongyonghuMapper.pageUserExist(forest_xitongyonghu);
    }

    /**
     *物业人员数据显示
     * @param m
     * @return
     */
   public List<Forest_xitongyonghu> pageUserSelect(Forest_xitongyonghu m){
       return forest_xitongyonghuMapper.pageUserSelect(m);
   }

    /**
     *物业人员数据统计
     * @return
     */
   public int findSelectCount(){
       return forest_xitongyonghuMapper.findSelectCount();
   }

    /**
     *物业人员申请数据显示
     * @return
     */
    public List<Forest_xitongyonghu> pageUserSelectUser(){
        return forest_xitongyonghuMapper.pageUserSelectUser();
    }

    /* * 删除用户信息
     * @param id
     * @return
             */
   public int deleteByPrimaryKeys(int forest_xitongyonghu){
       return  forest_xitongyonghuMapper.deleteByPrimaryKeys(forest_xitongyonghu);
   }
    /**
     * 修改状态
     * @return
     */
    public int updateSelectiveUsers(Forest_xitongyonghu forest_xitongyonghu){
        return forest_xitongyonghuMapper.updateSelectiveUsers(forest_xitongyonghu);
    }

    /**
     * 修改用户级别
     * @param forest_xitongyonghu
     * @return
     */
    public int updateSelectiveJiBie(Forest_xitongyonghu forest_xitongyonghu){
        return forest_xitongyonghuMapper.updateSelectiveJiBie(forest_xitongyonghu);
    }
    /**pageUserSelects  3
     *物业人员数据显示
     * @param m
     * @return
     */
    public List<Forest_xitongyonghu> pageUserSelects(Forest_xitongyonghu m){
        return forest_xitongyonghuMapper.pageUserSelects(m);
    }
    /**3
     *物业人员数据统计
     * @return
     */
    public int findSelectCounts(){
        return forest_xitongyonghuMapper.findSelectCounts();
    }
    /**
     * 模糊查询
     * @param forest_xitongyonghu
     * @return
     */
    public List<Forest_xitongyonghu>  pageUserSelectXtYongName(Forest_xitongyonghu forest_xitongyonghu){
        return forest_xitongyonghuMapper.pageUserSelectXtYongName(forest_xitongyonghu);
    }
    /**3
     * 模糊查询
     * @param forest_xitongyonghu
     * @return
     */
    public List<Forest_xitongyonghu>  pageUserSelectXtYongNames(Forest_xitongyonghu forest_xitongyonghu){
        return forest_xitongyonghuMapper.pageUserSelectXtYongNames(forest_xitongyonghu);
    }

}
