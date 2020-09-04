package com.easymall.web;

import com.easymall.domain.User;
import com.easymall.exception.MsgException;
import com.easymall.service.UserService;
import com.easymall.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.net.URLEncoder;

@WebServlet(name = "LoginServlet", value = "/LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
//        //处理乱码
//        request.setCharacterEncoding("utf-8");
//        response.setContentType("text/html;charset=utf-8");
        //获取参数
        String username=request.getParameter("username");
        String remname=request.getParameter("remname");
        String password= WebUtils.md5(request.getParameter("password"));

        //判断处理
        if("true".equals(remname)){
            Cookie ck=new Cookie("username",username);
            ck.setPath(request.getContextPath()+"/");
            ck.setMaxAge(60*60*24*30);
            response.addCookie(ck);
        }else
        {
            Cookie ck=new Cookie("username","");
            ck.setPath(request.getContextPath()+"/");
            ck.setMaxAge(0);
            response.addCookie(ck);
        }
        //登录验证
        User user=null;
        try {
            UserService userService=new UserService();
            user=userService.userLogin(username,password);
            request.getSession().setAttribute("user",user);

            //30天自动登录
            String autologin=request.getParameter("autologin");
            if("true".equals(autologin)){
                Cookie ckautologin=new Cookie("autologin", URLEncoder.encode(username,"utf-8")+"#"+password);
                ckautologin.setPath("/");
                ckautologin.setMaxAge(60*60*24*30);
                response.addCookie(ckautologin);
            }

            response.sendRedirect(request.getContextPath()+"/");

        }catch (MsgException e){
            request.setAttribute("msg",e.getMessage());
            request.getRequestDispatcher("/login.jsp").forward(request,response);
            return;
        }



    }
}
