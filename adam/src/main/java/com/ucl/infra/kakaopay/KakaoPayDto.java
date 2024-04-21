package com.ucl.infra.kakaopay;

import java.util.Date;

public class KakaoPayDto {
    private String tid;                  // 결제 고유 번호
    private String next_redirect_pc_url; // web - 받는 결제 페이지
    private Date   created_at;
    
    // 결제상품정보
	private String payTypeCd;	// 결제종류
	private String mbrSeq;		// 회원순번
	private String paySeq;		// 결제순번
    
	public String getTid() {
		return tid;
	}
	public void setTid(String tid) {
		this.tid = tid;
	}
	public String getNext_redirect_pc_url() {
		return next_redirect_pc_url;
	}
	public void setNext_redirect_pc_url(String next_redirect_pc_url) {
		this.next_redirect_pc_url = next_redirect_pc_url;
	}
	public Date getCreated_at() {
		return created_at;
	}
	public void setCreated_at(Date created_at) {
		this.created_at = created_at;
	}
	public String getPayTypeCd() {
		return payTypeCd;
	}
	public void setPayTypeCd(String payTypeCd) {
		this.payTypeCd = payTypeCd;
	}
	public String getMbrSeq() {
		return mbrSeq;
	}
	public void setMbrSeq(String mbrSeq) {
		this.mbrSeq = mbrSeq;
	}
	public String getPaySeq() {
		return paySeq;
	}
	public void setPaySeq(String paySeq) {
		this.paySeq = paySeq;
	}
	@Override
	public String toString() {
		return super.toString();
	}
    
    
}
