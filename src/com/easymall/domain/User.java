package com.easymall.domain;

import com.sun.javafx.scene.web.Debugger;
import javafx.util.Callback;
import org.apache.log4j.Logger;

import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * User Bean
 */
public class User implements HttpSessionBindingListener {
    private int id;
    private String username;
    private String password;
    private String nickname;
    private String email;

    public static Logger logger = Logger.getLogger(User.class);

    public User() {
    }

    public User(int id, String username, String password, String nickname, String email) {
        this.id = id;
        this.username = username;
        this.password = password;
        this.nickname = nickname;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }


    @Override
    public void valueBound(HttpSessionBindingEvent httpSessionBindingEvent) {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        logger.info("用户【"+username+"】已经登录 --- "+sdf.format(new Date())+" --- "+httpSessionBindingEvent.getName());
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent httpSessionBindingEvent) {
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        logger.info("用户【"+username+"】已经注销 --- "+sdf.format(new Date())+" --- "+httpSessionBindingEvent.getValue());
    }
}
