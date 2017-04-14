package com.ssm.enums;

import com.ssm.model.Book;
import org.joda.time.DateTime;
import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * Created by Administrator on 2017/4/2.
 */
public class testBookDBOrderEnums {
    public static void main(String args[]) {

        DateTimeFormatter fmt = DateTimeFormat.forPattern("yyyy-MM-dd");
        DateTime dt = new DateTime("2015-07-07");
        int month = dt.getMonthOfYear();  // where January is 1 and December is 12
        int year = dt.getYear();
        System.out.println(dt);
        DateTime newDt = dt.plusMonths(2);
        System.out.println(newDt);
        System.out.println(fmt.print(newDt));
    }

}
