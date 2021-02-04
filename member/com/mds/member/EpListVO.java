package com.mds.member;

import java.sql.Date;

public class EpListVO {
	private int idx;
	private String memId;
	private String userName;
	private Date rgDate;
	private String userTeam;
	private int tprice;
	
	
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}	
	public Date getRgDate() {
		return rgDate;
	}
	public void setRgDate(Date rgDate) {
		this.rgDate = rgDate;
	}
	public String getUserTeam() {
		return userTeam;
	}
	public void setUserTeam(String userTeam) {
		this.userTeam = userTeam;
	}
	public int getTprice() {
		return tprice;
	}
	public void setTprice(int tprice) {
		this.tprice = tprice;
	}
}
