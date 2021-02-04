package com.mds.member.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mds.member.AttSearchVO;
import com.mds.member.AttendanceVO;
import com.mds.member.Member;
import com.mds.member.service.AttendanceService;
import com.mds.member.service.SalesService;
import com.mds.property.PagingVO;

@Controller
@RequestMapping("/attendance")
public class AttendanceController {
	
	@Autowired
	AttendanceService service;
	
	@Autowired 
	SalesService sservice;
	
	@ModelAttribute("cp")
	public String getContextPath(HttpServletRequest request) {
		return request.getContextPath();
	}
	
	@RequestMapping("/attendanceMain")
	public String attendanceMain(Model model,AttendanceVO attendance,@RequestParam(value="nowPage",required = false, defaultValue = "1") int nowPage,HttpSession session) {
		Member member = (Member) session.getAttribute("member");
		Date lastoffdate = service.Lastoffatt(member);
		Date lastondate = service.Lastonatt(member);
		PagingVO paging = new PagingVO(service.total(member),nowPage,10);
		List<AttendanceVO> attendances= service.AttendanceList(paging,member);
		model.addAttribute("lastoffdate",lastoffdate);
		model.addAttribute("lastondate",lastondate);
		model.addAttribute("attendances",attendances);
		model.addAttribute("paging",paging);
		return "/attendance/attendancemain";
	}
	
	@RequestMapping("/onwork")
	public String onwork(HttpSession session) {
		Member member = (Member) session.getAttribute("member");
		
		//마지막 출석일자와 금일의 날짜간의 차이를 확인 후 결근데이터 생성 
		if(!(service.LastCdate(member)==null)) {
			service.AutoInsert(member,service.diffday(member));
		}	
		service.onWork(member);
		return "redirect:/attendance/attendanceMain";
	}
	
	@RequestMapping("/offwork")
	public String offwork(HttpSession session) {
		Member member = (Member) session.getAttribute("member");
		service.offWork(member.getMemId());
		return "redirect:/attendance/attendanceMain";
	}
	
	@RequestMapping("/attSearch")
	public String attSearch(Model model,HttpSession session,AttSearchVO attendance,@RequestParam(value="nowPage",required = false, defaultValue = "1") int nowPage) {
		if(attendance.getStart()=="") {
			Date date=new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyyMM01");
			String day= df.format(date);
			attendance.setStart(day);
		}
		if(attendance.getEnd()=="") {
			Date date=new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
			String day= df.format(date);
			attendance.setEnd(day);
		}
		AttSearchVO searchobj = attendance;
		Member member = (Member) session.getAttribute("member");
		PagingVO paging = new PagingVO(service.searchtotal(member, attendance),nowPage,10);
		List<AttendanceVO> attendances = service.AttSearchList(paging, member, attendance);
		Date lastoffdate = service.Lastoffatt(member);
		Date lastondate = service.Lastonatt(member);
		model.addAttribute("lastoffdate",lastoffdate);
		model.addAttribute("lastondate",lastondate);
		model.addAttribute("searchobj",searchobj);
		model.addAttribute("attendances",attendances);
		model.addAttribute("paging",paging);
		
		return "/attendance/attendancemain";
	}
}
