package com.mds.member.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import com.mds.member.Member;
import com.mds.member.dao.MemberDao;
import com.mds.property.PagingVO;

@Service
public class MemberService implements IMemberService{
	
	@Autowired
	MemberDao dao;
	
	@Autowired
	BCryptPasswordEncoder passwordEncoder;

	@Override
	public void memberRegister(Member member) {
		String encoPw = passwordEncoder.encode(member.getMemPw());
		String encoCPw = passwordEncoder.encode(member.getConfirmPw());
		member.setMemPw(encoPw);
		member.setConfirmPw(encoCPw);		
		dao.memberInsert(member);
	}
	
	@Override
	public Member memberSearch(Member member) {
		String pw = member.getMemPw();
		member.setMemPw(passwordEncoder.encode(member.getMemPw()));
		Member mem = dao.memberSelect(member);

		
		if (!passwordEncoder.matches(pw, mem.getMemPw())) {
			System.out.println("Login Fail!!");
			mem=null;
		} else if(passwordEncoder.matches(pw, mem.getMemPw())){
			System.out.println("Login Success!!");
			return mem;
		}
		return mem;
		
	}
	
	@Override
	public void memberModify(Member member,int idx) {
	
		dao.memberUpdate(member, idx);
	
	}
	
	@Override
	public void memberRemove(int idx) {
		dao.memberDelete(idx);
	}

	@Override
	public List<Member> memberList(PagingVO paging) {
		return dao.memberListAll(paging);
	}

	@Override
	public int total() {	
		return dao.totalList();
	}

	@Override
	public int searchtotal(Member member) {
		return dao.searchList(member);
	}

	@Override
	public List<Member> memberSerchList(Member member, PagingVO paging) {
	
		return dao.memberSerchList(member, paging);
	}

	@Override
	public Member memberDetail(int idx) {
		return dao.memDetailSelect(idx);
	}

	@Override
	public List<Member> IdList() {

		return dao.memIdList();
	}

}
