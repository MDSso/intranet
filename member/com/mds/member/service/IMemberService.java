package com.mds.member.service;

import java.util.List;

import com.mds.member.Member;
import com.mds.property.PagingVO;

public interface IMemberService {
	void memberRegister(Member member);
	Member memberSearch(Member member);
	void memberModify(Member member,int idx);
	Member memberDetail(int idx);
	void memberRemove(int idx);
	List<Member> memberList(PagingVO paging);
	List<Member> memberSerchList(Member member,PagingVO paging);
	int total();
	int searchtotal(Member member);
	List<Member> IdList();
}
