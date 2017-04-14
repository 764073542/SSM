package com.ssm.services.impl;

import com.ssm.exception.LowStocksException;
import com.ssm.exception.RepeatBorrowing;
import com.ssm.services.BookBorrowService;
import com.ssm.services.BookService;
import org.junit.Test;
import org.junit.Before;
import org.junit.After;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;

/**
 * BookServiceImpl Tester.
 *
 * @author <Authors name>
 * @version 1.0
 * @since <pre>四月 3, 2017</pre>
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration({"classpath:spring/spring-dao.xml","classpath:spring/spring-service.xml"})
public class BookServiceImplTest {

    @Resource
    BookService bookService;

    /**
     * Method: queryAll()
     */
    @Test
    public void testQueryAll() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: queryAllOrder(String ordered)
     */
    @Test
    public void testQueryAllOrder() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: queryByName(String bookName)
     */
    @Test
    public void testQueryByName() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: countBooks()
     */
    @Test
    public void testCountBooks() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: countOfBookNumber()
     */
    @Test
    public void testCountOfBookNumber() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: queryOfLimit(int offset, int limit)
     */
    @Test
    public void testQueryOfLimit() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: insertBook(Book book)
     */
    @Test
    public void testInsertBook() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: updateBookInfo(String newBookName, String oldBookName)
     */
    @Test
    public void testUpdateBookInfo() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: deleteBook(String bookName)
     */
    @Test
    public void testDeleteBook() throws Exception {
//TODO: Test goes here... 
    }

    /**
     * Method: borrowBook(String userName, String bookName)
     */
    @Test
    public void testBorrowBook() throws Exception {

        try {
            System.out.println(bookService.borrowBook("root", "Ruby2"));
        } catch (RepeatBorrowing repeatBorrowing) {
            System.out.println("已有一本该图书在借阅中，借阅失败。");
        }catch (LowStocksException lowStocksException){
            System.out.println("库存不足，借阅失败。");
        }catch (Exception e){
            e.printStackTrace();
        }
    }

    /**
     * Method: returnBook(String userName, String bookName)
     */
    @Test
    public void testReturnBook() throws Exception {
//TODO: Test goes here... 
    }


} 
