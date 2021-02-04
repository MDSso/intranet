package com.mds.property.service;

import java.util.List;

import com.mds.member.Member;
import com.mds.property.PagingVO;
import com.mds.property.PropertyVO;

public interface IPropertyService {
	void propertyinsert(PropertyVO property);
	List<PropertyVO> propertySearch(PropertyVO property,PagingVO paging,Member member);
	List<PropertyVO> propertyList(PagingVO paging,Member member);
	PropertyVO propertydetail(PropertyVO property);
	void propertydelete(PropertyVO property);
	void propertyupdate(PropertyVO property);
	int total(Member member);
	int searchtotal(PropertyVO property,Member member);
}
