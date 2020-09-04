package com.easymall.listener;

import com.easymall.domain.User;
import org.apache.log4j.Logger;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.*;
import java.text.SimpleDateFormat;
import java.util.Date;

@WebListener()
public class MyServletRequestListener implements ServletRequestListener {

    public static Logger logger = Logger.getLogger(MyServletRequestListener.class);

    // Public constructor is required by servlet spec
    public MyServletRequestListener() {
    }

    // -------------------------------------------------------
    // ServletContextListener implementation
    // -------------------------------------------------------
    public void contextInitialized(ServletContextEvent sce) {
      /* This method is called when the servlet context is
         initialized(when the Web application is deployed). 
         You can initialize servlet context related data here.
      */
    }

    public void contextDestroyed(ServletContextEvent sce) {
      /* This method is invoked when the Servlet Context 
         (the Web application) is undeployed or 
         Application Server shuts down.
      */
    }

    // -------------------------------------------------------
    // HttpSessionListener implementation
    // -------------------------------------------------------
    public void sessionCreated(HttpSessionEvent se) {
        /* Session is created. */
    }

    public void sessionDestroyed(HttpSessionEvent se) {
        /* Session is destroyed. */
    }

    // -------------------------------------------------------
    // HttpSessionAttributeListener implementation
    // -------------------------------------------------------

    public void attributeAdded(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute 
         is added to a session.
      */
    }

    public void attributeRemoved(HttpSessionBindingEvent sbe) {
      /* This method is called when an attribute
         is removed from a session.
      */
    }

    public void attributeReplaced(HttpSessionBindingEvent sbe) {
      /* This method is invoked when an attibute
         is replaced in a session.
      */
    }

    @Override
    public void requestDestroyed(ServletRequestEvent servletRequestEvent) {
        HttpServletRequest request = (HttpServletRequest) servletRequestEvent.getServletRequest();
        String username = "Guest";
        if (request.getSession(false) != null && request.getSession().getAttribute("user") != null) {
            User user = (User) request.getSession().getAttribute("user");
            username = user.getUsername();
        }
        String ip = request.getRemoteAddr();
        String url = request.getRequestURL().toString();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        logger.info("用户【" + username + "】:" + ip + ":" + url + " -- 访问结束 --- " + sdf.format(new Date()));
    }

    @Override
    public void requestInitialized(ServletRequestEvent servletRequestEvent) {
        HttpServletRequest request = (HttpServletRequest) servletRequestEvent.getServletRequest();
        String username = "Guest";
        if (request.getSession(false) != null && request.getSession().getAttribute("user") != null) {
            User user = (User) request.getSession().getAttribute("user");
            username = user.getUsername();
        }
        String ip = request.getRemoteAddr();
        String url = request.getRequestURL().toString();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        logger.info("用户【" + username + "】:" + ip + ":" + url + " -- 开始访问 --- " + sdf.format(new Date()));
    }
}
