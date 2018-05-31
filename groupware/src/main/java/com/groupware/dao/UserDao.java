package com.groupware.dao;

import org.apache.ibatis.annotations.Mapper;

import com.groupware.vo.UserVo;

@Mapper
public interface UserDao {
	public void updatenewuser(UserVo vo);
	public int useridCheck(String userid);
	public int userpwCheck(String userpw);
	public String selectUserPK(String userid);
	public String selectDepPK(String userid);
	public String selectRankPK(String userid);
	public int selectUserCount(UserVo userVo);
	public String[] selectUsers();
	public String[] selectUsersPK();
	public int selectUserRankPK(int user_ai);
	public int selectUserDepPK(int user_ai);
	public int selectRankPKByName(String rank_name);
}
