package com.ucl.infra.payment;

public class PaymentVo {
	private String shSeq;
	private String shMbrSeq;
	private String shPaySeq;

	private String xstorage;
//=============================================================================
	
	public String getShSeq() {
		return shSeq;
	}

	public void setShSeq(String shSeq) {
		this.shSeq = shSeq;
	}
	
	public String getShMbrSeq() {
		return shMbrSeq;
	}

	public void setShMbrSeq(String shMbrSeq) {
		this.shMbrSeq = shMbrSeq;
	}

	public String getShPaySeq() {
		return shPaySeq;
	}

	public void setShPaySeq(String shPaySeq) {
		this.shPaySeq = shPaySeq;
	}

	public String getXstorage() {
		return xstorage;
	}

	public void setXstorage(String xstorage) {
		this.xstorage = xstorage;
	}
	
}
