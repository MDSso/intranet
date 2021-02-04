package com.mds.member.dao;

import java.util.List;

import com.mds.member.AttSearchVO;
import com.mds.member.AttendanceVO;
import com.mds.member.Member;
import com.mds.property.PagingVO;
import com.mds.property.PropertyVO;

public interface ITeamManagerDao {
	List<AttendanceVO> TeamAttList(PagingVO paging,Member member);
	List<AttendanceVO> TeamAttSearch(PagingVO paging,Member member,AttSearchVO search);
	List<PropertyVO> PropertyList(PagingVO paging);
	List<PropertyVO> PropertySearchList(PagingVO paging,PropertyVO property); 
	int TeamAttTotal(Member member);
	int TeamAttSearchTotal(Member member,AttSearchVO search);
	int PropertyTotal();
	int PropertySearchTotal(PropertyVO property);
	void attUpdateOK(AttendanceVO attendance);
	AttendanceVO AttUpdate(AttendanceVO attendance);
	
}
