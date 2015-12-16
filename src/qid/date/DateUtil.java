package qid.date;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 日期工具
 * 1.getFirstDateAndLastDate 	根据某月获取当月第一天和最后一天的日期
 * 
 * @author djun
 *
 */
public class DateUtil {
	
	
	/**
	 * 日期格式：yyyy-MM-dd
	 */
	public final static String FORMAT_DATE = "yyyy-MM-dd";
	/**
	 * 日期格式：yyyy-MM-dd HH:mm:ss
	 */
    public final static String FORMAT_DATETIME = "yyyy-MM-dd HH:mm:ss";
    /**
	 * 日期格式：yyyy年MM月dd日
	 */
    public final static String FORMAT_DATE_ZH = "yyyy年MM月dd日";
    /**
	 * 日期格式：yyyy年MM月dd日 HH时mm分ss秒
	 */
    public final static String FORMAT_DATETIME_ZH = "yyyy年MM月dd日 HH时mm分ss秒";
    
    public final static String TYPE_DATE = "date";
    public final static String TYPE_DATETIME = "datetime";
    
    
    
    
    

	/**
	 * 根据某日获取取当月第一天和最后一天的日期/获取系统当前月第一天和最后一天的日期
	 * <p>
	 * date == null && isSystemDate = true 获取系统当前月第一天和最后一天的日期
	 * 
	 * @param date
	 * @param isSystemDate
	 * @return
	 */
	@SuppressWarnings("unused")
	public String[] getFirstDateAndLastDate(String date,boolean isSystemDate){
		String[] d = date.split("-");
		int year = Integer.valueOf(d[0]);
    	int month = Integer.valueOf(d[1]);
    	
		Calendar cal = Calendar.getInstance();
		if(isSystemDate && date == null){
			cal.set(Calendar.YEAR, year);
			cal.set(Calendar.MONTH, month);
		}
		cal.set(Calendar.DAY_OF_MONTH, 1);
		cal.add(Calendar.DAY_OF_MONTH, -1);
		Date lastDate = cal.getTime();

		cal.set(Calendar.DAY_OF_MONTH, 1);
		Date firstDate = cal.getTime();
		
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		return new String[]{sdf.format(firstDate),sdf.format(lastDate)};
	}
	
	/**
	 * 获取月份
	 * <p>
	 * 获取指定日期的上一个月或者上几个月或者下一个月或者下几个月
	 * @param date 日期
	 * @param number date日期的参数
	 * @return
	 */
	public static String getMonth(String date,int number){
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM");  
        Calendar calendar = Calendar.getInstance();  
        Date curDate = java.sql.Date.valueOf(date);  
        calendar.setTime(curDate);   
  
        //取得上一个时间  
        calendar.set(Calendar.MONDAY, calendar.get(Calendar.MONDAY) + number);  

        return sdf.format(calendar.getTime()); 
	}
	
	
	
	
	/**
     *将日期加上某些天或减去天数)返回字符串
     * @param date 待处理日期
     * @param to 加减的天数
     * @return 日期
     */
    public static Date dateAdd(String date, int to) {
            java.util.Date d = null;
            try {
                    d = java.sql.Date.valueOf(date);
            } catch (Exception e) {
                    e.printStackTrace();
                    d = new java.util.Date();
            }
            Calendar strDate = Calendar.getInstance();
            strDate.setTime(d);
            strDate.add(Calendar.DATE, to); // 日期减 如果不够减会将月变动
            return strDate.getTime();
    }
	
	 /**
     * 得到某天是周几
     * @param strDay
     * @return 周几
     */
    public static int getWeekDay(String strDay) {
            Date day = DateUtil.dateAdd(strDay, -1);
            Calendar strDate = Calendar.getInstance();
            strDate.setTime(day);
            int meStrDate = strDate.get(Calendar.DAY_OF_WEEK);
            return meStrDate;
    }

    /**
     * 得到某天是周几
     * @param strDay
     * @return 周几
     */
    public static int getWeekDay(Date date) {
            Date day = DateUtil.dateAdd(format(date, "date"), -1);
            Calendar strDate = Calendar.getInstance();
            strDate.setTime(day);
            int meStrDate = strDate.get(Calendar.DAY_OF_WEEK);
            return meStrDate;
    }

