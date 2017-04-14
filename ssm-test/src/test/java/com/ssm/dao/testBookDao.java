package com.ssm.dao;

import com.ssm.enums.BookBorrowState;
import com.ssm.enums.BookDBOrderEnums;
import com.ssm.model.Book;
import com.ssm.model.BorrowInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by Administrator on 2017/4/2.
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml"})
public class testBookDao {

    @Resource
    private BookMapper bookMapper;

    @Test
    public void queryAll() {
        Long bookId = 1000L;
        List<Book> books = bookMapper.queryAll();
        System.out.println(books.toString());
    }

    @Test
    public void queryAllOrder() {
        List<Book> books = bookMapper.queryAllOrder("DESC");
        syso(books);
    }

    @Test
    public void queryByName() {
        List<Book> books = bookMapper.queryByName("a");
        syso(books);
    }

    @Test
    public void countBooks() {
        int result = bookMapper.countBooks();
        syso(result);
    }

    @Test
    public void countOfBookNumber() {
        int result = bookMapper.countOfBookNumber();
        syso(result);
    }

    @Test
    public void queryOfLimit() {
        List<Book> books = bookMapper.queryOfLimit(0, 2);
        syso(books);
    }

    /**
     * 主键冲突忽略，插入负值或者null抛出异常org.springframework.jdbc.BadSqlGrammarException
     */
    @Test
    @Transactional
    public void insertBook() {

        syso(bookMapper.insertBook(new Book("Java", 10)));
    }

    /**
     * update添加ignore的话，冲突会返回1，不添加抛出异常
     * TODO
     * ?????????
     */
    @Test
    @Transactional
    public void updateBookInfo() {

        syso(bookMapper.updateBookInfo("JPython", "Jpython"));
    }

    @Test
    @Transactional
    public void subBookNumber() {
        try {
            syso(bookMapper.subBookNumber("Ruby"));
            throw new Exception("A");
        } catch (Exception e) {
            throw new RuntimeException("hahha");
        }


    }

    @Test
    @Transactional
    public void addBookNumber() {
        syso(bookMapper.addBookNumber("Java"));
        /*事务操作，同时将信息添加到user_book_borrow表中，
        * 要保证事务操作的ACID四个性质
        * */
    }

    @Test
    public void deleteBook() {
        syso(bookMapper.deleteBook(".Net"));
    }

    @Test
    public void testInsert() {
//        int insertBorrowResult = 0;
//        int updateBookResult = 0;
//        Date borrowTime = new Date();
//        Calendar cal = Calendar.getInstance();
//        cal.setTime(borrowTime);
//        cal.add(Calendar.MONTH, 2);
//        Date endTime = cal.getTime();
//        BorrowInfo borrowInfo = new BorrowInfo("root", ".Net", borrowTime, endTime, BookBorrowState.BORROWING);
//        insertBorrowResult = borrowInfoMapper.insertBorrowInfo(borrowInfo);

    }

    public void syso(Object obj) {
        System.out.println(obj.toString());
    }
}
