package com.ucl.infra.codegroup;

import java.util.Date;

public class CodeGroupDto {
	private String seq;
	private String name;
	private Date regDateTime;
	private Date modDateTime;
	private Integer delNy;
	
	private Integer xcodeGroupCount;
	private Integer xcodeCount;
	public String getSeq() {
		return seq;
	}
	public void setSeq(String seq) {
		this.seq = seq;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public Date getRegDateTime() {
		return regDateTime;
	}
	public void setRegDateTime(Date regDateTime) {
		this.regDateTime = regDateTime;
	}
	public Date getModDateTime() {
		return modDateTime;
	}
	public void setModDateTime(Date modDateTime) {
		this.modDateTime = modDateTime;
	}
	public Integer getDelNy() {
		return delNy;
	}
	public void setDelNy(Integer delNy) {
		this.delNy = delNy;
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
