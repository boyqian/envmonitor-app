package com.upsoft.environmentmonitoring.utils;

import java.sql.Timestamp;
import java.time.Clock;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.time.temporal.ChronoUnit;
import java.time.temporal.TemporalAdjusters;
import java.util.Calendar;
import java.util.Date;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.upsoft.environmentmonitoring.constant.Constants;


/**
 * @ClassName DateUtil
 * @Description JDK1.8日期与时间操作工具类
 * @Author wei wei
 * @Date 2018/12/12 14:14
 * @Version 1.0
 */
public class DateUtil  {

	/**
	 * @Description: 日志记录器
	 */
	private static final Logger logger = LoggerFactory.getLogger(DateUtil.class);

	/**
	 * @Description: 中国时区
	 */
	private static final String CN_ZONE_ID = "Asia/Shanghai";

	/**
	 * @Author weiwei
	 * @Description Date 转 LocalDateTime
	 * @Date 14:19 2018/12/12 
	 * @Params [Date date]
	 * @return java.time.LocalDateTime
	 **/
	public static LocalDateTime dateToLocalDateTime(Date date) {
		if (date == null) {
			return null;
		}
		long nanoOfSecond = (date.getTime() % 1000) * 1000000;
		return LocalDateTime.ofEpochSecond(date.getTime() / 1000, (int) nanoOfSecond, ZoneOffset.of("+8"));
	}

	/**
	 * @Author weiwei
	 * @Description LocalDateTime 转 Date
	 * @Date 14:23 2018/12/12 
	 * @Params [LocalDateTime localDateTime]
	 * @return java.util.Date
	 **/
	public static Date localDateTimeToDate(LocalDateTime localDateTime) {
		if (localDateTime == null) {
			return null;
		}
		return Date.from(localDateTime.atZone(ZoneId.systemDefault()).toInstant());
	}

	/**
	 * @Author weiwei
	 * @Description Date 转 LocalDate
	 * @Date 14:25 2018/12/12 
	 * @Params [Date date]
	 * @return java.time.LocalDate
	 **/
	public static LocalDate dateToLocalDate(Date date) {
		if (date == null) {
			return null;
		}
		return dateToLocalDateTime(date).toLocalDate();
	}

	/**
	 * @Author weiwei
	 * @Description LocalDate 转 Date
	 * @Date 14:26 2018/12/12
	 * @Params [LocalDate localDate]
	 * @return java.util.Date
	 **/
	public static Date localDateToDate(LocalDate localDate) {
		if (localDate == null) {
			return null;
		}
		return Date.from(LocalDate.now().atStartOfDay().atZone(ZoneId.systemDefault()).toInstant());
	}

	/**
	 * @Author weiwei
	 * @Description Timestamp 转 LocalDateTime
	 * @Date 14:27 2018/12/12
	 * @Params [Timestamp date]
	 * @return java.time.LocalDateTime
	 **/
	public static LocalDateTime timestampToLocalDateTime(Timestamp date) {
		if (date == null) {
			return null;
		}
		return LocalDateTime.ofEpochSecond(date.getTime() / 1000, date.getNanos(), ZoneOffset.of("+8"));
	}

	/**
	 * @Author weiwei
	 * @Description Timestamp 转 LocalDate
	 * @Date 14:28 2018/12/12
	 * @Params [Timestamp date]
	 * @return java.time.LocalDate
	 **/
	public static LocalDate timestampToLocalDate(Timestamp date) {
		if (date == null) {
			return null;
		}
		return timestampToLocalDateTime(date).toLocalDate();
	}

