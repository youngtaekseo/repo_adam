package com.ucl.infra.payment;

import java.util.Date;

import com.ucl.infra.product.ProductDto;

public class PaymentDto extends ProductDto {
	// 결제정보
	private String  paySeq;
	private Date    payDate;
	private Integer payTypeCd;
	private Integer payCardCd;
	private String  payOwnerEng;
	private String  payCardNo1;
	private String  payCardNo2;
	private String  payCardNo3;
	private String  payCardNo4;
	private String  payCardCvc;
	private String  payCardYear;
	private String  payCardMonth;
	private Integer payTotal;
	private String  mbrSeq;
	
	// 영수증
	private String  xpayTypeName;
	private String  xpayCardName;
	private Date    xbuyDateTime;
	private Integer xbuyCount;
	private Integer xbuyPrice;
	
//=============================================================================	
// 결제정보
//=============================================================================	
	public String getPaySeq() {
		return paySeq;
	}
	public void setPaySeq(String paySeq) {
		this.paySeq = paySeq;
	}
	public Date getPayDate() {
		return payDate;
	}
	public void setPayDate(Date payDate) {
		this.payDate = payDate;
	}
	public Integer getPayTypeCd() {
		return payTypeCd;
	}
	public void setPayTypeCd(Integer payTypeCd) {
		this.payTypeCd = payTypeCd;
	}
	public Integer getPayCardCd() {
		return payCardCd;
	}
	public void setPayCardCd(Integer payCardCd) {
		this.payCardCd = payCardCd;
	}
	public String getPayOwnerEng() {
		return payOwnerEng;
	}
	public void setPayOwnerEng(String payOwnerEng) {
		this.payOwnerEng = payOwnerEng;
	}
	public String getPayCardNo1() {
		return payCardNo1;
	}
	public void setPayCardNo1(String payCardNo1) {
		this.payCardNo1 = payCardNo1;
	}
	public String getPayCardNo2() {
		return payCardNo2;
	}
	public void setPayCardNo2(String payCardNo2) {
		this.payCardNo2 = payCardNo2;
	}
	public String getPayCardNo3() {
		return payCardNo3;
	}
	public void setPayCardNo3(String payCardNo3) {
		this.payCardNo3 = payCardNo3;
	}
	public String getPayCardNo4() {
		return payCardNo4;
	}
	public void setPayCardNo4(String payCardNo4) {
		this.payCardNo4 = payCardNo4;
	}
	public String getPayCardCvc() {
		return payCardCvc;
	}
	public void setPayCardCvc(String payCardCvc) {
		this.payCardCvc = payCardCvc;
	}
	public String getPayCardYear() {
		return payCardYear;
	}
	public void setPayCardYear(String payCardYear) {
		this.payCardYear = payCardYear;
	}
	public String getPayCardMonth() {
		return payCardMonth;
	}
	public void setPayCardMonth(String payCardMonth) {
		this.payCardMonth = payCardMonth;
	}
	public Integer getPayTotal() {
		return payTotal;
	}
	public void setPayTotal(Integer payTotal) {
		this.payTotal = payTotal;
	}
	public String getMbrSeq() {
		return mbrSeq;
	}
	public void setMbrSeq(String mbrSeq) {
		this.mbrSeq = mbrSeq;
	}
	
//=============================================================================
// 영수증
//=============================================================================
		
	public String getXpayTypeName() {
		return xpayTypeName;
	}
	public void setXpayTypeName(String xpayTypeName) {
		this.xpayTypeName = xpayTypeName;
	}
	public String getXpayCardName() {
		return xpayCardName;
	}
	public void setXpayCardName(String xpayCardName) {
		this.xpayCardName = xpayCardName;
	}
	public Date getXbuyDateTime() {
		return xbuyDateTime;
	}
	public void setXbuyDateTime(Date xbuyDateTime) {
		this.xbuyDateTime = xbuyDateTime;
	}
	public Integer getXbuyCount() {
		return xbuyCount;
	}
	public void setXbuyCount(Integer xbuyCount) {
		this.xbuyCount = xbuyCount;
	}	
	public Integer getXbuyPrice() {
		return xbuyPrice;
	}
	public void setXbuyPrice(Integer xbuyPrice) {
		this.xbuyPrice = xbuyPrice;
	}	
}
