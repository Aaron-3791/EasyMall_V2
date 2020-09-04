package com.easymall.filter;

import com.easymall.domain.User;
import com.easymall.exception.MsgException;
import com.easymall.service.UserService;

import javax.servlet.*;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.net.URLDecoder;

//@WebFilter(filterName = "AutoLoginFilter")
public class AutoLoginFilter implements Filter {
    private UserService userService=new UserService();

    public void destroy() {
    }

    public void doFilter(ServletRequest req, ServletResponse resp, FilterChain chain) throws ServletException, IOException {

        HttpServletRequest request = (HttpServletRequest) req;
        if (request.getSession(false) == null || request.getSession().getAttribute("user") == null) {
            Cookie[] cks = request.getCookies();
            Cookie findck = null;
            if (cks != null) {
                for (Cookie ck : cks) {
                    if ("autologin".equals(ck.getName())) {
                        findck = ck;
                    }
                }
            }

            if (findck != null) {
                String[] value = findck.getValue().split("#");
                String username = URLDecoder.decode(value[0], "utf-8");
                String password = value[1];
                //Login

                try {
                    User user = userService.userLogin(username, password);
                    request.getSession().setAttribute("user", user);
                } catch (MsgException e) {

                }
            }

        }

        chain.doFilter(req, resp);
    }

    public void init(FilterConfig config) throws ServletException {

    }

}
