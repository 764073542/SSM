package com.ssm.web;

import com.ssm.dto.UserLoginResult;
import com.ssm.enums.UserLoginState;
import com.ssm.model.User;
import com.ssm.services.UserService;
import org.apache.ibatis.annotations.Param;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Administrator on 2017/4/4.
 */
@Controller
@RequestMapping(value = "/UserControll")
public class UserController {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    UserService userService;

    @RequestMapping(value = "/login", method = RequestMethod.POST,
            produces = {"application/json; charset=utf-8"})
    @ResponseBody
    public UserLoginResult<User> userLogin(@RequestParam("userName") String userName, @RequestParam("userPassword") String userPassword/*, String verificationCode*/) {
        System.out.println("");
        /*封装成json格式数据*/
        UserLoginResult<User> result = null;
        if (userName != null && userPassword != null) {
            User user = new User(userName, userPassword);
            int existsUser = userService.checkUserName(userName);
            if (existsUser <= 0) {
                result = new UserLoginResult<User>(false, UserLoginState.NO_USER.getStateInfo());//不存在该用户,需要注册
            } else {
                int loginResult = userService.userLogin(new User(userName, userPassword));
                if (loginResult <= 0) {
                    result = new UserLoginResult<User>(false, UserLoginState.PASSWORD_INCORRECT.getStateInfo()); //密码错误
                } else {
                    result = new UserLoginResult<User>(true, user);
                }
            }
        } else {
            result = new UserLoginResult<User>(false, UserLoginState.INPUT_ILLEGAL.getStateInfo()); //输入内容非法
        }
        return result;
    }

    @RequestMapping(value = "/test", method = RequestMethod.GET
    )
    public String userTest() {
        return "test";
    }

}
