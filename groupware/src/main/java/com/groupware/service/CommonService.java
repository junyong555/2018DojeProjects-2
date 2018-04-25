package com.groupware.service;

import java.util.HashMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;
import com.groupware.dao.DraftDao;


@Service
public class CommonService {
	@Autowired HttpSession session;
	@Autowired DraftDao draftdao;
	
	public void addSession(String name,Object value) {
		session.setAttribute(name, value);
	}
	
	public void removeSession(String name) {
		session.removeAttribute(name);
	}
	
	public String getSession(String name) {
		return (String) session.getAttribute(name);
	}
	
	public ModelAndView selectdoc(HttpServletRequest req) {
		String headurl = "http://localhost:8088/";
		String url = req.getRequestURL().toString();
		int doccount = draftdao.selectDocCount(); //문서개수
		String[] docspk = draftdao.selectDocsPK(); //문서PK
		String[] docs = draftdao.selectDocs(); //문서명
		HashMap<String, Integer> map = new HashMap<String, Integer>(); //문서PK+문서명
		
		System.out.println("selected docs:::"+doccount);
		for(int i=0;i<docs.length;i++) {
			System.out.print((i+1)+"번째 문서프라이머리키:::"+docspk[i]+"|");
			System.out.println(docs[i]);
		}
		
		ModelAndView view = new ModelAndView();
		
		if(url.equals(headurl+"draftdoc")) {
			for(int i=0;i<docspk.length;i++) {
				map.put(docs[i], Integer.parseInt(docspk[i]));
			}
			view.setViewName("/draftdoc");
			view.addObject("doccount",doccount);
			view.addObject("docmap", map);
		}else if(url.equals(headurl+"draftmanager")) {
			view.setViewName("/draftmanager");
			view.addObject("doccount",doccount);
			view.addObject("pkarrays",docspk);
		}
		return view;
	}

}
