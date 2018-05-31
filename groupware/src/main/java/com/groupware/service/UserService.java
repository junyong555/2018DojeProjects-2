package com.groupware.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.groupware.dao.LineDao;
import com.groupware.dao.UserDao;
import com.groupware.vo.UserVo;

@Service
public class UserService{
	@Autowired UserDao userdao;
	@Autowired LineDao linedao;
	@Autowired CommonService commonservice;
	@Autowired HttpSession session;
	
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

//	리턴값의 0은 부저정인 의미로 안쓰인다.
//	숫자를 리턴할꺼면 -1로 리턴하고
//	문자열이 좋다
//	ex T, F
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
	
	public String loaduserpreview(HttpServletRequest req) {
		String originalpk = req.getParameter("ai");
		int user_ai = Integer.parseInt(originalpk);
		int dep_ai = userdao.selectUserDepPK(user_ai);
		int rank_ai = userdao.selectUserRankPK(user_ai);
		String dep = linedao.selectDepName(dep_ai);
		String rank = linedao.selectRankName(rank_ai);
		
		String userinfo = dep+","+rank;
		return userinfo;
	}
	
}

