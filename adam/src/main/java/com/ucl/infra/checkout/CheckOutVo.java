package com.ucl.infra.checkout;

import com.ucl.common.base.BaseVo;

public class CheckOutVo extends BaseVo {
	private String  shSeq;
	private Integer shPayType  = 1;  // 결제구분
	private Integer shCardType = 2; // 카드종류
	
	private String xstorage;

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

	public String getXstorage() {
		return xstorage;
	}

	public void setXstorage(String xstorage) {
		this.xstorage = xstorage;
	}	
}
