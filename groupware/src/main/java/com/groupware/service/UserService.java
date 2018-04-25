package com.groupware.service;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.groupware.dao.UserDao;
import com.groupware.vo.UserVo;

@Service
public class UserService{
	@Autowired UserDao userdao;
	@Autowired CommonService commonservice;
	
	//1.회원가입 서비스
	public ModelAndView userjoinlogic(HttpServletRequest req, UserVo userVo, String viewName) {
		ModelAndView model = new ModelAndView(viewName);
		model.addObject("vo",userVo);

		System.out.println(userVo.toString());
		System.out.println("success");

		//session.setAttribute(name, value);
		userdao.updatenewuser(userVo);
		return model;
	}

	//2.로그인 서비스
	public String loginCheck(HttpServletRequest req){
		String user_id = req.getParameter("user_id");
		String user_pw = req.getParameter("user_pw");
		String returnStr = "";
		UserVo userVo = new UserVo();
		userVo.setUser_id(user_id);
		userVo.setUser_pw(user_pw);
		System.out.println(user_id);
		System.out.println(user_pw);

		int cnt = userdao.selectUserCount(userVo);
		System.out.println(cnt);
		
		if(cnt == 1){
			commonservice.addSession("id", user_id);
			returnStr = "1";
		}else{
			returnStr = "-1";
		}
		return returnStr;
	}
	
	//3.로그아웃
	public ModelAndView userlogout(HttpServletRequest req, String viewName) {
		ModelAndView model = new ModelAndView(viewName);
		commonservice.removeSession("id");
		return model;
	}
}
