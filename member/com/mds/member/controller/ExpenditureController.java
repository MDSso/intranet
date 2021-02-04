package com.mds.member.controller;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.mds.member.AttSearchVO;
import com.mds.member.EpListVO;
import com.mds.member.ExpenditureVO;
import com.mds.member.Member;
import com.mds.member.service.ExpenditureService;
import com.mds.pjt.config.MakeExcel;
import com.mds.property.PagingVO;

@Controller
@RequestMapping("/expenditure")
public class ExpenditureController {
	
	@Autowired
	ExpenditureService service;
	
	@ModelAttribute("cp")
	public String getContextPath(HttpServletRequest request) {
		return request.getContextPath();
	}
	
	@RequestMapping("/epmanager")
	public String epmanager(Model model,HttpSession session,@RequestParam(value="nowPage",required = false, defaultValue = "1") int nowPage) {
		Member member = (Member) session.getAttribute("member");
		PagingVO paging = new PagingVO(service.eplisttotal(member),nowPage,10);
		List<EpListVO> eplist = service.epListSelect(paging, member);
		model.addAttribute("paging",paging);
		model.addAttribute("eplist",eplist);
		return "/expenditure/epmanager";
	}
	
	@RequestMapping("/epsearch")
	public String epsearch(Model model,HttpSession session,@RequestParam(value="nowPage",required = false, defaultValue = "1") int nowPage,AttSearchVO attsearch) {
		if(attsearch.getStart()=="") {
			Date date=new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyyMM01");
			String day= df.format(date);
			attsearch.setStart(day);
		}
		if(attsearch.getEnd()=="") {
			Date date=new Date();
			SimpleDateFormat df = new SimpleDateFormat("yyyyMMdd");
			String day= df.format(date);
			attsearch.setEnd(day);
		}
		AttSearchVO searchobj = attsearch;
		Member member = (Member) session.getAttribute("member");
		PagingVO paging = new PagingVO(service.epsearchtotal(member, attsearch),nowPage,10);
		List<EpListVO> eplist = service.epSearchList(paging, member, attsearch);
		model.addAttribute("searchobj", searchobj);
		model.addAttribute("paging",paging);
		model.addAttribute("eplist",eplist);
		return "/expenditure/epmanager";
	}
		
	@RequestMapping("/rgexpenditure")
	public String rgexpenditure() {
		return "/expenditure/rgexpenditure";
	}
	
	@RequestMapping("/rgexpenditureOK")
	public String rgexpenditureOK(HttpSession session,ExpenditureVO expenditure) {
		Member member = (Member) session.getAttribute("member");
		service.expenditureInsert(member, expenditure);
		return "redirect:/expenditure/epmanager";
	}
	
	@RequestMapping("/detail")
	public String detail(Model model,EpListVO eplist) {
		List<ExpenditureVO> expenditure = service.epDetail(eplist);
		model.addAttribute("expenditure",expenditure);
		model.addAttribute("eplist",eplist);
		return "/expenditure/epdetail";
	}
	
	@RequestMapping("/epremove")
	public String epremove(EpListVO eplist) {
		service.epRemove(eplist);
		return "redirect:/expenditure/epmanager";
	}
	
	@RequestMapping("/epupdate")
	public String epupdate(Model model,EpListVO eplist) {
		List<ExpenditureVO> expenditure = service.epUpdate(eplist);
		model.addAttribute("expenditure",expenditure);
		model.addAttribute("eplist",eplist);
		return "/expenditure/epupdate";
	}
	
	@RequestMapping("/epupdateOK")
	public String epupdateOK(HttpSession session,ExpenditureVO expenditure) {
		Member member = (Member) session.getAttribute("member");
		service.epUpdateOK(expenditure, member);
		return "redirect:/expenditure/epmanager";
	}
	
	@RequestMapping("/excel")
	public void createexcel(HttpServletRequest request,HttpServletResponse response,EpListVO eplist) throws Exception, Exception {
		//지출결의 내용 저장
		List<ExpenditureVO> expenditures = service.epDetail(eplist);
		Map<String, Object> beans = new HashMap<String, Object>();
		beans.put("expenditure", expenditures);
		//지출결의 총액 저장
		beans.put("tprice",service.totalPrice(eplist));
		MakeExcel me = new MakeExcel();
		
		me.download(request, response, beans, "test1", "test.xlsx");	 
	}
}
