package com.ucl.infra.codegroup;

import java.util.Date;

public class CodeGroupDto {
	private String cdgSeq;
	private String cdgName;
	private Date cdgRegDt;
	private Date cdgUdtDt;
	private Integer cdgDelNy;
	
	private String xnameType;
	private String xdateType;
	private String xdateFrom;
	private String xdateTo;
	
	private Integer xcodeGroupCount;
	private Integer xcodeCount;
	
//	-----------
	
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
	public Date getCdgRegDt() {
		return cdgRegDt;
	}
	public void setCdgRegDt(Date cdgRegDt) {
		this.cdgRegDt = cdgRegDt;
	}
	public Date getCdgUdtDt() {
		return cdgUdtDt;
	}
	public void setCdgUdtDt(Date cdgUdtDt) {
		this.cdgUdtDt = cdgUdtDt;
	}
	public Integer getCdgDelNy() {
		return cdgDelNy;
	}
	public void setCdgDelNy(Integer cdgDelNy) {
		this.cdgDelNy = cdgDelNy;
	}
	public String getXnameType() {
		return xnameType;
	}
	public void setXnameType(String xnameType) {
		this.xnameType = xnameType;
	}
	public String getXdateType() {
		return xdateType;
	}
	public void setXdateType(String xdateType) {
		this.xdateType = xdateType;
	}
	public String getXdateFrom() {
		return xdateFrom;
	}
	public void setXdateFrom(String xdateFrom) {
		this.xdateFrom = xdateFrom;
	}
	public String getXdateTo() {
		return xdateTo;
	}
	public void setXdateTo(String xdateTo) {
		this.xdateTo = xdateTo;
	}
	public Integer getXcodeGroupCount() {
		return xcodeGroupCount;
	}
	public void setXcodeGroupCount(Integer xcodeGroupCount) {
		this.xcodeGroupCount = xcodeGroupCount;
	}
	public Integer getXcodeCount() {
		return xcodeCount;
	}
	public void setXcodeCount(Integer xcodeCount) {
		this.xcodeCount = xcodeCount;
	}
	
}
