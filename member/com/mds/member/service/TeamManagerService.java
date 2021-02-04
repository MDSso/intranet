package com.mds.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mds.member.AttSearchVO;
import com.mds.member.AttendanceVO;
import com.mds.member.Member;
import com.mds.member.dao.TeamManagerDao;
import com.mds.property.PagingVO;
import com.mds.property.PropertyVO;

@Service
public class TeamManagerService implements ITeamManagerService{

	@Autowired
	TeamManagerDao dao;
	
	@Override
	public List<AttendanceVO> TeamAttList(PagingVO paging, Member member) {
		
		return dao.TeamAttList(paging, member);
	}

	@Override
	public int TeamAttTotal(Member member) {
		
		return dao.TeamAttTotal(member);
	}

	@Override
	public List<AttendanceVO> TeamAttSearch(PagingVO paging, Member member, AttSearchVO search) {
		
		return dao.TeamAttSearch(paging, member, search);
	}

	@Override
	public int TeamAttSearchTotal(Member member, AttSearchVO search) {
		return dao.TeamAttSearchTotal(member, search);
	}

	@Override
	public AttendanceVO AttUpdate(AttendanceVO attendance) {
		return dao.AttUpdate(attendance);
	}

	@Override
	public List<PropertyVO> PropertyList(PagingVO paging) {

		return dao.PropertyList(paging);
	}

	@Override
	public List<PropertyVO> PropertySearchList(PagingVO paging, PropertyVO property) {
		return dao.PropertySearchList(paging, property);
	}

	@Override
	public int PropertyTotal() {
	
		return dao.PropertyTotal();
	}

	@Override
	public int PropertySearchTotal(PropertyVO property) {
		return dao.PropertySearchTotal(property);
	}

	@Override
	public void attUpdateOK(AttendanceVO attendance) {
		dao.attUpdateOK(attendance);
		
	}

	
}
