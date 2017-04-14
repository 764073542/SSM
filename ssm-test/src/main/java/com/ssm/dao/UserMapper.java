package com.ssm.dao;

import com.ssm.model.User;

import java.util.List;

public interface UserMapper {
    /**
     * 查询所有用户
     *
     * @return
     */
    List<User> queryAllUser();

    /**
     * 根据名字查询用户信息
     *
     * @return
     */
    List<User> queryByUserName(String userName);

    /**
     * 插入用户
     *
     * @param user
     * @return
     */
    int insertUser(User user);

    /**
     * 修改用户信息，这里是修改密码
     *
     * @param userName
     * @return
     */
    int changeUserPassword(String userName, String oldPassWord, String newPassword);

    /**
     * 登录
     *
     * @param user
     * @return
     */
    int userLogin(User user);

    /**
     * 验证用户是否存在
     * @return
     */
    int checkUserName(String userName);
    /*不需要删除方法*/
/*****************************************************************************************/
    /**
     * 业务层实现
     * Spring事务操作，异常回滚
     * 这里提供一个删除方法，与业务无关，测试完全清除用户信息
     * 因为user表中的user_name是user_book_borrow表的外键，所以需要先删除
     * user_book_borrow表中为user_name值的记录(根据state确定记录是否可删除)
     * 然后删除user表中的记录
     *
     * @param userName
     * @return
     */
    int deleteUserByUserName(String userName);
}