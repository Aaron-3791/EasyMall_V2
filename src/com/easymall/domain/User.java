package com.easymall.domain;

import com.sun.javafx.scene.web.Debugger;
import javafx.util.Callback;
import org.apache.log4j.Logger;

/**
 * User Bean
 *
 */
public class User {
    private int id;
    private String username;
    private String password;
    private String nickname;
    private String email;

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

    public static Logger logger=Logger.getLogger(User.class);


}