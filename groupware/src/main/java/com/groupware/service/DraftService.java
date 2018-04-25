package com.groupware.service;


import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import com.groupware.dao.DraftDao;
import com.groupware.dao.UserDao;
import com.groupware.vo.DraftVo;

@Service
public class DraftService {
	@Autowired DraftDao draftdao;
	@Autowired UserDao userdao;
	@Autowired CommonService commonservice;
	
	public String loadpreview(HttpServletRequest req,DraftVo draftvo) {
		String originalpk = req.getParameter("ai");
		int pk = Integer.parseInt(originalpk);
		draftvo.setDraft_ai(pk);
		String content = draftdao.selectDocContent(draftvo);
		return content;
	}
	
	public String makedoclist(HttpServletRequest req, DraftVo draftvo) {
		String[] pks = req.getParameterValues("docpks");
		for(int i=0;i<pks.length;i++) {
			System.out.println(pks[i]);
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
		System.out.println(draftvo.toString());
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
		System.out.println("selected infos:::"+userai+","+depai+","+rankai);
		return view;
	}
	
	public ModelAndView gochangedoc(HttpServletRequest req,DraftVo vo,String viewName) {
		int pk = vo.getDraft_ai();
		ModelAndView model = new ModelAndView();
		
		DraftVo resultvo = draftdao.selectDraftInfos(pk);
		System.out.println(resultvo.toString());
		
		model.setViewName(viewName);
		model.addObject("vo",resultvo);
		
		return model;
	}
}
