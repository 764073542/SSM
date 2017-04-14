package com.ssm.services.impl;

import com.ssm.dao.UserMapper;
import com.ssm.model.User;
import com.ssm.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Created by Administrator on 2017/4/3.
 */
@Service
public class UserServiceImpl implements UserService {

    @Autowired
    UserMapper userMapper;

    public List<User> queryAllUser() {
        return userMapper.queryAllUser();
    }

    public List<User> queryByUserName(String userName) {
        if (userName != null) {
            return userMapper.queryByUserName(userName);
        } else {
            return null;
        }

    }

    public int insertUser(User user) {
        int result = 0;
        if (user != null) {
            result = userMapper.insertUser(user);
        }
        return result;
    }

    public int changeUserPassword(String userName, String oldPassWord, String newPassword) {
        int result = 0;
        if (userName != null && oldPassWord != null && oldPassWord != null) {
            result = userMapper.changeUserPassword(userName, oldPassWord, newPassword);
        }
        return result;
    }

    public int userLogin(User user) {
        int result =0;
        if(user != null){

            result = userMapper.userLogin(user);
        }
        return result;
    }

    public int checkUserName(String userName) {
        int result = 0;
        if(userName!=null){
            result = userMapper.checkUserName(userName);
        }
        return result;
    }

    /*******************与业务无关的方法，仅用来测试*************************/
    public int deleteUserByUserName(String userName) {
        int result = 0;
        if (userName != null) {
            //会引发外键约束异常
            userMapper.deleteUserByUserName(userName);
        }
        return result;
    }

}
