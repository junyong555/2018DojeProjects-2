package com.groupware.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.groupware.vo.LineVo;
import com.groupware.vo.UserVo;

@Mapper
public interface LineDao {
	public List<HashMap> selectEmp(String emp);
	public List<HashMap> selectDep(int dep_ai);
	public String selectDepName(int dep_ai);
	public String selectRankName(int rank_ai);
	public String selectLineContent(int apl_ai);
	public int selectDepPKbyName(String dep_name);
	public String[] selectApls();
	public String[] selectAplsPK();
	public void insertline(LineVo linevo);
}