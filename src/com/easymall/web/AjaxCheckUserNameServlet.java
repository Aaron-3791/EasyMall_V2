package com.easymall.web;

import com.easymall.dao.UserDao;
import com.easymall.domain.User;
import com.easymall.exception.MsgException;
import com.easymall.service.UserService;
import com.easymall.utils.WebUtils;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "AjaxCheckUserNameServlet", value = "/AjaxCheckUserNameServlet")
public class AjaxCheckUserNameServlet extends HttpServlet {
    private UserDao userDao=new UserDao();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //处理乱码
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        String username = request.getParameter("username");

        UserService userService =new UserService();
        try {
            userService.findUserByUserName(username);
            response.getWriter().write("恭喜，该用户名可以使用！");
        }catch (MsgException e){
            response.getWriter().write(e.getMessage());
        }

        /*User findUser=userDao.findUserByUserName(username);
        if(findUser!=null) {
            //throw new MsgException("用户名【" + username + "】已存在。");
//            request.setAttribute("msg","用户名【" + username + "】已存在。");
//            request.getRequestDispatcher("/regist.jsp").forward(request,response);
//            return;
            response.getWriter().write("该用户名已存在。");
        }else
        {
            response.getWriter().write("恭喜，该用户名可以使用！");
        }*/
    }
}
