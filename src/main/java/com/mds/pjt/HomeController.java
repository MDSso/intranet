package com.mds.pjt;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.mds.member.Member;

/**
 * Handles requests for the application home page.
 */
@Controller
public class HomeController {
	
	@ModelAttribute("cp")
	public String getContextPath(HttpServletRequest request) {
		return request.getContextPath();
	}
	
	@RequestMapping(value = "/", method = RequestMethod.GET)
	public String index(Member member) {
		return "/member/logform";
	}
	
}
