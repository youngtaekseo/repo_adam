package com.ucl.infra.code;

import java.util.Date;

public class CodeDto {
	private String seq;
	private String name;
	private Date regDateTime;
	private Date modDateTime;
	private Integer delNy;
	private String commonGroup_seq;
	
	private String xcodeGroupSeq;
	private String xcodeGroupName;
	
	private Integer xcodeCount;
//============

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
	
	public String getCommonGroup_seq() {
		return commonGroup_seq;
	}

	public void setCommonGroup_seq(String commonGroup_seq) {
		this.commonGroup_seq = commonGroup_seq;
	}

	public String getXcodeGroupSeq() {
		return xcodeGroupSeq;
	}

	public void setXcodeGroupSeq(String xcodeGroupSeq) {
		this.xcodeGroupSeq = xcodeGroupSeq;
	}

	public String getXcodeGroupName() {
		return xcodeGroupName;
	}

	public void setXcodeGroupName(String xcodeGroupName) {
		this.xcodeGroupName = xcodeGroupName;
	}

	public Integer getXcodeCount() {
		return xcodeCount;
	}

	public void setXcodeCount(Integer xcodeCount) {
		this.xcodeCount = xcodeCount;
	}

}
