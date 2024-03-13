package com.ucl.infra.code;

import java.util.Date;

public class CodeDto {
	private String 	cdcSeq;
	private String 	cdcName;
	private Date 	cdcRegDt;
	private Date 	cdcUdtDt;
	private Integer cdcDelNy;
	private String 	cdgSeq;
			
	private String 	cdgName;	
	private Integer xcodeCount;
	
//============
	
	public String getCdcSeq() {
		return cdcSeq;
	}
	public void setCdcSeq(String cdcSeq) {
		this.cdcSeq = cdcSeq;
	}
	public String getCdcName() {
		return cdcName;
	}
	public void setCdcName(String cdcName) {
		this.cdcName = cdcName;
	}
	public Date getCdcRegDt() {
		return cdcRegDt;
	}
	public void setCdcRegDt(Date cdcRegDt) {
		this.cdcRegDt = cdcRegDt;
	}
	public Date getCdcUdtDt() {
		return cdcUdtDt;
	}
	public void setCdcUdtDt(Date cdcUdtDt) {
		this.cdcUdtDt = cdcUdtDt;
	}
	public Integer getCdcDelNy() {
		return cdcDelNy;
	}
	public void setCdcDelNy(Integer cdcDelNy) {
		this.cdcDelNy = cdcDelNy;
	}
	public String getCdgSeq() {
		return cdgSeq;
	}
	public void setCdgSeq(String cdgSeq) {
		this.cdgSeq = cdgSeq;
	}
	public String getCdgName() {
		return cdgName;
	}
	public void setCdgName(String cdgName) {
		this.cdgName = cdgName;
	}
	public Integer getXcodeCount() {
		return xcodeCount;
	}
	public void setXcodeCount(Integer xcodeCount) {
		this.xcodeCount = xcodeCount;
	}
	

}
