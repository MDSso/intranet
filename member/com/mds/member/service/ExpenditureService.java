package com.mds.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.mds.member.AttSearchVO;
import com.mds.member.EpListVO;
import com.mds.member.ExpenditureVO;
import com.mds.member.Member;
import com.mds.member.dao.ExpenDitureDao;
import com.mds.property.PagingVO;

@Service
public class ExpenditureService implements IExpenditureService{

	@Autowired
	ExpenDitureDao dao;
	
	@Override
	public void expenditureInsert(Member member, ExpenditureVO expenditure) {
		dao.expenditurInsert(member, expenditure);
	}

	@Override
	public List<EpListVO> epListSelect(PagingVO paging, Member member) {
		
		return dao.epListSelect(paging, member);
	}

	@Override
	public int eplisttotal(Member member) {
		
		return dao.eplisttotal(member);
	}

	@Override
	public List<EpListVO> epSearchList(PagingVO paging, Member member, AttSearchVO attsearch) {
		return dao.epSearchList(paging, member, attsearch);
	}

	@Override
	public int epsearchtotal(Member member, AttSearchVO attsearch) {
		return dao.epsearchtotal(member, attsearch);
	}

	@Override
	public List<ExpenditureVO> epDetail(EpListVO eplist) {

		return dao.epDetail(eplist);
	}

	@Override
	public void epRemove(EpListVO eplist) {
		dao.epRemove(eplist);
		
	}

	@Override
	public List<ExpenditureVO> epUpdate(EpListVO eplist) {

		return dao.epUpdate(eplist);
	}

	@Override
	public void epUpdateOK(ExpenditureVO expenditure,Member member) {
		dao.epUpdateOK(expenditure, member);
		
	}

	@Override
	public int totalPrice(EpListVO eplist) {

		return dao.totalPrice(eplist);
	}
	
}
