package com.aqfun.p2p.utils;

import java.util.Calendar;
import java.util.Date;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * 日期工具类
 * ClassName : DateUtil
 *
 * @author aq
 * @date 2018/9/6
 */
public class DateUtil {
    private DateUtil(){}

    /**
     * 普通日期转datetime字符串
     * @return
     */
    public static String dateToString(){
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        return LocalDateTime.now().format(formatter);
    }

    /**
     * 产品周期为天,返回满标后生成收益的时间
     * @param productFullTime
     * @param cycle
     * @return
     */
    public static Date getDateByDays(Date productFullTime, Integer cycle) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(productFullTime);
        calendar.add(Calendar.DAY_OF_MONTH,cycle);
        return calendar.getTime();
    }

    /**
     * 产品周期为月,返回满标后生成收益的时间
     * @param productFullTime
     * @param cycle
     * @return
     */
    public static Date getDateByMonth(Date productFullTime, Integer cycle) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(productFullTime);
        calendar.add(Calendar.MONDAY,cycle);
        return calendar.getTime();
    }

    /**
     * 生成时间戳
     * @return
     */
    public static String getTimeStamp() {
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");
        return LocalDateTime.now().format(formatter);
    }

}
