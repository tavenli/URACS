package com.tavenli.utils;

import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class DateUtil {
	
	public static Date getCurrentTime() {

		return new Date();

	}
	
	public static Date getDateTime(String dateStr) throws ParseException {

		return SimpleDateFormat.getDateTimeInstance().parse(dateStr);

	}

	public static String getCurrentTimeStr() {

		return getTimeByFormat(new Date(), "yyyy-MM-dd HH:mm:ss");

	}
	
	public static String getDateTimeStr(Date date) {

		return getTimeByFormat(date, "yyyy-MM-dd HH:mm:ss");

	}

	public static String getTimeByFormat(Date currentTime, String timeFormat) {

		SimpleDateFormat formatter = new SimpleDateFormat(timeFormat);
		String dateString = formatter.format(currentTime);

		return dateString;

	}

	public static String convertTimeByFormat(Date currentTime, String timeFormat) {

		SimpleDateFormat formatter = new SimpleDateFormat(timeFormat);
		String dateString = formatter.format(currentTime);

		return dateString;

	}

	public static Date getTimeByZone(Date dateTime, int timeZone, String timeFormat) {

		//timeZone 中国是8
		SimpleDateFormat formatter = new SimpleDateFormat(timeFormat);
		ParsePosition pos = new ParsePosition(timeZone);
		Date resultDate = formatter.parse(formatter.format(dateTime), pos);

		return resultDate;

	}

	public static Date convertTimeByFormat(String timeString, String timeFormat) throws ParseException {

		SimpleDateFormat formatter = new SimpleDateFormat(timeFormat);
		Date resultDate = null;

		resultDate = formatter.parse(timeString);

		return resultDate;

	}

	public static Date addDay(Date sDate, int days) {

		Calendar c = new GregorianCalendar();
		c.setTime(sDate);
		c.add(Calendar.DAY_OF_MONTH, days);
		return c.getTime();
	}

	public static Date addMinute(Date sDate, int minute) {

		Calendar c = new GregorianCalendar();
		c.setTime(sDate);
		c.add(Calendar.MINUTE, minute);
		return c.getTime();
	}
	
	public static Date addSecond(Date sDate, int second) {

		Calendar c = new GregorianCalendar();
		c.setTime(sDate);
		c.add(Calendar.SECOND, second);
		return c.getTime();
	}
	
	public static Date addSecOnNowTime(Long second) {

		return addSecond(new Date(),second.intValue());
		
	}

	public static int calculateDayInterval(String first, String second, String format) {
		Date f = convertTimeByFormat2(first, format);
		Date s = convertTimeByFormat2(second, format);
		long inter = Math.abs(f.getTime() - s.getTime());
		return new Long(inter / (1000 * 60 * 60 * 24)).intValue();
	}
	
	public static int calculateDayInterval(Date first, Date second) {
		long inter = Math.abs(first.getTime() - second.getTime());
		return new Long(inter / (1000 * 60 * 60 * 24)).intValue();
	}

	public static Date convertTimeByFormat2(String timeString, String timeFormat) {

		SimpleDateFormat formatter = new SimpleDateFormat(timeFormat);
		Date resultDate = null;
		try {
			resultDate = formatter.parse(timeString);
		}
		catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return resultDate;

	}

}
