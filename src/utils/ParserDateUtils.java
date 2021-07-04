package utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * 解析时间工作类
 */
public class ParserDateUtils {

    /**
     * 时间分割数组
     * @param date 时间
     * @return 分割结果
     */
    public static int[] processDate(String date){
        String[] dateStr = date.split("-");
        int[] ans = new int[3];
        for(int i = 0; i < 3; ++i){
            ans[i] = Integer.valueOf(dateStr[i]);
        }
        return ans;
    }

    /**
     * 格式：YYYY-MM-dd
     * 获取年份
     *
     * @param date YYYY-MM-dd
     * @return 年份
     */
    public static int getYear(String date) {

       return processDate(date)[0];
    }

    /**
     * 格式：YYYY-MM-dd
     * 获取月份
     *
     * @param date YYYY-MM-dd
     * @return 月份
     */
    public static int getMonth(String date) {

        return processDate(date)[1];
    }

    /**
     * 格式：YYYY-MM-dd
     * 获取日
     *
     * @param date YYYY-MM-dd
     * @return 几日
     */
    public static int getDay(String date) {

        return processDate(date)[2];
    }

    /**
     * 验证时间是否是合法的格式
     * 格式：YYYY-MM-dd
     *
     * @param date YYYY-MM-dd
     * @return 是否合法
     */
    public static boolean validDate(String date) {
        String regex = "(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29)(([0-9]{3}[1-9]|[0-9]{2}[1-9][0-9]{1}|[0-9]{1}[1-9][0-9]{2}|[1-9][0-9]{3})-(((0[13578]|1[02])-(0[1-9]|[12][0-9]|3[01]))|((0[469]|11)-(0[1-9]|[12][0-9]|30))|(02-(0[1-9]|[1][0-9]|2[0-8]))))|((([0-9]{2})(0[48]|[2468][048]|[13579][26])|((0[48]|[2468][048]|[3579][26])00))-02-29) ";
        return date.matches(regex);
    }

}
