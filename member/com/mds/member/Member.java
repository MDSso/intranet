package com.mds.member;

import java.sql.Date;

public class Member {
	
	private int idx;
	private String userName;
	private String userTeam;
	private Date etDate;
	private String memId;
	private String memPw;
	private String confirmPw;
	private String phoneNum;
	private String zipCode;
	private String snAddress;
	private String dtAddress;
	private String memMail;
	private String memGd;
	private String memPo;
	
	public Boolean isPwEqual() {
		return memPw.equals(confirmPw);
	}
	
	public Date getEtDate() {
		return etDate;
	}

	public void setEtDate(Date etDate) {
		this.etDate = etDate;
	}



	public String getMemPo() {
		return memPo;
	}
	public void setMemPo(String memPo) {
		this.memPo = memPo;
	}
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getUserTeam() {
		return userTeam;
	}
	public void setUserTeam(String userTeam) {
		this.userTeam = userTeam;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getMemPw() {
		return memPw;
	}
	public void setMemPw(String memPw) {
		this.memPw = memPw;
	}
	public String getConfirmPw() {
		return confirmPw;
	}
	public void setConfirmPw(String confirmPw) {
		this.confirmPw = confirmPw;
	}
	public String getPhoneNum() {
		return phoneNum;
	}
	public void setPhoneNum(String phoneNum) {
		this.phoneNum = phoneNum;
	}
	public String getZipCode() {
		return zipCode;
	}
	public void setZipCode(String zipCode) {
		this.zipCode = zipCode;
	}
	public String getSnAddress() {
		return snAddress;
	}
	public void setSnAddress(String snAddress) {
		this.snAddress = snAddress;
	}
	public String getDtAddress() {
		return dtAddress;
	}
	public void setDtAddress(String dtAddress) {
		this.dtAddress = dtAddress;
	}
	public String getMemMail() {
		return memMail;
	}
	public void setMemMail(String memMail) {
		this.memMail = memMail;
	}
	public String getMemGd() {
		return memGd;
	}
	public void setMemGd(String memGd) {
		this.memGd = memGd;
	}
	
	
	
}
