package com.groupware.service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import com.groupware.dao.DraftDao;
import com.groupware.dao.UserDao;
import com.groupware.vo.DraftVo;
import com.groupware.vo.UserVo;

@Service
public class DraftService {
	@Autowired DraftDao draftdao;
	@Autowired UserDao userdao;
	@Autowired CommonService commonservice;
	
	//1.미리보기 보여주기
	public String loadpreview(HttpServletRequest req) {
		String originalpk = req.getParameter("ai");
		int draft_ai = Integer.parseInt(originalpk);
		String content = draftdao.selectDocContent(draft_ai);
		return content;
	}
	
	public String makedoclist(HttpServletRequest req, DraftVo draftvo) {
		List<String> pks = new ArrayList<String>();
		for(int i=0;i<req.getParameterValues("docpks").length;i++) {
			pks.add(i, req.getParameterValues("docpks")[i]); 
			System.out.println("dd");
			System.out.println(pks.get(i));
		}
		return null;
	}
	
	public ModelAndView insertdoc(DraftVo draftvo, String viewName) {
		ModelAndView model = new ModelAndView();
		model.setViewName(viewName);
		model.addObject("vo",draftvo);
		
		System.out.println(draftvo.toString());
		System.out.println("success");
		draftdao.insertDoc(draftvo);
		return model;
	}
	
	public String updatedoc(DraftVo draftvo, String viewName) {
		commonservice.deleteOldImage(draftvo);
		draftdao.updateDoc(draftvo);
		return viewName;
	}
	
	public ModelAndView adduserinfo() {
		String loginedid = commonservice.getSession("id");
		
		String userai = userdao.selectUserPK(loginedid);
		String depai = userdao.selectDepPK(loginedid);
		String rankai = userdao.selectRankPK(loginedid);
		
		ModelAndView view = new ModelAndView();
		view.setViewName("/writedoc");
		view.addObject("userai",userai);
		view.addObject("depai",depai);
		view.addObject("rankai",rankai);
		return view;
	}
	
	public ModelAndView gochangedoc(HttpServletRequest req,DraftVo vo,String viewName) {
		int pk = vo.getDraft_ai();
		ModelAndView model = new ModelAndView();
		DraftVo resultvo = draftdao.selectDraftInfos(pk);
		
		model.setViewName(viewName);
		model.addObject("vo",resultvo);
		
		return model;
	}
	
	public List<DraftVo> getDraft() {
		List<HashMap> draftlist = draftdao.selectDraft();
		List<DraftVo> draftvos = new ArrayList<DraftVo>();
		
		if(draftlist.size()!=0) {
		for(int i=0;i<draftlist.size();i++) {
			DraftVo vo = new DraftVo(); // arrayList든 Vector든 아이템을 추가하려면 다른 레퍼런스(주소)를 가지는 새로운 객체를 넣어주어야함. 그래서 new 연산자를 사용해야한다
			vo.setDraft_ai((int)draftlist.get(i).get("draft_ai"));
			vo.setDraft_name((String)draftlist.get(i).get("draft_name"));
			vo.setDraft_type((String)draftlist.get(i).get("draft_type"));
			vo.setDraft_temp((int)draftlist.get(i).get("draft_temp"));
			vo.setDraft_important((int)draftlist.get(i).get("draft_important"));
			draftvos.add(vo);
		}
			return draftvos;
		}else {
			System.out.println("warning no results!!!");
			return null;
		}
	}

	public String godeldoc(HttpServletRequest req) {
		int draft_pk = Integer.parseInt(req.getParameter("ai"));
		draftdao.deleteDraft(draft_pk);
		return "삭제되었습니다";
	}
}
