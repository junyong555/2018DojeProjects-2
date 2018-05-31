package com.groupware.service;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.servlet.ModelAndView;

import com.groupware.JavaUtils;
import com.groupware.dao.DraftDao;
import com.groupware.vo.DraftVo;


@Service
public class CommonService{
	@Autowired HttpSession session;
	@Autowired DraftDao draftdao;
	JavaUtils jutils = new JavaUtils();
	
	public void addSession(String name,Object value) {
		session.setAttribute(name, value);
	}
	
	public void removeSession(String name) {
		session.removeAttribute(name);
	}
	
	public String getSession(String name) {
		return (String) session.getAttribute(name);
	}
	
	//리팩토링 요망
	public ModelAndView selectdoc(HttpServletRequest req) {
		String headurl = "http://localhost:8088/";
		String url = req.getRequestURL().toString();
		int doccount = draftdao.selectDocCount(); //문서개수
		String[] docspk = draftdao.selectDocsPK(); //문서PK
		String[] docs = draftdao.selectDocs(); //문서명
		HashMap<String, Integer> map = new HashMap<String, Integer>(); //문서PK+문서명
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
		}else if(url.equals(headurl+"approvalline")) {
			for(int i=0;i<docspk.length;i++) {
				map.put(docs[i], Integer.parseInt(docspk[i]));
			}
			view.setViewName("/approvalline");
			view.addObject("doccount",doccount);
			view.addObject("docmap", map);
		}else if(url.equals(headurl+"makeapproval")) {
			for(int i=0;i<docspk.length;i++) {
				map.put(docs[i], Integer.parseInt(docspk[i]));
			}
		}
		return view;
	}
	
	public void deleteOldImage(DraftVo draftvo) {
		String imgRegex = "<img[^>]+src\\s*=\\s*['\"]([^'\"]+)['\"][^>]*>";
		String updatedcontent = draftvo.getDraft_content();
		String prevcontent = draftdao.selectDocContent(draftvo.getDraft_ai());
		List<String> updateimgs = new ArrayList<String>();
		List<String> previmgs = new ArrayList<String>();
		List<String> delimgs = new ArrayList<String>();
		
		updateimgs = jutils.makeMatcherList(imgRegex, updatedcontent);
		previmgs = jutils.makeMatcherList(imgRegex, prevcontent);
		
		delimgs = findDeletedImgs(previmgs, updateimgs);
		dropDeletedImgs(delimgs);
	}
	
	public List<String> findDeletedImgs(List<String>previmg,List<String>updateimg) {
		if(!previmg.isEmpty()) {
			if(!updateimg.isEmpty()) {
			for(int k=0;k<previmg.size();k++) {
				for(int i=0;i<updateimg.size();i++) {
					if(previmg.get(k).equals(updateimg.get(i))) {
						previmg.remove(k);
					}
				}
			}
			}else{
				System.out.println("업데이트 과정에서 모든 이미지들이 삭제됨");
			}
		}
		return previmg;
	}
	
	public void dropDeletedImgs(List<String>delimgs) {
		for(int i=0;i<delimgs.size();i++) {
			String delimg = delimgs.get(i).substring(10,34);
			String headurl = 
					"C:/Users/JunYongLee/Desktop/"
					+ "2018DojeProjects-master/"
					+ "groupware/src/main/webapp";
			File delimgfile = new File(headurl+delimg);
			delimgfile.delete();
		}
	}
}
