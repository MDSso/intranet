package com.mds.member.dao;

import java.util.Date;
import java.util.List;
import java.util.Map;

import com.mds.member.AttSearchVO;
import com.mds.member.AttendanceVO;
import com.mds.member.Member;
import com.mds.property.PagingVO;

public interface IAttendanceDao {
	List<AttendanceVO> AttendanceList(PagingVO paging,Member member);
	List<AttendanceVO> AttSearchList(PagingVO paging,Member member,AttSearchVO attendance);
	int totalList(Member member);
	int searchList(Member member,AttSearchVO attendance);
	void onWork(Member member);
	void offWork(String memId);
	Date LastOnAttendance(Member member);
	Date LastOffAttendance(Member member);
	String LastCdate(Member member) throws Exception;
	void AutoInsert(Member member,long i,Date LastDate,Map<String,String> hollydaymap);
}