    /**
     * 取得两个日期段的日期间隔
     *
     * @author color
     * @param t1 时间1
     * @param t2 时间2
     * @return t2 与t1的间隔天数
     * @throws ParseException
     *             如果输入的日期格式不是0000-00-00 格式抛出异常
     */
    public static int getBetweenDays(String t1, String t2) throws ParseException {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            int betweenDays = 0;
            Date d1 = format.parse(t1);
            Date d2 = format.parse(t2);
            betweenDays = getBetweenDays(d1, d2);
            return betweenDays;
    }
   
    /**
     * 取得两个日期段的日期间隔
     *
     * @author color
     * @param t1 时间1
     * @param t2 时间2
     * @param swapDate      当日期1小于日期2时是否交换两个日期值
     * @return t2 与t1的间隔天数
     * @throws ParseException
     *             如果输入的日期格式不是0000-00-00 格式抛出异常
     */
    public static int getBetweenDays(String t1, String t2, boolean swapDate) throws ParseException {
            DateFormat format = new SimpleDateFormat("yyyy-MM-dd");
            int betweenDays = 0;
            Date d1 = format.parse(t1);
            Date d2 = format.parse(t2);
            betweenDays = getBetweenDays(d1, d2, swapDate);
            return betweenDays;
    }

    /**
     * 取得两个日期段的日期间隔
     * @param d1    日期1
     * @param d2    日期2
     * @param swapDate      当日期1小于日期2时是否交换两个日期值
     * @return      t2 与t1的间隔天数
     */
    public static int getBetweenDays(Date d1, Date d2, boolean swapDate) {
            if (d1 == null || d2 == null) {
                    return -1;
            }
            int betweenDays;
            Calendar c1 = Calendar.getInstance();
            Calendar c2 = Calendar.getInstance();
            c1.setTime(d1);
            c2.setTime(d2);
            if (swapDate) {
                    // 保证第二个时间一定大于第一个时间
                    if (c1.after(c2)) {
                            c2.setTime(d1);
                            c1.setTime(d2);
                    }
            }
            int betweenYears = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);
            betweenDays = c2.get(Calendar.DAY_OF_YEAR) - c1.get(Calendar.DAY_OF_YEAR);
            for (int i = 0; i < betweenYears; i++) {
                    c1.set(Calendar.YEAR, (c1.get(Calendar.YEAR) + 1));
                    betweenDays += c1.getMaximum(Calendar.DAY_OF_YEAR);
            }
            return betweenDays;
    }

    /**
     * 取得两个日期段的日期间隔
     * @param d1    日期1
     * @param d2    日期2
     * @return      t2 与t1的间隔天数
     */
    private static int getBetweenDays(Date d1, Date d2) {
            if (d1 == null || d2 == null) {
                    return -1;
            }
            int betweenDays;
            Calendar c1 = Calendar.getInstance();
            Calendar c2 = Calendar.getInstance();
            c1.setTime(d1);
            c2.setTime(d2);
            // 保证第二个时间一定大于第一个时间
            if (c1.after(c2)) {
                    c2.setTime(d1);
                    c1.setTime(d2);
            }
            int betweenYears = c2.get(Calendar.YEAR) - c1.get(Calendar.YEAR);
            betweenDays = c2.get(Calendar.DAY_OF_YEAR) - c1.get(Calendar.DAY_OF_YEAR);
            for (int i = 0; i < betweenYears; i++) {
                    c1.set(Calendar.YEAR, (c1.get(Calendar.YEAR) + 1));
                    betweenDays += c1.getMaximum(Calendar.DAY_OF_YEAR);
            }
            return betweenDays;
    }
    
    /**
     * @dateValue 日期对象，可以是java.util.Date和java.sql.Date
     * @dateType 格式化的类型,date和datetime
     */
    public static String format(Object dateValue, String dateType) {
            if (dateValue == null)
                    return "";
            if (dateValue instanceof java.sql.Date) {
                    return dateValue.toString();
            } else if (dateValue instanceof java.util.Date) {
                    if (dateType.equals(TYPE_DATE)) {
                            java.text.SimpleDateFormat sfdate = new java.text.SimpleDateFormat(FORMAT_DATE);
                            return sfdate.format(dateValue);
                    } else if (dateType.equals(TYPE_DATETIME)) {
                            java.text.SimpleDateFormat sftime = new java.text.SimpleDateFormat(FORMAT_DATETIME);
                            return sftime.format(dateValue);
                    } else {
                            return "非法日期格式[" + dateType + "]";
                    }
            } else {
                    return "非日期类型";
            }
    }
	
}
