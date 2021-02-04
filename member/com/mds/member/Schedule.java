package com.mds.member;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;

import com.mds.member.dao.SalesDao;

public class Schedule {
 
	@Autowired
	SalesDao dao;
	
	@Scheduled(cron = "0 41 0 * * *")  /* 1초가 될때마다 실행   ex)  1시 1초 ,  1시 1분 1초,  1시 2분 1초,  1시 3분 1초 */
	public void sheduler() {
		
		Calendar cal = Calendar.getInstance();
		Date date = cal.getTime();
		String today = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(date);
		System.out.println("매출액 쿼리 실행 : " + today);
		dao.AutoInsertTake();
	}
}
