package com.ssm.Apache_Common;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.google.common.collect.ComparisonChain;
import com.google.common.collect.Ordering;

import java.util.Date;

/**
 * Created by Administrator on 2017/4/10.
 */
public class UserEntiy implements Comparable {

    @JsonIgnore
    private Long id; //忽略该属性

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private Date date;


    private String userName;

    @JsonProperty("password")
    private String userPassword; //序列化为password

    public UserEntiy() {
    }

    public UserEntiy(Long id, Date date, String userName, String userPassword) {
        this.id = id;
        this.date = date;
        this.userName = userName;
        this.userPassword = userPassword;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }


    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserPassword() {
        return userPassword;
    }

    public void setUserPassword(String userPassword) {
        this.userPassword = userPassword;
    }

    @Override
    public String toString() {
        return "UserEntiy{" +
                "id=" + id +
                ", date='" + date + '\'' +
                ", userName='" + userName + '\'' +
                ", userPassword='" + userPassword + '\'' +
                '}';
    }

    /**
     * ComparisonChain执行一种懒比较：它执行比较操作直至发现非零的结果，在那之后的比较输入将被忽略。
     * 这种Fluent接口风格的可读性更高，发生错误编码的几率更小，并且能避免做不必要的工作。
     *
     * @param o
     * @return
     */
    public int compareTo(Object o) {
        UserEntiy that = (UserEntiy) o;
        return ComparisonChain.start()
                .compare(this.date, that.date, Ordering.natural().nullsLast())//排序器
                .compare(this.userName, that.userName)
                .compare(this.userPassword, that.userPassword)
                .result();
        /*创建排序器：常见的排序器可以由下面的静态方法创建
        方法	描述
        natural()	对可排序类型做自然排序，如数字按大小，日期按先后排序
        usingToString()	按对象的字符串形式做字典排序[lexicographical ordering]
        from(Comparator)	把给定的Comparator转化为排序器*/
    }


}
