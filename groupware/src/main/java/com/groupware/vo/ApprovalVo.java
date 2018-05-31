package com.groupware.vo;

public class ApprovalVo {
	private int aprov_ai;
	private int draft_ai;
	private int user_ai;
	private int dep_ai;
	private int rank_ai;
	private int apl_ai;
	private String aprov_reg;
	private int aprov_status;
	private String aprov_title;
	
	public int getAprov_ai() {
		return aprov_ai;
	}
	public void setAprov_ai(int aprov_ai) {
		this.aprov_ai = aprov_ai;
	}
	public int getDraft_ai() {
		return draft_ai;
	}
	public void setDraft_ai(int draft_ai) {
		this.draft_ai = draft_ai;
	}
	public int getUser_ai() {
		return user_ai;
	}
	public void setUser_ai(int user_ai) {
		this.user_ai = user_ai;
	}
	public int getDep_ai() {
		return dep_ai;
	}
	public void setDep_ai(int dep_ai) {
		this.dep_ai = dep_ai;
	}
	public int getRank_ai() {
		return rank_ai;
	}
	public void setRank_ai(int rank_ai) {
		this.rank_ai = rank_ai;
	}
	public int getApl_ai() {
		return apl_ai;
	}
	public void setApl_ai(int apl_ai) {
		this.apl_ai = apl_ai;
	}
	public String getAprov_reg() {
		return aprov_reg;
	}
	public void setAprov_reg(String aprov_reg) {
		this.aprov_reg = aprov_reg;
	}
	public int getAprov_status() {
		return aprov_status;
	}
	public void setAprov_status(int aprov_status) {
		this.aprov_status = aprov_status;
	}
	public String getAprov_title() {
		return aprov_title;
	}
	public void setAprov_title(String aprov_title) {
		this.aprov_title = aprov_title;
	}

	@Override
	public String toString() {
		return "ApprovalVo [aprov_ai=" + aprov_ai + ", draft_ai=" + draft_ai + ", user_ai=" + user_ai + ", dep_ai="
				+ dep_ai + ", rank_ai=" + rank_ai + ", apl_ai=" + apl_ai + ", aprov_reg=" + aprov_reg
				+ ", aprov_status=" + aprov_status + ", aprov_title=" + aprov_title + "]";
	}
}