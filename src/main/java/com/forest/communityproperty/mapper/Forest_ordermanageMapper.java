package com.forest.communityproperty.mapper;

import com.forest.communityproperty.entity.Forest_ordermanage;
import org.apache.ibatis.annotations.Mapper;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Mapper
public interface Forest_ordermanageMapper {
    /**
     * 查询已缴费
     * @param model
     * @return
     */
    List<Forest_ordermanage> selectEmployeeState(Forest_ordermanage model);

    /**
     * 查询未缴费
     * @param model
     * @return
     */
    List<Forest_ordermanage> selectOrderState(Forest_ordermanage model);

    /**
     * 统计已缴费信息
     * @return
     */
    int findSelectCount();

    /**
     * 统计未缴费信息
     * @return
     */
    int findOrderSelectCount(Forest_ordermanage model);

    /**
     * 查询未缴费的搜索
     * @param model
     * @return
     */
    List<Forest_ordermanage> selectByPrimaryKeysName(Forest_ordermanage model);

    /**
     * 统计未缴费的搜索
     * @param model
     * @return
     */
    int findMessageSelectCount(Forest_ordermanage model);

    /**
     * 新增订单信息
     * @return
     */
    int insertSelective(Forest_ordermanage forest_ordermanage);

    /**
     * 查询订单新的编号
 * ~
     * @param forest_ordermanage
     * @return
     */
    List<Forest_ordermanage> selectOrderKey(Forest_ordermanage forest_ordermanage);

    /**
     * 支付成功
     * @param forest_ordermanage
     * @return
     */
    int updateRegisterSelective(Forest_ordermanage forest_ordermanage);

    /**
     * 支付失败
     * @param id
     * @return
     */
    int deleteByPrimaryKey(int id);

    /**
     * 总收益
     * @return
     */
    ArrayList<Map<String,Object>> selectSumOrderKey();

    /**
     * 单收益
     * @return
     */
    List<Forest_ordermanage> selectsumOrderKey();
}
