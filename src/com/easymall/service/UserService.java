package com.easymall.service;

import com.easymall.dao.UserDao;
import com.easymall.domain.User;
import com.easymall.exception.MsgException;

/**
 * User Service
 */
public class UserService {
    private UserDao userDao=new UserDao();

    /**
     * 用户登录
     * @param username 用户名
     * @param password 用户密码
     * @return 成功，返回User，失败，抛出异常
     */
    public User userLogin(String username,String password){
        User findUser=userDao.findUserByUserNameAndPasswd(username,password);
        if(findUser==null){
            throw new MsgException("用户名或密码不正确！");
        }
        else{
            return findUser;
        }
    }
    /**
     * 添加用户
     * @param 用户Bean
     */
    public void registUser(User user){
        findUserByUserName(user.getUsername());
        findUserByeMail(user.getEmail());

        userDao.addUser(user);

    }

    /**
     *
     * @param user
     * @return
     */
    public void  findUserByUserName(String username){
        User findUser=userDao.findUserByUserName(username);
        if(findUser!=null){
            throw new MsgException("用户名【"+username+"】已存在。");
        }
    }

    /**
     *
     * @param user
     * @return
     */
    public void findUserByeMail(String eMail){
        User findUserMail=userDao.findUserByeMail(eMail);
        if(findUserMail!=null){
            throw new MsgException("邮箱【"+eMail+"】已存在。");
        }
    }
}
