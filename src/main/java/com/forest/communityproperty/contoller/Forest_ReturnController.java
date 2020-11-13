package com.forest.communityproperty.contoller;

import com.forest.communityproperty.entity.Forest_ordermanage;
import com.forest.communityproperty.service.Forest_ordermanageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

@Controller
public class Forest_ReturnController {
    @Autowired
    Forest_ordermanageService forest_ordermanageService;

    @RequestMapping("/successZhiFu")
    public String returnPay(HttpServletRequest request) {
        System.out.println("成功接收到API返回的同步信息");
        Forest_ordermanage oreder = new Forest_ordermanage();
        HttpSession session = request.getSession();
        String Order_no= (String) session.getAttribute("order");
        System.out.println(Order_no);
        oreder.setOrderID(Integer.parseInt(Order_no));
        oreder.setOrderState(1);
        String u;
        //如果支付成功则修改其支付状态
        u = "恭喜你支付成功！！";
        forest_ordermanageService.updateRegisterSelective(oreder);
        return "contact.html";
    }


    @RequestMapping("/TuiChu")
    public String TuiChu(HttpServletRequest request,HttpSession session) {
        session.removeAttribute("username");
        session.removeAttribute("userId");
        return "web-index.html";
    }
}
