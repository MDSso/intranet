package com.mds.member.dao;

import java.util.List;

import com.mds.member.Member;
import com.mds.property.PagingVO;

public interface IMemberDao {
	void memberInsert(Member member);
	Member memberSelect(Member member);
	Member memDetailSelect(int idx);
	void memberUpdate(Member member, int idx);
	void memberDelete(int idx);
	List<Member> memberListAll(PagingVO paging);
	List<Member> memberSerchList(Member member,PagingVO paging);
	int totalList();
	int searchList(Member member);
	List<Member> memIdList();
}