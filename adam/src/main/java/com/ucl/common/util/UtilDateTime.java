package com.ucl.common.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Date;

import com.ucl.common.constants.Commvar;

public class UtilDateTime {
	
	// 리턴값 : 2024-03-12T11:37:22.234104100
	public static LocalDateTime nowLocalDateTime() throws Exception {
		LocalDateTime localDateTime = LocalDateTime.now();
		return localDateTime;
	}
	
	// 리턴값 : Tue Mar 12 11:38:32 KST 2024
	public static Date nowDate() throws Exception {
		LocalDateTime localDateTime = LocalDateTime.now();
		String localDateTimeString = localDateTime.format(DateTimeFormatter.ofPattern(Commvar.DATETIME_FORMAT_BASIC));
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Commvar.DATETIME_FORMAT_BASIC);
        Date date = simpleDateFormat.parse(localDateTimeString);
		return date;
	}	
	
	// 리턴값 : 2024-03-12 11:39:15
	// 정규식(yyyy-MM-dd HH:mm:ss) 적용된 일시
	public static String nowString() throws Exception {
		LocalDateTime localDateTime = LocalDateTime.now();
		String localDateTimeString = localDateTime.format(DateTimeFormatter.ofPattern(Commvar.DATETIME_FORMAT_BASIC));
		return localDateTimeString;
	}	
	
	// 리턴값 : 2024-03-22 11:57:42
	// LocalDateTime에 day 만큼 정규식(yyyy-MM-dd HH:mm:ss) 적용된 이후(이전)일시
	public static String calculateDayString(LocalDateTime localDateTime, int day) throws Exception {
		LocalDateTime localDateTimeNew;
		
		if(day >= 0) {
			localDateTimeNew = localDateTime.plusDays(day); 
		} else {
			localDateTimeNew = localDateTime.minusDays(Math.abs(day));
		}

		String localDateTimeNewString = localDateTimeNew.format(DateTimeFormatter.ofPattern(Commvar.DATETIME_FORMAT_BASIC));
		return localDateTimeNewString;
	}	
	
	// 리턴값 : 2024-03-22 00:00:00
	// LocalDateTime에 day 만큼 이후(이전)일자에 시분초(00:00:00) 설정하여 리턴 
	public static String calculateDayReplace00TimeString(LocalDateTime localDateTime, int day) throws Exception {
		LocalDateTime localDateTimeNew;
		
		if(day >= 0) {
			localDateTimeNew = localDateTime.plusDays(day); 
		} else {
			localDateTimeNew = localDateTime.minusDays(Math.abs(day));
		}
		
		String localDateTimeNewString = localDateTimeNew.format(DateTimeFormatter.ofPattern(Commvar.DATETIME_FORMAT_BASIC));
		localDateTimeNewString = localDateTimeNewString.substring(0, 10) + " 00:00:00";
		return localDateTimeNewString;
	}	
	
	// 리턴값 : 2024-03-22T11:56:26.350406100
	// LocalDateTime에 day 만큼 이후(이전)일시
	public static LocalDateTime calculateDayLocalDateTime(LocalDateTime localDateTime, int day) throws Exception {
		LocalDateTime localDateTimeNew;
		
		if(day >= 0) {
			localDateTimeNew = localDateTime.plusDays(Math.abs(day)); 
		} else {
			localDateTimeNew = localDateTime.minusDays(Math.abs(day));
		}
		
		return localDateTimeNew;
	}
	
	// 린터값 : Fri Mar 22 12:12:50 KST 2024
	// LocalDateTime에 day 만큼 이후(이전)일시
	public static Date calculateDayDate(LocalDateTime localDateTime, int day) throws Exception {
		LocalDateTime localDateTimeNew;
		
		if(day >= 0) {
			localDateTimeNew = localDateTime.plusDays(Math.abs(day)); 
		} else {
			localDateTimeNew = localDateTime.minusDays(Math.abs(day));
		}
		
		String localDateTimeNewString = localDateTimeNew.format(DateTimeFormatter.ofPattern(Commvar.DATETIME_FORMAT_BASIC));
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat(Commvar.DATETIME_FORMAT_BASIC);
        Date date = simpleDateFormat.parse(localDateTimeNewString);
		
		return date;
	}	
	
	// 리턴값 : 2024-03-12 12:16:39
	// 파라미터 date에 현재 시분초 포함
	public static String addNowTimeString(String date) {
		LocalDateTime localDateTime = LocalDateTime.now();
		String localDateTimeString = localDateTime.format(DateTimeFormatter.ofPattern(Commvar.TIME_FORMAT_BASIC));
		return date + " " + localDateTimeString;
	}	
	
	// 리턴값 : 2024-03-12 00:00:00
	// 파라미터 date에 시분초(00:00:00) 포함
	public static String add00TimeString(String date) {
		return date + " 00:00:00";
	}

	// 리턴값 : 2024-03-12 23:59:59
	// 파라미터 date에 시분초(23:59:59) 포함	
	public static String add59TimeString(String date) {
		return date + " 23:59:59";
	}
	
	// Date -> String
	// 정규식(yyyy-MM-dd)
	public static String dateToString(Date dateParam) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String dateRt = simpleDateFormat.format(dateParam);
		return dateRt;
	}
	
	// Date -> String
	// 정규식(yyyy-MM-dd HH:mm:ss)
	// Date 자체는 포맷이 없어 SimpleDateFormat 이용하여 정규식 적용 
	public static String dateTimeToString(Date dateTimeParam) {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String dateTimeRt = simpleDateFormat.format(dateTimeParam);
		return dateTimeRt;
	}
	
	// LocalDateTime -> String
	// 정규식(yyyy-MM-dd HH:mm:ss)	
	public static String localDateTimeToString(LocalDateTime localDateTimeParam) {
		String dateTimeRt = localDateTimeParam.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
		return dateTimeRt;
	}	

	// String -> Date
	public static Date StringToDateTime(String dateStr) throws ParseException {
		SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Date date = simpleDateFormat.parse(dateStr);
		return date;
	}
	
	// 리턴값 : 현재년도
	public static String nowYearString() throws Exception {
		LocalDateTime localDateTime = LocalDateTime.now();
		String localDateTimeString = localDateTime.format(DateTimeFormatter.ofPattern("yyyy"));
		return localDateTimeString;
	}	

	// 리턴값 : 오늘일자(yyyymmdd)
	public static String nowYmdString() throws Exception {
		LocalDateTime localDateTime = LocalDateTime.now();
		String localDateTimeString = localDateTime.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
		return localDateTimeString;
	}		
	
	// 리턴값 : 오늘일자(yyyymmdd) - 1
	public static String preYmdString() throws Exception {
		LocalDateTime localDateTime = LocalDateTime.now().minusDays(1);
		String localDateTimeString = localDateTime.format(DateTimeFormatter.ofPattern("yyyyMMdd"));
		return localDateTimeString;
	}
	
	// 리턴값 : 오늘일자(yyyy-mm-dd)
	public static String nowYmdStringDash() throws Exception {
		LocalDateTime localDateTime = LocalDateTime.now();
		String localDateTimeString = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		return localDateTimeString;
	}		
	
	// 리턴값 : 오늘일자(yyyy-mm-dd) - 1
	public static String preYmdStringDash() throws Exception {
		LocalDateTime localDateTime = LocalDateTime.now().minusDays(1);
		String localDateTimeString = localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
		return localDateTimeString;
	}	
	
	// 리턴값 : 현제시간 정규식(HH)
	public static int houreToInteger() {
		LocalDateTime localDateTime = LocalDateTime.now().minusDays(1);
		int localHourInteger = Integer.parseInt(localDateTime.format(DateTimeFormatter.ofPattern("HH")));
		return localHourInteger;
	}
	
	// 리턴값 : 현재년도
	public static Integer nowYearInteger() throws Exception {
		LocalDateTime localDateTime = LocalDateTime.now();
		Integer localDateTimeInteger = localDateTime.getYear();
		return localDateTimeInteger;
	}
	
	// 리턴값 : 요일 (월요일이 1, 일요일이 7)
	public static int nowOfWeekNumber() throws Exception {
		// 1. LocalDate 생성
		LocalDateTime localDateTime = LocalDateTime.now();
	    // 2. DayOfWeek 객체 구하기        
		DayOfWeek dayOfWeek = localDateTime.getDayOfWeek();         
		// 3. 숫자 요일 구하기        
		int dayOfWeekNumber = dayOfWeek.getValue();
		
        // 3. 텍스트 요일 구하기 (영문)        
		//System.out.println(dayOfWeek.getDisplayName(TextStyle.FULL, Locale.US));  
		// Saturday        
		//System.out.println(dayOfWeek.getDisplayName(TextStyle.NARROW, Locale.US));  
		// S        
		//System.out.println(dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.US));  
		// Sat         
		// 4. 텍스트 요일 구하기 (한글)        
		//System.out.println(dayOfWeek.getDisplayName(TextStyle.FULL, Locale.KOREAN));  
		// 토요일        
		//System.out.println(dayOfWeek.getDisplayName(TextStyle.NARROW, Locale.KOREAN));  
		// 토        
		//System.out.println(dayOfWeek.getDisplayName(TextStyle.SHORT, Locale.KOREAN));  
		// 토         
		// 5. 텍스트 요일 구하기 (default)        
		//System.out.println(dayOfWeek.getDisplayName(TextStyle.FULL, Locale.getDefault()));  // 토요일
		
		return dayOfWeekNumber;
	}


}
