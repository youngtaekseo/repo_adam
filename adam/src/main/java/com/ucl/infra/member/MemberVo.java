package com.ucl.infra.member;

import com.ucl.common.base.BaseVo;

public class MemberVo extends BaseVo {
	private String shSeq;
	private Integer shOptionType; // 1:관리자, 2:사용자
	private Integer shSex; // 12:남성, 13:여성
	private Integer shCdgSeq;
	
//=============================================================================
	
	public String getShSeq() {
		return shSeq;
	}
	public void setShSeq(String shSeq) {
		this.shSeq = shSeq;
	}
	public Integer getShOptionType() {
		return shOptionType;
	}
	public void setShOptionType(Integer shOptionType) {
		this.shOptionType = shOptionType;
	}
	public Integer getShSex() {
		return shSex;
	}
	public void setShSex(Integer shSex) {
		this.shSex = shSex;
	}
	public Integer getShCdgSeq() {
		return shCdgSeq;
	}
	public void setShCdgSeq(Integer shCdgSeq) {
		this.shCdgSeq = shCdgSeq;
	}
}
