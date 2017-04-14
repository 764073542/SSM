package com.ssm.web;

import com.ssm.services.UserService;
import org.junit.Test;
import org.junit.Before; 
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/** 
* UserController Tester. 
* 
* @author <Authors name> 
* @since <pre>ËÄÔÂ 4, 2017</pre> 
* @version 1.0 
*/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml"})
public class UserControllerTest { 

    @Resource
    UserService userService;
/** 
* 
* Method: userLogin(@Param("userName") String userName, @Param("userPassword") String userPassword, String verificationCode) 
* 
*/ 
@Test
public void testUserLogin() throws Exception { 
//    userService
} 


} 