	/**
	 * @Author weiwei
	 * @Description localDateTime转自定义格式String
	 * localDateTime: LocalDateTime对象
	 * format: 指定转换的格式，如：yyyy-MM-dd hh:mm:ss
	 * @Date 14:33 2018/12/12 
	 * @Params [LocalDateTime localDateTime, String format]
	 * @return java.lang.String
	 **/
	public static String localDateTimeToFormatString(LocalDateTime localDateTime, String format) {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
			return localDateTime.format(formatter);
		} catch (DateTimeParseException e) {
			logger.error("格式错误,时间转换失败", e);
		}
		return null;
	}

	/**
	 * @Author weiwei
	 * @Description String 转 LocalDateTime
	 * dateString：例 "2017-08-11 01:00:00"
	 * format：例 "yyyy-MM-dd HH:mm:ss"
	 * @Date 14:38 2018/12/12
	 * @Params [String dateString, String format]
	 * @return java.time.LocalDateTime
	 **/
	public static LocalDateTime stringToLocalDateTime(String dateString, String format) {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
			return LocalDateTime.parse(dateString, formatter);
		} catch (DateTimeParseException e) {
			logger.error("格式错误,时间转换失败", e);
		}
		return null;
	}

	/**
	 * @Author weiwei
	 * @Description String 转 LocalDate
	 * dateString：例 "2017-08-11"
	 * format：例 "yyyy-MM-dd"
	 * @Date 14:38 2018/12/12
	 * @Params [String dateString, String format]
	 * @return java.time.LocalDate
	 **/
	public static LocalDate stringToLocalDate(String dateString, String format) {
		try {
			DateTimeFormatter formatter = DateTimeFormatter.ofPattern(format);
			return LocalDate.parse(dateString, formatter);
		} catch (DateTimeParseException e) {
			logger.error("格式错误,时间转换失败", e);
		}
		return null;
	}

	/**
	 * @Author weiwei
	 * @Description Date 转 String
	 * format：例 "yyyy-MM-dd HH:mm:ss"
	 * @Date 16:14 2018/12/12
	 * @Params [date, format]
	 * @return java.lang.String
	 **/
	public static String dateToString(Date date, String format) {
		LocalDateTime localDateTime = dateToLocalDateTime(date);
		return localDateTimeToFormatString(localDateTime, format);
	}

	/**
	 * @Author weiwei
	 * @Description String 转 DateTime
	 * dateString: 如 2018-08-01 21:22:22
	 * format： 如 yyyy-MM-dd HH:mm:ss
	 * @Date 16:22 2018/12/12
	 * @Params [dateString]
	 * @return java.lang.String
	 **/
	public static Date stringToDateTime(String dateString, String format) {
		LocalDateTime localDateTime = stringToLocalDateTime(dateString, format);
		return localDateTimeToDate(localDateTime);
	}

	/**
	 * @Author weiwei
	 * @Description String 转 Date
	 * dateString：如 2018-08-01 21:22:22
	 * format：如 yyyy-MM-dd
	 * @Date 16:22 2018/12/12
	 * @Params [dateString]
	 * @return java.lang.String
	 **/
	public static Date stringToDate(String dateString, String format) {
		LocalDate localDate = stringToLocalDate(dateString, format);
		return localDateToDate(localDate);
	}

	/**
	 * @Author weiwei
	 * @Description 获取指定日期的数字格式的星期
	 * @Date 15:30 2018/12/12
	 * @Params [Date date]
	 * @return java.lang.Integer
	 **/
	public static Integer getWeek(Date date) {
		if (date == null) {
			return null;
		}
		return dateToLocalDateTime(date).getDayOfWeek().getValue();
	}

	/**
	 * @Author weiwei
	 * @Description 获取指定日期的中文格式的星期
	 * @Date 15:40 2018/12/12
	 * @Params [Date date]
	 * @return java.lang.String
	 **/
	public static String getWeekString(Date date) {
		if (date == null) {
			return null;
		}
		String[] weekDays = {"星期一", "星期二", "星期三", "星期四", "星期五", "星期六", "星期日"};
		int week = dateToLocalDateTime(date).getDayOfWeek().getValue();
		return weekDays[week - 1];
	}

	/**
	 * @Author weiwei
	 * @Description 计算两个日期相差的天数,相同日期为0天
	 * @Date 15:43 2018/12/12
	 * @Params [Date start, Date end]
	 * @return java.lang.Integer
	 **/
	public static Integer getDifferenceDay(Date start, Date end) {
		if (start == null || end == null) {
			return null;
		}
		Long days = dateToLocalDate(start).until(dateToLocalDate(end), ChronoUnit.DAYS);
		return days.intValue();
	}

	/**
	 * @Author weiwei
	 * @Description 计算两个日期相差的月数
	 * @Date 15:48 2018/12/12
	 * @Params [Date start, Date end]
	 * @return java.lang.Integer
	 **/
	public static Integer getDifferenceMonths(Date start, Date end) {
		if (start == null || end == null) {
			return null;
		}
		Long months = dateToLocalDate(start).until(dateToLocalDate(end), ChronoUnit.MONTHS);
		return months.intValue();
	}

	/**
	 * @Author weiwei
	 * @Description 计算两个日期相差的年数
	 * @Date 15:50 2018/12/12
	 * @Params [Date start, Date end]
	 * @return java.lang.Integer
	 **/
	public static Integer getDifferenceYears(Date start, Date end) {
		if (start == null || end == null) {
			return null;
		}
		Long years = dateToLocalDate(start).until(dateToLocalDate(end), ChronoUnit.YEARS);
		return years.intValue();
	}

	/**
	 * @Author weiwei
	 * @Description 增加或减少年/月/周/天/小时/分/秒数
	 * date: 指定的日期
	 * chronoUnit: 年/月/周/天/小时/分/秒数，如：ChronoUnit.DAYS 指的是天
	 * num: 增加或减少的数，如：增加2 或 减少-2
	 * @Date 15:52 2018/12/12
	 * @Params [Date date, ChronoUnit chronoUnit, int number]
	 * @return java.util.Date
	 **/
	public static Date addOrSubtractDate(Date date, ChronoUnit chronoUnit, int number) {
		if (date == null) {
			return null;
		}
		LocalDateTime localDateTime = dateToLocalDateTime(date).plus(number, chronoUnit);
		return localDateTimeToDate(localDateTime);
	}

	/**
	 * @Author weiwei
	 * @Description 判断当前时间是否在指定的时间范围内(包含时分秒)
	 * @Date 15:57 2018/12/12
	 * @Params [Date startDate, Date endDate]
	 * @return java.lang.Boolean
	 **/
	public static Boolean isTimeInRange(Date startDate, Date endDate) {
		if (startDate == null || endDate == null) {
			return false;
		}
		LocalDateTime now = LocalDateTime.now(Clock.system(ZoneId.of(CN_ZONE_ID)));
		LocalDateTime start = dateToLocalDateTime(startDate);
		LocalDateTime end = dateToLocalDateTime(endDate);
		return (start.isBefore(now) && end.isAfter(now)) || start.isEqual(now) || end.isEqual(now);
	}
	
	/**
	 * @Author Wang wanqian
	 * @Description 初始化返回对象信息
	 * @Date 2019/11/22
	 * @Params [time,publishDateTime]
	 * @return boolean
	 **/
	public static Boolean isSameTime(String time, LocalDateTime publishDateTime) {
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
		LocalDateTime date = LocalDateTime.parse(time,dtf);
		return date.equals(publishDateTime);
	}

	
	/**
	 * @Author Wang wanqian
	 * @Description addHourValue
	 * @Date 2019/12/03
	 * @Params [h]
	 * @return String
	 **/
	public static String addHourValue(int h) {
		String[] hours=new String[Constants.HOUR_24];
		for (int i = Constants.HOUR_24; i>=1; i--) {
			Calendar calendar=Calendar.getInstance();
	        calendar.add(Calendar.HOUR_OF_DAY, i-Constants.HOUR_24);
	        String year=String.valueOf(calendar.get(Calendar.YEAR));
	        String month=calendar.get(Calendar.MONTH)+1<10?"0"+calendar.get(Calendar.MONTH)+1:String.valueOf(calendar.get(Calendar.MONTH)+1);
	        String day=calendar.get(Calendar.DATE)<10?"0"+calendar.get(Calendar.DATE):String.valueOf(calendar.get(Calendar.DATE));
	        String hour=calendar.get(Calendar.HOUR_OF_DAY)<10?"0"+calendar.get(Calendar.HOUR_OF_DAY):String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
	        hours[i-1]=year+"-"+month+"-"+day+" "+hour+":00:00";
		}
		return hours[h];
	}
	
	/**
	 * @Author Wang wanqian
	 * @Description addDayValue
	 * @Date 2019/12/03
	 * @Params [h]
	 * @return String
	 **/
	public static String addDayValue(int h) {
		String[] day=new String[Constants.DAY_30];
	    for (int i = Constants.DAY_30; i>=1; i--) {
	         Calendar calendar=Calendar.getInstance();
	         calendar.add(Calendar.DAY_OF_MONTH, i-Constants.DAY_30);
	         String year=String.valueOf(calendar.get(Calendar.YEAR));
	         String month=calendar.get(Calendar.MONTH)+1<10?"0"+calendar.get(Calendar.MONTH)+1:String.valueOf(calendar.get(Calendar.MONTH)+1);
	         String days=calendar.get(Calendar.DATE)<10?"0"+calendar.get(Calendar.DATE):String.valueOf(calendar.get(Calendar.DATE));
	         day[i-1]=year+"-"+month+"-"+days+" 00:00:00";
	    }
	    return day[h];
	}
	
	/**
	 * @Author Wang wanqian
	 * @Description 返回某月的第几天,从0开始计数
	 * @Date 10:13 2019/11/19
	 * @Params [publishDateTime]
	 * @return Integer
	 **/
	public static Integer whichDayMonth(LocalDateTime publishDateTime) {
		return publishDateTime.getDayOfMonth()+1;
	}
	
	/**
	 * @Author Wang wanqian
	 * @Description 返回某年某月共几天
	 * @Date 16:13 2019/11/21
	 * @Params [publishDateTime]
	 * @return Integer
	 **/
	public static Integer howDayMonth(String time){
		DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
		LocalDate localTime=LocalDate.parse(time,dtf);
		LocalDate firstday = localTime.with(TemporalAdjusters.firstDayOfMonth());
		LocalDate lastDay = localTime.with(TemporalAdjusters.lastDayOfMonth());
		return lastDay.getDayOfMonth()-firstday.getDayOfMonth()+1;
	}
}
