package com.easymall.web;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "LogOutServlet", value = "/LogOutServlet")
public class LogOutServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //处理乱码
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        if(request.getSession(false)!=null) {
            request.getSession(false).invalidate();
        }
        //清除30天自动登录
        Cookie ck=new Cookie("autologin","");
        ck.setPath("/");
        ck.setMaxAge(0);
        response.addCookie(ck);

        response.sendRedirect(request.getContextPath()+"/");
    }
}
