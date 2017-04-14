package com.ssm.test_dao_impl;

import com.ssm.dao.UserMapper;
import com.ssm.model.User;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * UserMapperImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>四月 3, 2017</pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class UserMapperImplTest {


    @Resource
    UserMapper userMapper;

    public void syso(Object obj) {
        System.out.println(obj.toString());
    }

    /**
     * Method: queryAllUser()
     */
    @Test
    public void testQueryAllUser() throws Exception {
        syso(userMapper.queryAllUser());
    }

    /**
     * Method: queryByUserName()
     */
    @Test
    public void testQueryByUserName() throws Exception {
        syso(userMapper.queryByUserName("root"));
    }

    /**
     * Method: insertUser(User user)
     */
    @Test
    public void testInsertUser() throws Exception {
        syso(userMapper.insertUser(new User("Helln", "Helln")));
    }

    /**
     * 注意MySQL数值不区分大小写的
     * 'Admin' = 'admin' !!!!!!!!!!!
     * Method: changeUserPassword(String userName, String oldPassWord, String newPassword)
     */
    @Test
    public void testChangeUserPassword() throws Exception {
        syso(userMapper.changeUserPassword(
                "admin", "Admin", "administrator"));
    }

    /**
     * Method: deleteUserByUserName(String userName)
     */
    @Test
    public void testDeleteUserByUserName() throws Exception {
        syso(userMapper.deleteUserByUserName("Allen"));
    }


} 
