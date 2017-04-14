package com.ssm.model;

public class Book {
    private String bookName;

    private Integer bookNumber;

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName == null ? null : bookName.trim();
    }

    public Integer getBookNumber() {
        return bookNumber;
    }

    public void setBookNumber(Integer bookNumber) {
        this.bookNumber = bookNumber;
    }

    public Book() {
    }

    public Book(String bookName, Integer bookNumber) {
        this.bookName = bookName;
        this.bookNumber = bookNumber;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookName='" + bookName + '\'' +
                ", bookNumber=" + bookNumber +
                '}';
    }
}