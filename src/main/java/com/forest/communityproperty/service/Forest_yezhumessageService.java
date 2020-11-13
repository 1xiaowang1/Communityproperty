package com.forest.communityproperty.service;

import com.forest.communityproperty.entity.Forest_yezhumessage;
import com.forest.communityproperty.mapper.Forest_yezhumessageMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;

@Service
public class Forest_yezhumessageService {

    @Resource
    private Forest_yezhumessageMapper forest_yezhumessageMapper;

    /**
     * 按业主信息新增
     *
     * @return
     */
    public int insertSelective(Forest_yezhumessage record){
        return forest_yezhumessageMapper.insertSelective(record);
    }
    /**
     * 按业主信息查询
     *
     * @return
     */
    public List<Forest_yezhumessage> selectByPrimaryKeys(Forest_yezhumessage record){
        return forest_yezhumessageMapper.selectByPrimaryKeys(record);
    }

    /**
     * 统计数据
     *
     * @return
     */
    public int findSelectCount(){
        return forest_yezhumessageMapper.findSelectCount();
    }
    /**
     * 删除业主信息
     * @param id
     * @return
     */
    public int deleteByPrimaryKey(int id){
        return forest_yezhumessageMapper.deleteByPrimaryKey(id);
    }
    /**
     * 按业主身份信息
     * @return
     */
    public String findUserCredit(String yeZhuSFZ){
        return forest_yezhumessageMapper.findUserCredit(yeZhuSFZ);
    }
    /**
     * 按业主id查询
     * @param id
     * @return
     */
    public List<Forest_yezhumessage> selectByPrimaryKey(int id){
        return  forest_yezhumessageMapper.selectByPrimaryKey(id);
    }
    /**
     * 修改业主信息
     * @param record
     * @return
     */
    public int updateByPrimaryKeySelective(Forest_yezhumessage record)
    {
        return forest_yezhumessageMapper.updateByPrimaryKeySelective(record);
    }
    /**
     * 查询按姓名业主的统计数据
     * @param yeZhuName
     * @return
     */
    public int findSelectCountName (String yeZhuName){
        return  forest_yezhumessageMapper.findSelectCountName(yeZhuName);
    }

    /**
     * 按业主信息查询 按姓名
     *分页查询
     * @return
     */
    public  List<Forest_yezhumessage> selectByPrimaryKeysName(Forest_yezhumessage record){
        return forest_yezhumessageMapper.selectByPrimaryKeysName(record);
    }

    /**
     * 查询业主身份证号  返回业主编号
     * @param yeZhuSFZ
     * @return
     */
    public int findYeZhuCredit(String yeZhuSFZ){
        return forest_yezhumessageMapper.findYeZhuCredit(yeZhuSFZ);
    }
    /**
     * 修改业主信息
     * @param forest_yezhumessage
     * @return
     */
    public int updateRegisterSelective(Forest_yezhumessage forest_yezhumessage){
        return forest_yezhumessageMapper.updateRegisterSelective(forest_yezhumessage);
    }
    /**
     * 检测业主的登录名和密码
     * @param forest_yezhumessage
     * @return
     */
    public List<Forest_yezhumessage> findYeZhuUserName(Forest_yezhumessage forest_yezhumessage){
        return forest_yezhumessageMapper.findYeZhuUserName(forest_yezhumessage);
    }
}
