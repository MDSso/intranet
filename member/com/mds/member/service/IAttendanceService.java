package com.mds.member.service;

import java.util.Date;
import java.util.List;

import com.mds.member.AttSearchVO;
import com.mds.member.AttendanceVO;
import com.mds.member.Member;
import com.mds.property.PagingVO;

public interface IAttendanceService {
	List<AttendanceVO> AttendanceList(PagingVO paging,Member member); //출석 리스트  
	List<AttendanceVO> AttSearchList(PagingVO paging,Member member,AttSearchVO attendance); //조회 리스트
	int total(Member member); //총 출석 리스트 개수
	int searchtotal(Member member,AttSearchVO attendance);
	Date Lastonatt(Member member); //마지막으로 출석한 시간
	Date Lastoffatt(Member member); //마지막으로 출석(퇴근)한 시간
	void onWork(Member member);  //출석
	void offWork(String memId); //퇴근
	long diffday(Member member); //마지막으로 출석한 날짜와 현재날짜와의 차이값 계산
	void AutoInsert(Member member,long diff); //마지막으로 출석한 날짜와 현재날짜간의 공백기간이 있으면 공백기간에 자동으로 결근상태 입력
	String LastCdate(Member member); // 최초출석인지 아닌지 알아보는 용도

}
