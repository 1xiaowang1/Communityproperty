package com.forest.communityproperty.contoller;

import com.forest.communityproperty.entity.Forest_carmessage;
import com.forest.communityproperty.entity.Forest_cartype;
import com.forest.communityproperty.service.Forest_carmessageService;
import com.forest.communityproperty.service.Forest_cartypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class Forest_carmessageController {
    private Map<String, Object> map = new HashMap<>();
    @Autowired
    Forest_carmessageService forest_carmessageService;

    /**
     * 查询车辆信息
     */
    public List<Forest_carmessage> selectEmployee() {
        //查询车辆信息
        List<Forest_carmessage> list = forest_carmessageService.selectEmployee();
        return list;
    }

    /**
     * 新增车辆信息
     *
     * @param forest_carmessage
     * @return
     */
    public int insertSelective(Forest_carmessage forest_carmessage) {
        int mNum = 0;
        //设置时间的格式
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获取时间的方法
        Date d = new Date();
        //将获取的时间转换成设置的时间格式进行存储
        forest_carmessage.setCatDate(sf.format(d));
        //新增车辆信息
        int i = forest_carmessageService.insertSelective(forest_carmessage);
        if (i == 1) {
            //查询车辆信息
            List<Forest_carmessage> list = forest_carmessageService.selectEmployee();
            //取出新增的车辆编号
            mNum = list.get(0).getCarID();

        }
        return mNum;
    }

    /**
     * 上传图片
     */
    public int updateByPrimaryImage(Forest_carmessage forest_carmessage) {
        int sNum = forest_carmessageService.updateByPrimaryImage(forest_carmessage);
        return sNum;
    }

    /**
     * 删除车辆信息
     */
    public int deleteByPrimaryKey(int forest_carmessage) {
        int sNum = forest_carmessageService.deleteByPrimaryKey(forest_carmessage);
        return sNum;
    }

    /**
     * 修改车辆信息
     *
     * @param forest_carmessage
     * @return
     */
    public int updateByPrimaryKeySelective(Forest_carmessage forest_carmessage) {
        //设置时间的格式
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //获取时间的方法
        Date d = new Date();
        //将获取的时间转换成设置的时间格式进行存储
        forest_carmessage.setCatDate(sf.format(d));
        //修改车辆信息
        int mNum = forest_carmessageService.updateByPrimaryKeySelective(forest_carmessage);
        return mNum;
    }
}
