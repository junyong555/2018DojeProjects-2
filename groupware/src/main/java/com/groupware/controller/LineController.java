package com.groupware.controller;

import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;
import com.groupware.service.CommonService;
import com.groupware.service.LineService;
import com.groupware.vo.LineVo;
import com.groupware.vo.UserVo;
	
@Controller
public class LineController {
	@Autowired LineService lineservice;
	@Autowired CommonService commonservice;
	
	
	@RequestMapping("/approvalline")
	public ModelAndView showapprovalline(HttpServletRequest req) {
		return commonservice.selectdoc(req);
	}
	
	@RequestMapping(value="/linesearch.ajax")
	@ResponseBody
	public List<UserVo> linesearch(HttpServletRequest req) {
		return lineservice.linesearch(req);
	}
	
	@RequestMapping(value="/loadlinepreview.ajax",produces = "application/text; charset=utf8")
	@ResponseBody
	public String loadlinepreview(HttpServletRequest req) {
		return lineservice.loadlinepreview(req);
	}
	
	@RequestMapping("/insertline")
	public String insertline(LineVo linevo){
		return lineservice.insertline(linevo,"/approvalline");
	}
}
