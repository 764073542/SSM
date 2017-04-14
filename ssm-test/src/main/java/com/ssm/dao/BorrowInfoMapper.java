package com.ssm.dao;

import com.ssm.model.BorrowInfo;

import java.util.Date;
import java.util.List;

public interface BorrowInfoMapper {
    /**
     * 查询操作-查询所有借阅信息
     *
     * @return
     */
    List<BorrowInfo> queryAll();

    /**
     * 查询操作-查询用户所有借阅信息
     *
     * @return
     */
    List<BorrowInfo> queryByUserName(String userName);

    /**
     * 查询操作-查询书籍所有借阅信息
     *
     * @return
     */
    List<BorrowInfo> queryByBookName(String bookName);

    /**
     * 查询操作-根据状态查询所有借阅信息
     *
     * @return
     */
    List<BorrowInfo> queryByState(int state);

    /**
     * 查询操作-查询某个时间段内的借阅信息 时间序列-查询统计
     *
     * @return
     */
    List<BorrowInfo> queryByTime(Date start_date, Date end_date);

    /**
     * 获取id
     * @param info
     * @return
     */
    Long getInfoId(BorrowInfo info);

    /**
     * 插入一条借阅记录
     *
     * @return
     */
    int insertBorrowInfo(BorrowInfo info);

    /**
     * 更新借阅状态 state借阅状态{1：正常状态;0：即将到期;-1:超期;-2：归还状态；}
     *
     * @return
     */
    int updateBorrowState(Long id, int state);
    /*不授权删除*/

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
    int deleteBorrowInfoByUserName(String userName);
}