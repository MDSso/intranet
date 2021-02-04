package com.mds.member.dao;

import java.util.List;

import com.mds.member.AttSearchVO;
import com.mds.member.EpListVO;
import com.mds.member.ExpenditureVO;
import com.mds.member.Member;
import com.mds.property.PagingVO;

public interface IExpenditureDao {
	void expenditurInsert(Member member, ExpenditureVO expenditure);
	List<EpListVO> epListSelect(PagingVO paging,Member member);
	List<EpListVO> epSearchList(PagingVO paging,Member member,AttSearchVO attsearch);
	List<ExpenditureVO> epDetail(EpListVO eplist);
	List<ExpenditureVO> epUpdate(EpListVO eplist);
	void epUpdateOK(ExpenditureVO expenditure,Member member);
	void epRemove(EpListVO eplist);
	int eplisttotal(Member member);
	int epsearchtotal(Member member,AttSearchVO attsearch);
	int totalPrice(EpListVO eplist);
}
