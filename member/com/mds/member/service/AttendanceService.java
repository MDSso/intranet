package com.mds.member.service;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mds.member.AttSearchVO;
import com.mds.member.AttendanceVO;
import com.mds.member.Member;
import com.mds.member.dao.AttendanceDao;
import com.mds.pjt.util.Hollydays;
import com.mds.property.PagingVO;

@Service
public class AttendanceService implements IAttendanceService{

	@Autowired
	AttendanceDao dao;
	
	private Hollydays hollydays;
	
	//모든 출석기록
	@Override
	public List<AttendanceVO> AttendanceList(PagingVO paging,Member member) {

		
		return dao.AttendanceList(paging,member);
	}

	
	//출석 리스트 개수
	@Override
	public int total(Member member) {
		
		return dao.totalList(member);
	}


	@Override
	public Date Lastonatt(Member member) {
		
		return dao.LastOnAttendance(member);
	}


	@Override
	public Date Lastoffatt(Member member) {
		return dao.LastOffAttendance(member);
	}


	@Override
	public void onWork(Member member) {
		dao.onWork(member);
	}


	@Override
	public void offWork(String memId) {
		dao.offWork(memId);
		
	}

	//마지막으로 출석이 찍힌 날짜와 금일의 차이 계산(결근)
	@Override
	public long diffday(Member member) {
		long diff = 0;
		Date date = new Date();
		Date Cdate = null;
		Date LastDate = null;
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		String CdateString = df.format(date);
		String LastDateString = dao.LastCdate(member);
		try {
			Cdate = df.parse(CdateString);
			LastDate = df.parse(LastDateString);
		} catch (ParseException e) {
			e.printStackTrace();
		} 
		diff = (Cdate.getTime() - LastDate.getTime()) / (24*60*60*1000);
		return diff;
	}

	//출석이 없을 시 자동으로 입력
	@Override
	public void AutoInsert(Member member,long diff) {
		hollydays = new Hollydays();
		Map<String,String> hollydaymap = new HashMap<String, String>();
		Date LastDate = null;
		String LastDateString = dao.LastCdate(member);
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
		try {
			LastDate = df.parse(LastDateString);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		
		try {
			hollydaymap =  hollydays.getHollyday(LastDate);
		} catch (IOException e) {
			e.printStackTrace();
		}
		dao.AutoInsert(member, diff,LastDate,hollydaymap);	
	}


	@Override
	public String LastCdate(Member member) {
		return dao.LastCdate(member);
	}

	//조회 결과
	@Override
	public List<AttendanceVO> AttSearchList(PagingVO paging, Member member, AttSearchVO attendance) {		
		return dao.AttSearchList(paging, member, attendance);
	}


	@Override
	public int searchtotal(Member member, AttSearchVO attendance) {

		return dao.searchList(member, attendance);
	}
	
}
