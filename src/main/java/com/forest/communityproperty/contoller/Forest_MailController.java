package com.forest.communityproperty.contoller;


import com.forest.communityproperty.entity.Forest_yezhumessage;
import com.forest.communityproperty.entity.Forest_MailEntity;
import com.forest.communityproperty.service.Forest_MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class Forest_MailController {
    private Map<String, Object> map = new HashMap<>();

    @Autowired
    Forest_MailService mailService;
    @Autowired
    Forest_yezhumessageController forest_yezhumessageController;

    @RequestMapping("goEmailUpload")
    public Map<String, Object> sendSimpleMail(@RequestBody Forest_MailEntity mails) throws Exception {
        //获取用户编号  获取业主的信息
        List<Forest_yezhumessage> list = forest_yezhumessageController.selectByPrimaryKeyID(mails.getId());
        //系统发送账号
        mails.setFrom("wh508377132@163.com");
        //发送邮件内容
        mails.setContent("请你尽快将本月的费用进行缴费！！！");
        //发送邮件标题
        mails.setSubject("缴费通知：");
        //发送邮件收件人的账号
        mails.setTo(list.get(0).getYeZhuEmail());
        //邮件接收人的姓名
        mails.setName(list.get(0).getYeZhuName());
        //邮件的发送的调用函数
        mailService.sendSimpleMail(mails.getFrom(), mails.getTo(), mails.getTo(), mails.getSubject(), mails.getContent(), mails.getName());
        //返回值
        map.put("code", 200);
        return map;
    }

    @RequestMapping("goEmail")
    public Map<String, Object> goEmail(@RequestBody Forest_MailEntity mails) throws Exception {
        int num = (int) ((Math.random() * 9 + 1) * 100000);
        //获取用户编号  获取业主的信息
        List<Forest_yezhumessage> list = forest_yezhumessageController.selectByPrimaryKeyID(mails.getId());
        //系统发送账号
        mails.setFrom("wh508377132@163.com");
        //发送邮件内容
        String names = String.valueOf(num);
        mails.setContent("请尽快输入验证码：" + names);
        System.out.println(names);
        //发送邮件标题
        mails.setSubject("重要通知：");
        if (mails.getCc().equals(list.get(0).getYeZhuEmail())) {
            //发送邮件收件人的账号
            mails.setTo(list.get(0).getYeZhuEmail());
            //邮件接收人的姓名
            mails.setName(list.get(0).getYeZhuName());
            //邮件的发送的调用函数
            mailService.sendSimpleMail(mails.getFrom(), mails.getTo(), mails.getTo(), mails.getSubject(), mails.getContent(), mails.getName());
            //返回值
            map.put("code", 200);
            map.put("emailNum", names);
            return map;
        }
        map.put("code", 400);
        return map;
    }


}
