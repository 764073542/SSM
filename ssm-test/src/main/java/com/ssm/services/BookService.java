package com.ssm.services;

import com.ssm.dto.BookBorrowDto;
import com.ssm.model.Book;
import org.apache.ibatis.annotations.Param;

import java.util.List;

/**
 * BookService
 */
public interface BookService {
    /**
     * 查询所有图书 （默认排序规则）
     *
     * @return
     */
    List<Book> queryAll();

    /**
     * 按照图书馆存量排序，默认降序
     *
     * @param ordered
     * @return
     */
    List<Book> queryAllOrder(String ordered);

    /**
     * 通过图书名模糊查询 like
     *
     * @param bookName
     * @return
     */
    List<Book> queryByName(String bookName);

    /**
     * 统计图书总数 count(1)
     *
     * @return
     */
    int countBooks();

    /**
     * 统计馆存总量
     *
     * @return
     */
    int countOfBookNumber();

    /**
     * 根据偏移量查询，分页
     *
     * @param offset @Param用于将request请求的数据传到这里
     * @param limit
     * @return
     */
    List<Book> queryOfLimit(@Param("offset") int offset, @Param("limit") int limit);

    /**
     * 插入图书，返回影响的行数 ignore 主键冲突
     *
     * @param book
     * @return
     */
    int insertBook(Book book);

    /**
     * 借书操作
     * @param userName
     * @param bookName
     * @return
     */
    BookBorrowDto borrowBook(String userName, String bookName);

    /**
     * 还书操作
     * @param userName
     * @param bookName
     * @return
     */
    BookBorrowDto returnBook(String userName,String bookName);
    /**
     * 删除图书，返回影响行数
     *
     * @param bookName
     * @return
     */

    int deleteBook(String bookName);
}
