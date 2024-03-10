package com.ucl.infra.manager;

import java.util.Date;

public class ManagerDto {
	private String mgSeq;
	private String mgName;
	private String mgEmail;
	private String mgDept;
	private String mgPosition;
	private String mgAddrM;
	private String mgAddrD;
	private Integer mgDelNy;
	private Date mgRegDt;
	private Date mgUdtDt;
	
	private Integer xmgCount;
	private String xmgNameType;
	private String xmgDateType;
	private String xmgDateFrom;
	private String xmgDateTo;
	
//	----------
	
	public String getMgSeq() {
		return mgSeq;
	}
	public void setMgSeq(String mgSeq) {
		this.mgSeq = mgSeq;
	}
	public String getMgName() {
		return mgName;
	}
	public void setMgName(String mgName) {
		this.mgName = mgName;
	}
	public String getMgEmail() {
		return mgEmail;
	}
	public void setMgEmail(String mgEmail) {
		this.mgEmail = mgEmail;
	}
	public String getMgDept() {
		return mgDept;
	}
	public void setMgDept(String mgDept) {
		this.mgDept = mgDept;
	}
	public String getMgPosition() {
		return mgPosition;
	}
	public void setMgPosition(String mgPosition) {
		this.mgPosition = mgPosition;
	}
	public String getMgAddrM() {
		return mgAddrM;
	}
	public void setMgAddrM(String mgAddrM) {
		this.mgAddrM = mgAddrM;
	}
	public String getMgAddrD() {
		return mgAddrD;
	}
	public void setMgAddrD(String mgAddrD) {
		this.mgAddrD = mgAddrD;
	}
	public Integer getMgDelNy() {
		return mgDelNy;
	}
	public void setMgDelNy(Integer mgDelNy) {
		this.mgDelNy = mgDelNy;
	}
	public Date getMgRegDt() {
		return mgRegDt;
	}
	public void setMgRegDt(Date mgRegDt) {
		this.mgRegDt = mgRegDt;
	}
	public Date getMgUdtDt() {
		return mgUdtDt;
	}
	public void setMgUdtDt(Date mgUdtDt) {
		this.mgUdtDt = mgUdtDt;
	}
	public Integer getXmgCount() {
		return xmgCount;
	}
	public void setXmgCount(Integer xmgCount) {
		this.xmgCount = xmgCount;
	}
	public String getXmgNameType() {
		return xmgNameType;
	}
	public void setXmgNameType(String xmgNameType) {
		this.xmgNameType = xmgNameType;
	}
	public String getXmgDateType() {
		return xmgDateType;
	}
	public void setXmgDateType(String xmgDateType) {
		this.xmgDateType = xmgDateType;
	}
	public String getXmgDateFrom() {
		return xmgDateFrom;
	}
	public void setXmgDateFrom(String xmgDateFrom) {
		this.xmgDateFrom = xmgDateFrom;
	}
	public String getXmgDateTo() {
		return xmgDateTo;
	}
	public void setXmgDateTo(String xmgDateTo) {
		this.xmgDateTo = xmgDateTo;
	}
		
}
