package com.groupware.dao;

import org.apache.ibatis.annotations.Mapper;

import com.groupware.vo.UserVo;

@Mapper
public interface UserDao {
	public void updatenewuser(UserVo vo);
	public int useridCheck(String userid);
	public int userpwCheck(String userpw);

	public int selectUserCount(UserVo userVo);

}
