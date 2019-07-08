package com.knowledge_network.support.utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * ** Created by gongjiangtao on 2018/5/5
 **/
public class DateUtils {
    public static Date getCurrentDay() throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd");
        return df.parse(df.format(new Date()));
    }

    public static Date getCurrentMinute() throws ParseException {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-mm-dd hh:mm");
        return df.parse(df.format(new Date()));
    }
}
