package com.ucl.infra.checkout;

import com.ucl.common.base.BaseVo;

public class CheckOutVo extends BaseVo {
	private String  shSeq;
	private Integer shPayType = null;  // 결제구분
	private Integer shCardType = null; // 카드종류

//=============================================================================	
	
	public String getShSeq() {
		return shSeq;
	}

	public void setShSeq(String shSeq) {
		this.shSeq = shSeq;
	}
	
	public Integer getShPayType() {
		return shPayType;
	}

	public void setShPayType(Integer shPayType) {
		this.shPayType = shPayType;
	}	
	
	public Integer getShCardType() {
		return shCardType;
	}

	public void setShCardType(Integer shCardType) {
		this.shCardType = shCardType;
	}	
}
