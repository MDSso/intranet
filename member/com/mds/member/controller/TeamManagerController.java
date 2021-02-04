package com.mds.member.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.mds.member.AttSearchVO;
import com.mds.member.AttendanceVO;
import com.mds.member.Member;
import com.mds.member.service.TeamManagerService;
import com.mds.property.PagingVO;
import com.mds.property.PropertyVO;

@Controller
@RequestMapping("/teammanager")
public class TeamManagerController {
	
	@Autowired
	TeamManagerService service;
	
	@RequestMapping("/attmanager")
	public String teammanager(Model model ,@RequestParam(value="nowPage",required = false, defaultValue = "1") int nowPage,HttpSession session) {
		Member member = (Member) session.getAttribute("member");
		PagingVO paging = new PagingVO(service.TeamAttTotal(member),nowPage,10);
		List<AttendanceVO> attlist = service.TeamAttList(paging, member);
		model.addAttribute("paging",paging);
		model.addAttribute("attlist",attlist);
		return "/teammanager/teamattmanager";
	}
	
	@RequestMapping("/attSearch")
	public String teamattsearch(Model model ,@RequestParam(value="nowPage",required = false, defaultValue = "1")int nowPage,HttpSession session,AttSearchVO search) {
		AttSearchVO searchobj = search;
		if(search.getStart()=="") {
			Date date=new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyyMM01");
			String day= df.format(date);
			search.setStart(day);
		}
		if(search.getEnd()=="") {
			Date date=new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
			String day= df.format(date);
			search.setEnd(day);
		}
		Member member = (Member) session.getAttribute("member");
		PagingVO paging = new PagingVO(service.TeamAttSearchTotal(member, search),nowPage,10);
		List<AttendanceVO> attlist = service.TeamAttSearch(paging, member, search);
		model.addAttribute("paging",paging);
		model.addAttribute("searchobj",searchobj);
		model.addAttribute("attlist",attlist);
		return "/teammanager/teamattmanager";
	}
	
	@RequestMapping("/attupdate")
	public String attupdate(Model model,AttendanceVO attendance) {
		AttendanceVO att=service.AttUpdate(attendance);
		model.addAttribute("att",att);
		return "/teammanager/attupdate";
	}
	
	@RequestMapping(value = "/attupdateOK", method = RequestMethod.POST)
	public String attupdateOK(AttendanceVO attendance) {
		service.attUpdateOK(attendance);
		return "redirect:/teammanager/attmanager";
	}
	
	@RequestMapping("/promanager")
	public String promanager(Model model,@RequestParam(value="nowPage",required = false, defaultValue = "1") int nowPage) {
		PagingVO paging = new PagingVO(service.PropertyTotal(),nowPage,5);
		List<PropertyVO> propertylist = service.PropertyList(paging);
		model.addAttribute("paging",paging);
		model.addAttribute("propertyList",propertylist);
		return "/teammanager/teampromanager";
	}
	
	@RequestMapping("/prosearch")
	public String prosearch(Model model,@RequestParam(value="nowPage",required = false, defaultValue = "1") int nowPage,PropertyVO property) {
		PropertyVO searchobj = property;
		PagingVO paging = new PagingVO(service.PropertySearchTotal(property),nowPage,5);
		List<PropertyVO> propertylist = service.PropertySearchList(paging, property);
		model.addAttribute("paging",paging);
		model.addAttribute("searchobj",searchobj);
		model.addAttribute("propertyList",propertylist);
		return "/teammanager/teampromanager";
	}
}
