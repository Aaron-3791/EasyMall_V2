package com.easymall.dao;

import com.easymall.domain.User;
import com.easymall.utils.WebUtils;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class UserDao {
    /**
     * @param username
     * @param password
     * @return
     */
    public User findUserByUserNameAndPasswd(String username, String password) {
        //检查用户名密码是否正确
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = WebUtils.getConnection();
            ps = conn.prepareStatement("select * from user where username=? and password=?");
            ps.setString(1, username);
            ps.setString(2, password);
            rs = ps.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("email"));
                user.setNickname(rs.getString("nickname"));
                user.setPassword(rs.getString("password"));
                user.setUsername(rs.getString("username"));
                return user;
            } else {
                return null;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);

        } finally {
            WebUtils.close(conn, ps, rs);
        }
    }

    /**
     * @param username
     * @return
     */
    public User findUserByUserName(String username) {
        //检查是否已存在用户
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = WebUtils.getConnection();
            ps = conn.prepareStatement("select * from user where username=?");
            ps.setString(1, username);
            rs = ps.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("email"));
                user.setNickname(rs.getString("nickname"));
                user.setPassword(rs.getString("password"));
                user.setUsername(rs.getString("username"));
                return user;
            } else {
                return null;
            }


        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);

        } finally {
            WebUtils.close(conn, ps, rs);
        }
    }

    /**
     * @param email
     * @return
     */
    public User findUserByeMail(String email) {
        //检查是否已存在邮箱
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = WebUtils.getConnection();
            ps = conn.prepareStatement("select * from user where email=?");
            ps.setString(1, email);
            rs = ps.executeQuery();
            if (rs.next()) {
                User user = new User();
                user.setId(rs.getInt("id"));
                user.setEmail(rs.getString("email"));
                user.setNickname(rs.getString("nickname"));
                user.setPassword(rs.getString("password"));
                user.setUsername(rs.getString("username"));
                return user;
            } else {
                return null;
            }

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);

        } finally {
            WebUtils.close(conn, ps, rs);
        }
    }

    /**
     * @param user
     */
    public void addUser(User user) {
        Connection conn = null;
        PreparedStatement ps = null;
        ResultSet rs = null;

        try {
            conn = WebUtils.getConnection();
            //没有重复，进行新增
            ps = conn.prepareStatement("insert into user(username, password, nickname, email) values(?,?,?,?)");
            ps.setString(1, user.getUsername());
            ps.setString(2, user.getPassword());
            ps.setString(3, user.getNickname());
            ps.setString(4, user.getEmail());
            ps.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);

        } finally {
            WebUtils.close(conn, ps, rs);
        }
    }
}
