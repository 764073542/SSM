package com.ssm.services.impl;

import com.ssm.dao.BookMapper;
import com.ssm.dao.BorrowInfoMapper;
import com.ssm.dto.BookBorrowDto;
import com.ssm.enums.BookBorrowState;
import com.ssm.exception.BookBorrowException;
import com.ssm.exception.LowStocksException;
import com.ssm.exception.RepeatBorrowing;
import com.ssm.exception.SQLParamException;
import com.ssm.model.Book;
import com.ssm.model.BorrowInfo;
import com.ssm.services.BookService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * BookService实现类
 */
//@Componet @Service @Dao @Controller
@Service
public class BookServiceImpl implements BookService {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    @Autowired
    BookMapper bookMapper;
    @Autowired
    BorrowInfoMapper borrowInfoMapper;

    public List<Book> queryAll() {
        return bookMapper.queryAll();
    }

    public List<Book> queryAllOrder(String ordered) {
        if (ordered.equals("DESC") || ordered.equals("ASC")) {
            return bookMapper.queryAllOrder(ordered);
        } else {
            throw new SQLParamException("Ordered 参数错误，只支持DESC和ASC");
        }
    }

    public List<Book> queryByName(String bookName) {
        if (bookName != null) {
            return bookMapper.queryByName(bookName);
        }
        return null;
    }

    public int countBooks() {
        return bookMapper.countBooks();
    }

    public int countOfBookNumber() {
        return bookMapper.countOfBookNumber();
    }

    public List<Book> queryOfLimit(int offset, int limit) {
        if (offset < 0 || limit < 0) {
            return null;
        } else {

            return bookMapper.queryOfLimit(offset, limit);
        }
    }

    public int insertBook(Book book) {
        int result = 0;
        if (book != null) {
            result = bookMapper.insertBook(book);
        }
        return result;
    }

    public int updateBookInfo(String newBookName, String oldBookName) {
        int result = 0;
        if (newBookName != null && oldBookName != null) {
            result = bookMapper.updateBookInfo(newBookName, oldBookName);
        }
        return result;
    }

    public int deleteBook(String bookName) {
        int result = 0;
        if (bookName != null) {
            result = bookMapper.deleteBook(bookName);
        }
        return result;
    }

    /**
     * 借书操作实现
     *
     * @param userName
     * @param bookName
     * @return
     */
    @Transactional
    public BookBorrowDto borrowBook(String userName, String bookName) {
        int insertBorrowResult = 0;
        int updateBookResult = 0;
        Date borrowTime = new Date();
        Calendar cal = Calendar.getInstance();
        cal.setTime(borrowTime);
        try {
            cal.add(Calendar.MONTH, 2);
            Date endTime = cal.getTime();
            if (userName != null && bookName != null) {
                BorrowInfo borrowInfo = new BorrowInfo(userName, bookName, borrowTime, endTime, BookBorrowState.BORROWING);
                insertBorrowResult = borrowInfoMapper.insertBorrowInfo(borrowInfo);
                //当插入的数据在books和user表中找不到会发生外键约束异常，insertBorrowResult=0
                if (insertBorrowResult <= 0) {
                    throw new RepeatBorrowing("重复借阅，不允许");
                } else if (insertBorrowResult == 1) {
                    updateBookResult = bookMapper.subBookNumber(bookName);
                    if (updateBookResult <= 0) {
                        throw new LowStocksException("书籍库存不足");
                    } else if (updateBookResult == 1) {
                        return new BookBorrowDto(1, BookBorrowState.BORROWING, borrowInfo);
                    }
                }
            }
        } catch (RepeatBorrowing repeatBorrowing) {
            throw repeatBorrowing;
        } catch (LowStocksException lowStocksException) {
            throw lowStocksException;
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
            throw new BookBorrowException("其他类型异常", e);
        }
        return null;
    }


    /**
     * 还书操作实现
     *
     * @param userName
     * @param bookName
     * @return
     */
    public BookBorrowDto returnBook(String userName, String bookName) {
        return null;
    }
}
