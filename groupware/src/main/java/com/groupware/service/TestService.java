package com.groupware.service;

import org.springframework.beans.factory.annotation.Autowired;

import com.groupware.dao.TestDao;

public class TestService {
	@Autowired TestDao testdao;
	
	public int asd() {
		return testdao.selectTest();
	}
}
