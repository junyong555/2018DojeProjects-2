package com.groupware.controller;

import javax.servlet.http.HttpServletRequest;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class LinkController {
	@RequestMapping("/index")
	public String showindex(HttpServletRequest req){
		return "/index";
	}
	
	@RequestMapping("/login")
	public String showlogin(HttpServletRequest req){
		return "/login";
	}
	
	@RequestMapping("/join")
	public String showjoin(HttpServletRequest req){
		return "/join";
	}
	
	/*@RequestMapping("/draftmanager")
	public String showdraftmanager(HttpServletRequest req) {
		return "/draftmanager";
	}*/
	
	/*@RequestMapping("/draftdoc")
	public String showdraftdoc(HttpServletRequest req){
		return "/draftdoc";
	}*/
	
	/*@RequestMapping("/writedoc")
	public String showdoceditor(HttpServletRequest req){
		return "/writedoc";
	}*/
	
}
