package com.groupware.dao;

import java.util.HashMap;
import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import com.groupware.vo.DraftVo;

@Mapper
public interface DraftDao {
	public int selectDocCount();
	public int selectDocPK(String draft_name);
	public String[] selectDocs();
	public String[] selectDocsPK();
	public String selectDocContent(int draft_ai);
	public DraftVo selectDraftInfos(int pk);
	public void insertDoc(DraftVo vo);
	public void updateDoc(DraftVo vo);
	public List<HashMap> selectDraft();
	public void deleteDraft(int draft_ai);
}
