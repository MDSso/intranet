package com.mds.member;

import java.sql.Date;
import java.util.List;

public class ExpenditureVO {
	private int idx;
	private int pidx;
	private String useDate;
	private String wtUse;
	private int price;
	private String poUse;
	private String memId;
	private Date rgDate;
	
	private List<ExpenditureVO> ExpenditureVoList;
	
	public List<ExpenditureVO> getExpenditureVoList() {
		return ExpenditureVoList;
	}
	public void setExpenditureVoList(List<ExpenditureVO> expenditureVoList) {
		ExpenditureVoList = expenditureVoList;
	}
	
	
	public int getIdx() {
		return idx;
	}
	public void setIdx(int idx) {
		this.idx = idx;
	}
	public int getPidx() {
		return pidx;
	}
	public void setPidx(int pidx) {
		this.pidx = pidx;
	}
	public String getUseDate() {
		return useDate;
	}
	public void setUseDate(String useDate) {
		this.useDate = useDate;
	}
	public String getWtUse() {
		return wtUse;
	}
	public void setWtUse(String wtUse) {
		this.wtUse = wtUse;
	}
	public int getPrice() {
		return price;
	}
	public void setPrice(int price) {
		this.price = price;
	}
	public String getPoUse() {
		return poUse;
	}
	public void setPoUse(String poUse) {
		this.poUse = poUse;
	}
	public String getMemId() {
		return memId;
	}
	public void setMemId(String memId) {
		this.memId = memId;
	}
	public Date getRgDate() {
		return rgDate;
	}
	public void setRgDate(Date rgDate) {
		this.rgDate = rgDate;
	}
	
	
	
}
