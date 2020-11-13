package com.forest.communityproperty.service;

import com.forest.communityproperty.entity.Forest_ordermanage;
import com.forest.communityproperty.mapper.Forest_ordermanageMapper;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Service
public class Forest_ordermanageService {
    @Resource
    Forest_ordermanageMapper forest_ordermanageMapper;
    /**
     * 查询已缴费
     * @param model
     * @return
     */
    public List<Forest_ordermanage> selectEmployeeState(Forest_ordermanage model){
        return  forest_ordermanageMapper.selectEmployeeState(model);
    }

    /**
     * 查询未缴费
     * @param model
     * @return
     */
    public List<Forest_ordermanage> selectOrderState(Forest_ordermanage model){
        return forest_ordermanageMapper.selectOrderState(model);
    }
    /**
     * 统计缴费信息
     * @return
     */
    public int findSelectCount(){
        return forest_ordermanageMapper.findSelectCount();
    }

    /**
     * 统计未缴费信息
     * @return
     */
    public int findOrderSelectCount(Forest_ordermanage model){
        return forest_ordermanageMapper.findOrderSelectCount(model);
    }
    /**
     * 查询未缴费的搜索
     * @param model
     * @return
     */
    public List<Forest_ordermanage> selectByPrimaryKeysName(Forest_ordermanage model){
        return  forest_ordermanageMapper.selectByPrimaryKeysName(model);
    }
    /**
     * 统计未缴费的搜索
     * @param model
     * @return
     */
    public int findMessageSelectCount(Forest_ordermanage model){
        return forest_ordermanageMapper.findMessageSelectCount(model);
    }
    /**
     * 新增订单信息
     * @return
     */
    public int insertSelective(Forest_ordermanage forest_ordermanage){
        return  forest_ordermanageMapper.insertSelective(forest_ordermanage);
    }


    /**
     * 查询订单新的编号
     * ~
     * @param forest_ordermanage
     * @return
     */
    public List<Forest_ordermanage> selectOrderKey(Forest_ordermanage forest_ordermanage){
        return forest_ordermanageMapper.selectOrderKey(forest_ordermanage);
    }

    /**
     * 支付成功
     * @param forest_ordermanage
     * @return
     */
    public int updateRegisterSelective(Forest_ordermanage forest_ordermanage){
        return forest_ordermanageMapper.updateRegisterSelective(forest_ordermanage);
    }

    /**
     * 支付失败
     * @param id
     * @return
     */
    public int deleteByPrimaryKey(int id){
        return  forest_ordermanageMapper.deleteByPrimaryKey(id);
    }
    /**
     * 总收益
     * @return
     */
   public ArrayList<Map<String,Object>> selectSumOrderKey(){
       return forest_ordermanageMapper.selectSumOrderKey();
   }
    /**
     * 单收益
     * @return
     */
    public  List<Forest_ordermanage> selectsumOrderKey(){
        return forest_ordermanageMapper.selectsumOrderKey();
    }
}
