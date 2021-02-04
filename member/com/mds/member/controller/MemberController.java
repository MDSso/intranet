package com.mds.member.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mds.member.Member;
import com.mds.member.TeamVO;
import com.mds.member.service.MemberService;
import com.mds.member.service.TeamService;
import com.mds.pjt.util.EgovHttpSessionBindingListener;
import com.mds.property.PagingVO;

@Controller
@RequestMapping("/member")
public class MemberController {
	
	@Autowired
	MemberService service;
	
	@Autowired
	TeamService teamservice;
	
	@ModelAttribute("cp")
	public String getContextPath(HttpServletRequest request) {
		return request.getContextPath();
	}

	//로그인
	@RequestMapping(value = "/login", method = RequestMethod.POST)
	public String memLogin(Member member, HttpSession session,Errors errors) {
		if((member.getMemId() == "") || (member.getMemPw() == "")) {
			errors.rejectValue("memId","Empty");
			return "member/logform";
		}
		
		Member mem = service.memberSearch(member);
		if(mem == null) {
			errors.rejectValue("memId","SearchF");
			return "/member/logform";
		}
		session.setAttribute("member", mem);
		EgovHttpSessionBindingListener listener = new EgovHttpSessionBindingListener();
		session.setAttribute(mem.getMemId(), listener);
		
		return "redirect:/attendance/attendanceMain";
	}
	
	//유저관리 페이지 진입
	@RequestMapping("usermanager")
	public String usermanager(Model model ,@RequestParam(value="nowPage",required = false, defaultValue = "1") int nowPage){
		PagingVO paging = new PagingVO(service.total(),nowPage,10);
		List <Member> member = service.memberList(paging);
		model.addAttribute("member", member);
		model.addAttribute("paging", paging);
		return "member/usermanager";
	}
	
	//유저등록 페이지 진입
	@RequestMapping("rgmember")
	public String rgmember(Model model,Member member) {
		List<TeamVO> team = teamservice.teams();
		List<Member> idList = service.IdList();
		model.addAttribute("idList",idList);
		model.addAttribute("team",team);
		return "member/rgmember";
	}
	
	//유저등록 완료
	@RequestMapping(value = "/rgmemberOK", method = RequestMethod.POST)
	public String rgmemberOK(Member member, Model model) {
		service.memberRegister(member);
		return "redirect:/member/usermanager";
	}
	
	//유저 검색 
	@RequestMapping("membersearch")
	public String membersearch(Member member,Model model,@RequestParam(value="nowPage",required = false, defaultValue = "1") int nowPage) {
		PagingVO paging = new PagingVO(service.searchtotal(member),nowPage,10);
		Member searchobj = new Member();
		List<Member> members = service.memberSerchList(member, paging);
		searchobj=member;
		model.addAttribute("searchobj",searchobj);
		model.addAttribute("member",members);
		model.addAttribute("paging",paging);
		
		return "/member/usermanager";
	}
	
	//유저 상세 페이지 진입
	@RequestMapping("memdetail")
	public String memdetail(Model model,@RequestParam("idx") int idx){
		Member member = new Member();
		member=service.memberDetail(idx);
		model.addAttribute("member",member);
		return "/member/userdetail";
	}
	
	//유저 수정 페이지 진입
	@RequestMapping("memupdate")
	public String memupdate(Model model,@RequestParam("idx") int idx){
		List<TeamVO> team = teamservice.teams();
		Member member = service.memberDetail(idx);
		model.addAttribute("team", team);
		model.addAttribute("member", member);
		return "/member/userupdate";
	}
	
	//유저 수정 완료
	@RequestMapping("memupdateOK")
	public String memupdateOK(Member member,@RequestParam("idx") int idx) {
		
		service.memberModify(member,idx);
		return  "redirect:/member/usermanager";
	}
	
	//유저 삭제
	@RequestMapping("memremove")
	public String memremove(@RequestParam("idx") int idx) {
		service.memberRemove(idx);
		return "redirect:/member/usermanager";
	}
}
