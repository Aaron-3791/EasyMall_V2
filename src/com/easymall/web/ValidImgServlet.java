package com.easymall.web;

import com.easymall.utils.VerifyCode;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import java.io.IOException;

@WebServlet(name = "ValidImgServlet", value = "/ValidImgServlet")
public class ValidImgServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //处理乱码
        request.setCharacterEncoding("utf-8");
        response.setContentType("text/html;charset=utf-8");

        VerifyCode vc = new VerifyCode();
        vc.drawImage(response.getOutputStream());
        String code = vc.getCode();

        Cookie ck=new Cookie("code",code);
        ck.setMaxAge(60*60*24);
        ck.setPath(request.getContextPath()+"/");
        response.addCookie(ck);



        //--将Code保存在session
        System.out.println(code);

        HttpSession session = request.getSession();
        session.setAttribute("Code",code);

        //禁止缓存
        response.setDateHeader("Expires", -1);
        response.setHeader("Cache-Control", "no-cache");
    }
}
