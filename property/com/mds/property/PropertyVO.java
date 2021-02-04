package com.mds.property;

import java.sql.Date;

public class PropertyVO {
	
	public int pronum;
	public String imgpath;
	public String proName;
	public String serialNum;
	public String team;
	public Date purcday;
	
	public int getPronum() {
		return pronum;
	}

	public void setPronum(int pronum) {
		this.pronum = pronum;
	}
	
	public String getImgpath() {
		return imgpath;
	}
	
	public void setImgpath(String imgpath) {
		this.imgpath = imgpath;
	}
	
	public String getProName() {
		return proName;
	}
	
	public void setProName(String proName) {
		this.proName = proName;
	}
	
	public String getSerialNum() {
		return serialNum;
	}
	
	public void setSerialNum(String serialNum) {
		this.serialNum = serialNum;
	}
	
	public String getTeam() {
		return team;
	}
	
	public void setTeam(String team) {
		this.team = team;
	}
	
	public Date getPurcday() {
		return purcday;
	}
	
	public void setPurcday(Date purcday) {
		this.purcday = purcday;
	}
	
	
	
}
