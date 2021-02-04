package com.mds.property.controller;

import java.io.File;
import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.mds.member.Member;
import com.mds.member.TeamVO;
import com.mds.member.service.TeamService;
import com.mds.property.PagingVO;
import com.mds.property.PropertyVO;
import com.mds.property.service.PropertyService;

@Controller
@RequestMapping("/property")
public class PropertyController {
	
	@Autowired
	PropertyService service;
	
	@Autowired
	TeamService teamservice;
	
	@ModelAttribute("cp")
	public String getContextPath(HttpServletRequest request) {
		return request.getContextPath();
	}
	
	//property list 페이지로 이동
	@RequestMapping("/propertylist")
	public String proList(Model model,@RequestParam(value="nowPage",required = false, defaultValue = "1") int nowPage,HttpSession session) {
		Member member = (Member) session.getAttribute("member");
		PagingVO paging = new PagingVO(service.total(member),nowPage,5);
		List<PropertyVO> propertyList= service.propertyList(paging,member);	
		model.addAttribute("paging",paging);
		model.addAttribute("propertyList", propertyList);
		return "/property/propertymainOK";
	}
	
	//property등록 페이지로 이동
	@RequestMapping("/rgproperty")
	public String rgproperty(Model model) {
		List<TeamVO> teams=teamservice.teams();
		PropertyVO propertyVO = new PropertyVO();
		model.addAttribute("teams",teams);
		model.addAttribute("PropertyVO",propertyVO);
		return "/property/rgproperty";
	}
	
	//property 등록
	@RequestMapping(value="rgpropertyok", method=RequestMethod.POST)
	public String rgpropertyok(MultipartFile file,PropertyVO property, HttpServletRequest request) throws Exception {
		String imgpath = request.getSession().getServletContext().getRealPath("") + "\\resources\\img\\";
		System.out.println(imgpath);
				//"C:\\Users\\zktld\\eclipse-workspace\\MDSP\\src\\main\\webapp\\resources\\img\\";

		if (file.isEmpty()) {
			property.setImgpath("noimg.jpg");
		}
		else {
		//String uploadPath = "C:\\\\Users\\zktld\\Desktop\\img";
		String filename = file.getOriginalFilename();
		File target= new File(imgpath,filename);
		FileCopyUtils.copy(file.getBytes(), target);
		property.setImgpath(file.getOriginalFilename());
		}
		service.propertyinsert(property);
		
		return "redirect:propertylist?nowPage=1";
	}
 
	//property detail 이동
	@RequestMapping("/prodetail")
	public String prodetailok(Model model,@RequestParam("idx") int idx) {
		PropertyVO propertyVO= new PropertyVO();
		propertyVO.setPronum(idx);
		propertyVO = service.propertydetail(propertyVO);
		model.addAttribute("PropertyVO", propertyVO);
		return "/property/prodetail";
	}
	
	//property 삭제
	@RequestMapping("/proremove")
	public String proremove(Model model,@RequestParam("idx") int idx) {
		PropertyVO propertyVO = new PropertyVO();
		propertyVO.setPronum(idx);
		service.propertydelete(propertyVO);
		return "redirect:propertylist";
	}
	
	//property 수정페이지 진입
	@RequestMapping("/proupdate")
	public String propertyup(Model model,@RequestParam("idx") int idx) {
		List<TeamVO> teams=teamservice.teams();
		model.addAttribute("teams",teams); 
		PropertyVO propertyVO = new PropertyVO();
		propertyVO.setPronum(idx);
		propertyVO=service.propertydetail(propertyVO);
		model.addAttribute("PropertyVO", propertyVO);
		return "/property/proupdate";
	}
	
	
	//property 수정 완료
	@RequestMapping("/proupdateok")
	public String propertyupok(MultipartFile file, Model model,@RequestParam("idx") int idx,PropertyVO property, @RequestParam("imgpath") String imgpath, HttpServletRequest request) throws IOException {
		property.setPronum(idx);
		System.out.println(file.getOriginalFilename()); 
		if (file.isEmpty()) {
			property.setImgpath(imgpath);
		}
		else {
			String uploadPath = request.getSession().getServletContext().getRealPath("") + "\\resources\\img\\";
			String filename = file.getOriginalFilename();
			File target= new File(uploadPath,filename);
			FileCopyUtils.copy(file.getBytes(), target);
			property.setImgpath(file.getOriginalFilename());
		}
		service.propertyupdate(property);
		
		return "redirect:propertylist";
	}
	
	//property 검색
	@RequestMapping("/prosearch")
	public String propertysearch(Model model,PropertyVO property,@RequestParam(value="nowPage",required = false, defaultValue = "1") int nowPage,HttpSession session) {
		Member member = (Member) session.getAttribute("member");
		PropertyVO searchobj = new PropertyVO();
		searchobj = property;
		PagingVO paging=new PagingVO(service.searchtotal(property,member),nowPage,5);
		List<PropertyVO> propertys=service.propertySearch(property,paging,member);
		model.addAttribute("searchobj",searchobj);
		model.addAttribute("paging",paging);
		model.addAttribute("propertyList", propertys);
		return "property/propertymainOK";
	}

}
