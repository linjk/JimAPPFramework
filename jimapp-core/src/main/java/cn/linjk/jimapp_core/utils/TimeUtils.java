package cn.linjk.jimapp_core.utils;

import java.util.Calendar;

/**
 * Created by Jim on 2016/6/23.
 *
 *
 */
public class TimeUtils {
    public static int curDayOfMonth() {
        int curDayOfMonth = Calendar.getInstance().get(Calendar.DAY_OF_MONTH);

        return curDayOfMonth;
    }

    public static int curMonth() {
        int curMonth = Calendar.getInstance().get(Calendar.MONTH);

        return curMonth+1;
    }
}
