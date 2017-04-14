package com.ssm.test_dao_impl;


import com.ssm.dao.BorrowInfoMapper;
import com.ssm.model.BorrowInfo;
import org.joda.time.DateTime;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2017/4/2.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class BorrowInfoMapperImplTest {

    @Resource
    BorrowInfoMapper borrowInfoMapper;
    SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd");
    Calendar cal = Calendar.getInstance();

    public void syso(Object obj) {
        System.out.println(obj.toString());
    }

    /**
     * Method: queryAll()
     */
    @Test
    public void testQueryAll() throws Exception {
        syso(borrowInfoMapper.queryAll());
    }

    /**
     * Method: queryByUserName(String userName)
     */
    @Test
    public void testQueryByUserName() throws Exception {
        syso(borrowInfoMapper.queryByUserName("root"));
    }


    /**
     * Method: queryByBookName(String bookName)
     */
    @Test
    public void testQueryByBookName() throws Exception {
        syso(borrowInfoMapper.queryByBookName("Java"));
    }

    /**
     * Method: queryByState(int state)
     */
    @Test
    public void testQueryByState() throws Exception {
        syso(borrowInfoMapper.queryByState(1));
    }

    /**
     * Method: queryByTime(Date start_date, Date end_date)
     */
    @Test
    public void testQueryByTime() throws Exception {
        syso(borrowInfoMapper.queryByTime(
                formatter.parse("2017-01-01"), formatter.parse("2017-05-01")));
    }

    /**
     * Method: getInfoId(BorrowInfo info)
     */
    @Test
    public void testGetInfoId() throws Exception {
//        syso(borrowInfoMapper.getInfoId(
//                new BorrowInfo("root", "Java", null, null, 1)));
    }

    /**
     * Method: insertBorrowInfo(BorrowInfo info)
     */
    @Test
    public void testInsertBorrowInfo() throws Exception {
//        Date now = new Date();
//        cal.setTime(now);
//        cal.add(Calendar.MONTH, 2);
//        Date starTime, endTime;
//        starTime = formatter.parse("2017-4-3");
//        endTime = formatter.parse("2017-6-3");
//        BorrowInfo info = new BorrowInfo("root", "Scala", starTime, endTime, 1);
//        syso(borrowInfoMapper.insertBorrowInfo(info));
        //formatter.parse(formatter.format(cal.getTime()))
        DateTime dt = new DateTime();
        int month = dt.getMonthOfYear();  // where January is 1 and December is 12
        int year = dt.getYear();
        DateTime year2000 = dt.withYear(2000);
        DateTime twoHoursLater = dt.plusHours(2);


    }

    /**
     * Method: updateBorrowState(Long id, int state)
     * 这里需要先判断end_time是否<当前时间 ，没有根据的更新state不安全！！！
     */
    @Test
    public void testUpdateBorrowState() throws Exception {
        syso(borrowInfoMapper.updateBorrowState(1000L, 1));
    }

    /**
     * 根据用户名删除
     *
     * @throws Exception
     */
    @Test
    public void testdeleteBorrowInfoByUserName() throws Exception {
        syso(borrowInfoMapper.deleteBorrowInfoByUserName("Allen"));
    }
} 
