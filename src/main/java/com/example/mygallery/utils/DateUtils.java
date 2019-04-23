package com.example.mygallery.utils;

import org.apache.commons.lang.StringUtils;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


/**
 * Created with IntelliJ IDEA.
 *
 * @author zhang tong
 * date: 2018/7/16 9:48
 * description:时间工具类
 */
public class DateUtils {


    public static final String DATE_TIME_FORMAT = "yyyy-MM-dd HH:mm:ss";


    /**
     * 格式化时间为 yyyy-MM-dd HH:mm:ss 格式
     *
     * @param dateTime LocalDateTime
     * @return String
     */
    public static String formatDateTime(LocalDateTime dateTime) {
        String result = null;
        if (dateTime != null && StringUtils.isNotEmpty(DATE_TIME_FORMAT)) {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern(DATE_TIME_FORMAT);
            result = formatter.format(dateTime);
        }
        return result;
    }

}
