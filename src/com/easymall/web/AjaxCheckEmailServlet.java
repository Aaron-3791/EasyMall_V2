package com.easymall.web;

import com.easymall.dao.UserDao;
import com.easymall.domain.User;
import com.easymall.exception.MsgException;
import com.easymall.service.UserService;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@WebServlet(name = "AjaxCheckEmailServlet", value = "/AjaxCheckEmailServlet")
public class AjaxCheckEmailServlet extends HttpServlet {
    private UserDao userDao=new UserDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //处理乱码
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String eMail = request.getParameter("email");

        UserService userService =new UserService();
        try {
            userService.findUserByeMail(eMail);
            response.getWriter().write("恭喜，该邮箱可以使用！");
        }catch (MsgException e){
            response.getWriter().write(e.getMessage());
        }

        /*User findUser=userDao.findUserByeMail(eMail);
        if(findUser!=null) {
//            throw new MsgException("邮箱【" + eMail + "】已存在。");
//            request.setAttribute("msg","邮箱【" + eMail + "】已存在。");
//            request.getRequestDispatcher("/regist.jsp").forward(request,response);
//            return;
            response.getWriter().write("该邮箱已存在！");
        }else{
            response.getWriter().write("恭喜，该邮箱可以使用！");
        }*/
    }
}
