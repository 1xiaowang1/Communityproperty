package com.forest.communityproperty.global;


import com.forest.communityproperty.entity.Forest_xitongyonghu;
import com.forest.communityproperty.entity.Forest_yezhumessage;
import com.forest.communityproperty.global.Impl.variableImpl;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

public class Forest_variable implements variableImpl {

    @Override
    public String variableName(HttpSession session, Forest_xitongyonghu model) {
        //存储用户名
        session.setAttribute("name", model.getXtYongUserName());
        System.out.println(model.getXtYongHuID());
        session.setAttribute("id", model.getXtYongHuID());
        //2个小时
        session.setMaxInactiveInterval(2 * 60 * 60);
        return null;
    }

    //七天内免登录
    @Override
    public String variableNameTime(HttpSession session, Forest_xitongyonghu model) {
        session.setAttribute("name", model.getXtYongUserName());
        session.setAttribute("id", model.getXtYongHuID());
        session.setMaxInactiveInterval(7 * 24 * 60 * 60);
        return null;
    }

    /**
     * 如果500表示没有名字
     * 如果200表示有名字
     *
     * @param request
     * @return
     */
    @Override
    public int variableNameSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("name");
        if (name == null) {
            return 500;
        }
        return 200;
    }

    public String sessionName(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("name");
        return name;
    }


    @Override
    public String variableYeZhuName(HttpSession session, Forest_yezhumessage model) {
        //存储用户名
        session.setAttribute("username", model.getYeZhuName());
        session.setAttribute("userId", model.getYeZhuID());
        System.out.println(model.getYeZhuID());
        //2个小时
        session.setMaxInactiveInterval(7 * 24 * 60 * 60);
        return null;
    }

    public int sessionID(HttpServletRequest request) {
        HttpSession session = request.getSession();
        Object userId = session.getAttribute("userId");
        System.out.println(userId);
        int u = (int) userId;
        return u;
    }

    /**
     * 如果500表示没有名字
     * 如果200表示有名字
     *
     * @param request
     * @return
     */
    @Override
    public int variableYeZhuNameSession(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("username");
        if (name == null) {
            return 500;
        }
        return 200;
    }

    public String variableYeZhuName(HttpServletRequest request) {
        HttpSession session = request.getSession();
        String name = (String) session.getAttribute("username");
        return name;
    }

}
