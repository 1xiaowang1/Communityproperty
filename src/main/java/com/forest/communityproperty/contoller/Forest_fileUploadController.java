package com.forest.communityproperty.contoller;

import com.forest.communityproperty.entity.Forest_carmessage;
import com.forest.communityproperty.entity.Forest_repairrequest;
import com.forest.communityproperty.global.Forest_variable;
import com.forest.communityproperty.service.Forest_repairrequestService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//D:\images\
@RestController
public class Forest_fileUploadController {

    public Forest_carmessage forest_carmessage = new Forest_carmessage();
    private Forest_repairrequest forest_repairrequest = new Forest_repairrequest();
    @Autowired
    Forest_carmessageController forest_carmessageController;
    @Autowired
    Forest_repairrequestService forest_repairrequestService;

    /**
     * 将图片存在对应文件中
     */
    @RequestMapping("upload")
    public Map fileUpload(@RequestParam MultipartFile file, String carID, HttpServletRequest request) {
        Map map = new HashMap();
        boolean rs = false;
        if (!file.isEmpty()) {
            String fileName = System.currentTimeMillis() + file.getOriginalFilename();
            forest_carmessage.setCarID(Integer.parseInt(carID));
            forest_carmessage.setCarImage(fileName);
            forest_carmessageController.updateByPrimaryImage(forest_carmessage);
            String savePath = "/www/server/SpringBoot/wh/XiaoQuWuYe/images/";
            File dest = new File(savePath + File.separator + fileName);
            try {
                file.transferTo(dest);
                rs = true;
                map.put("result", rs);
            } catch (IOException e) {
                e.printStackTrace();
                map.put("result", rs);
            }
        } else if (file.isEmpty()) {
            map.put("result", rs);
        }
        return map;
    }

    /**
     * 将图片存在对应文件中
     */
    @RequestMapping(value = "uploadRepair", method = RequestMethod.POST)
    public Map uploadRepair(@RequestParam MultipartFile file, HttpServletRequest request) {
        Map map = new HashMap();
        boolean rs = false;
        if (!file.isEmpty()) {
            //图片名称
            String fileName = System.currentTimeMillis() + file.getOriginalFilename();
            forest_repairrequest.setRepairImage(fileName);
            //业主的编号
            int yeZhuID = new Forest_variable().sessionID(request);
            forest_repairrequest.setYeZhuID(yeZhuID);
            //设置时间的格式
            SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
            //获取时间的方法
            Date d = new Date();
            //将获取的时间转换成设置的时间格式进行存储
            forest_repairrequest.setShenQingShiJian(sf.format(d));
            //维修申请状态
            forest_repairrequest.setRepairState(1);
            //新增维修申请
            forest_repairrequestService.insertSelective(forest_repairrequest);
            //查询新增的维修申请
            List<Forest_repairrequest> list = forest_repairrequestService.selectsEmployee(forest_repairrequest);
            //存储新增的维修申请
            map.put("num", list.get(0).getRepairID());
            //图片位置
            String savePath = "/www/server/SpringBoot/wh/XiaoQuWuYe/images/";
            //拼接
            File dest = new File(savePath + File.separator + fileName);
            try {
                file.transferTo(dest);
                rs = true;
                map.put("result", rs);
            } catch (IOException e) {
                e.printStackTrace();
                map.put("result", rs);
            }
        } else if (file.isEmpty()) {
            map.put("result", rs);
        }
        return map;
    }

}
