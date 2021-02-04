package com.mds.property.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mds.member.Member;
import com.mds.property.PagingVO;
import com.mds.property.PropertyVO;
import com.mds.property.dao.PropertyDao;

@Service
public class PropertyService implements IPropertyService{
	
	@Autowired
	PropertyDao dao;
	
	@Override
	public List<PropertyVO> propertySearch(PropertyVO property,PagingVO paging,Member member) {	
		
		return dao.propertySearch(property,paging,member);
	}

	@Override
	public List<PropertyVO> propertyList(PagingVO paging,Member member) {
		return dao.propertyList(paging,member);
	}

	@Override
	public void propertyinsert(PropertyVO property) {
		dao.propertyInsert(property);
	}

	@Override
	public PropertyVO propertydetail(PropertyVO property) {
		
		return dao.propertyDetail(property);
	}

	@Override
	public void propertydelete(PropertyVO property) {
		dao.propertyDelete(property);
	}

	@Override
	public void propertyupdate(PropertyVO property) {
		dao.propertyUpdate(property);
		
	}

	@Override
	public int total(Member member) {
		
		return dao.totallist(member);
	}

	@Override
	public int searchtotal(PropertyVO property,Member member) {
	
		return dao.searchtotallist(property,member);
	}
	

}
