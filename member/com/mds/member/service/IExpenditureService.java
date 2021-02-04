package com.mds.member.service;

import java.util.List;

import com.mds.member.AttSearchVO;
import com.mds.member.EpListVO;
import com.mds.member.ExpenditureVO;
import com.mds.member.Member;
import com.mds.property.PagingVO;

public interface IExpenditureService {
	void expenditureInsert(Member member, ExpenditureVO expenditure);
	List<EpListVO> epListSelect(PagingVO paging,Member member);
	List<EpListVO> epSearchList(PagingVO paging,Member member,AttSearchVO attsearch);
	List<ExpenditureVO> epUpdate(EpListVO eplist);
	void epUpdateOK(ExpenditureVO expenditure,Member member);
	void epRemove(EpListVO eplist);
	int eplisttotal(Member member);
	int epsearchtotal(Member member,AttSearchVO attsearch);
	List<ExpenditureVO> epDetail(EpListVO eplist);
	int totalPrice(EpListVO eplist);
}
