package com.nesder.utils;

import java.io.ByteArrayOutputStream;
import java.io.OutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

public class toolUtils {
    public static String DATE_FORMART_YYYYMMDD = "yyyyMMdd";
    public static String DATE_FORMART_YYYYMMDD_hhmmss = "yyyy-MM-dd hh:mm:ss";

    /**
     * 默认日期格式化
     * Date to String
     * @param date
     * @return
     */
    public static String dateFormat(Date date) {
        return dateFormat(date, DATE_FORMART_YYYYMMDD_hhmmss);
    }

    /**
     * 指定日期格式化
     * Date to String
     * @param date
     * @param formatStr
     * @return
     */
    public static String dateFormat(Date date, String formatStr) {
        if (date != null) {
            SimpleDateFormat format = new SimpleDateFormat(formatStr);
            return format.format(date.getTime());
        }
        return "";
    }

    /**
     * 默认日期格式
     * String to Date
     * @param str
     * @return
     * @throws ParseException
     */
    public static Date str2Date(String str) throws ParseException {
        return str2Date(str, DATE_FORMART_YYYYMMDD_hhmmss);
    }

    /**
     * String to Date
     * @param str
     * @param formatStr
     * @return
     * @throws ParseException
     */
    public static Date str2Date(String str, String formatStr) throws ParseException {
        if (str != null && str !="") {
            SimpleDateFormat format = new SimpleDateFormat(formatStr);
            return format.parse(str);
        }
        return null;
    }

    /**
     * 获得前几天的日期
     * @param N
     * @return
     */
    public static Calendar calculateNdaysBefore(int N){
        Calendar calendar1 = Calendar.getInstance();
        SimpleDateFormat sdf1 = new SimpleDateFormat(DATE_FORMART_YYYYMMDD);
        calendar1.add(Calendar.DATE, -1*N);
        return calendar1;
    }

    /**
     * 获得后几天的日期
     * @param N
     * @return
     */
    public static Calendar calculateNdaysAfter(int N){
        Calendar calendar1 = Calendar.getInstance();
        SimpleDateFormat sdf1 = new SimpleDateFormat(DATE_FORMART_YYYYMMDD);
        calendar1.add(Calendar.DATE, N);
        return calendar1;
    }

    /**
     * 获得日差
     * @param d1
     * @param d2
     * @return
     */
    public static int getDaysBetween(Calendar d1, Calendar d2) {
        if (d1.after(d2)) { // swap dates so that d1 is start and d2 is end
            Calendar swap = d1;
            d1 = d2;
            d2 = swap;
        }
        int days = d2.get(Calendar.DAY_OF_YEAR)
                - d1.get(Calendar.DAY_OF_YEAR);
        int y2 = d2.get(Calendar.YEAR);
        if (d1.get(Calendar.YEAR) != y2) {
            d1 = (Calendar) d1.clone();
            do {
                days += d1.getActualMaximum(Calendar.DAY_OF_YEAR);
                d1.add(Calendar.YEAR, 1);
            } while (d1.get(Calendar.YEAR) != y2);
        }
        return days;
    }

    /**
     * 转换星期
     * @param date
     * @return
     */
    public static String getChineseWeek(Calendar date) {
        final String dayNames[] = { "星期日", "星期一", "星期二", "星期三", "星期四", "星期五", "星期六" };
        int dayOfWeek = date.get(Calendar.DAY_OF_WEEK);
        // System.out.println(dayNames[dayOfWeek - 1]);
        return dayNames[dayOfWeek - 1];
    }

    /**
     * 取得下一个月
     * @param date
     * @return
     */
    public static Calendar getNextMonday(Calendar date) {
        Calendar result = null;
        result = date;
        do {
            result = (Calendar) result.clone();
            result.add(Calendar.DATE, 1);
        } while (result.get(Calendar.DAY_OF_WEEK) != 2);
        return result;
    }

    /**
     * MD5加密
     * @param s 密钥
     * @return
     */
    public static final String encodeToMD5(String s) {
        char hexDigits[] =
                { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'A', 'B', 'C', 'D', 'E', 'F' };

        try
        {
            byte[] btInput = s.getBytes("UTF-8");
            MessageDigest mdInst = MessageDigest.getInstance("MD5");
            mdInst.update(btInput);
            byte[] md = mdInst.digest();
            int j = md.length;
            char str[] = new char[j * 2];
            int k = 0;
            for (int i = 0; i < j; i++)
            {
                byte byte0 = md[i];
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];
                str[k++] = hexDigits[byte0 & 0xf];
            }
            return new String(str);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return null;
        }
    }
}
