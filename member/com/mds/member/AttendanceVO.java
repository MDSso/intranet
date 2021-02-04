package com.mds.member;

import java.sql.Date;

public class AttendanceVO {
	private String memId;
	private String onWork;
	private String offWork;
	private String status;
	private String userName;
	private String Reason;
	private Date cDate;
	
	
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public String getOnWork() {
		return onWork;
	}
	public void setOnWork(String onWork) {
		this.onWork = onWork;
	}
	public String getOffWork() {
		return offWork;
	}
	public void setOffWork(String offWork) {
		this.offWork = offWork;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	public String getUserName() {
		return userName;
	}
	public void setUserName(String userName) {
		this.userName = userName;
	}
	public String getReason() {
		return Reason;
	}
	public void setReason(String reason) {
		Reason = reason;
	}
	public Date getcDate() {
		return cDate;
	}
	public void setcDate(Date cDate) {
		this.cDate = cDate;
	}
}
