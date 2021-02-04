package com.mds.property.dao;

import java.util.List;

import com.mds.member.Member;
import com.mds.property.PagingVO;
import com.mds.property.PropertyVO;

public interface IPropertyDao {
	int propertyInsert(PropertyVO property);
	PropertyVO propertySelect(PropertyVO property);
	PropertyVO propertyDetail(PropertyVO property);
	List<PropertyVO> propertySearch(PropertyVO property,PagingVO paging,Member member);
	List<PropertyVO> propertyList(PagingVO paging,Member member);
	void propertyUpdate(PropertyVO property);
	void propertyDelete(PropertyVO property);
	int totallist(Member member);
	int searchtotallist(PropertyVO property,Member member);
}
