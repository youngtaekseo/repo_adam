package com.ucl.infra.wishlist;

import java.util.Date;

public class WishlistDto {
	private String  wshSeq;
	private String  mbrSeq;
	private Date    wshDt;
	private Date    wshRegDt;
	private Date    wshUdtDt;
	private Integer wshDelNy;
	private String  pdtSeq;
	
	private Integer xwshCount;

	//=============================================================================

	public String getWshSeq() {
		return wshSeq;
	}

	public void setWshSeq(String wshSeq) {
		this.wshSeq = wshSeq;
	}

	public String getMbrSeq() {
		return mbrSeq;
	}

	public void setMbrSeq(String mbrSeq) {
		this.mbrSeq = mbrSeq;
	}

	public Date getWshDt() {
		return wshDt;
	}

	public void setWshDt(Date wshDt) {
		this.wshDt = wshDt;
	}

	public Date getWshRegDt() {
		return wshRegDt;
	}

	public void setWshRegDt(Date wshRegDt) {
		this.wshRegDt = wshRegDt;
	}

	public Date getWshUdtDt() {
		return wshUdtDt;
	}

	public void setWshUdtDt(Date wshUdtDt) {
		this.wshUdtDt = wshUdtDt;
	}

	public Integer getWshDelNy() {
		return wshDelNy;
	}

	public void setWshDelNy(Integer wshDelNy) {
		this.wshDelNy = wshDelNy;
	}

	public String getPdtSeq() {
		return pdtSeq;
	}

	public void setPdtSeq(String pdtSeq) {
		this.pdtSeq = pdtSeq;
	}

	public Integer getXwshCount() {
		return xwshCount;
	}

	public void setXwshCount(Integer xwshCount) {
		this.xwshCount = xwshCount;
	}
}
