package com.ucl.infra.kakaopay;

public class KakaoPayVo {

	private String partner_order_id; // 주문 번호
	private String partner_user_id;  // 회원 아이디
	private String item_name;		 // 상품 명
	private String quantity;		 // 상품 수량
	private String total_amount;	 // 상품 가격
	private String tax_free_amount;	 // 상품 비과세 금액	
	private String approval_url = "http://localhost:8081/kakaoPaySuccess";  // 성공시 url
	private String cancel_url   = "http://localhost:8081/productUsrCheckOut"; // 취소시 url
	private String fail_url     = "http://localhost:8081/productUsrCheckOut"; // 실패시 url
	
	private String payTypeCd;
	private String mbrSeq;
	private String paySeq;
	
	public String getPartner_order_id() {
		return partner_order_id;
	}
	public void setPartner_order_id(String partner_order_id) {
		this.partner_order_id = partner_order_id;
	}
	public String getPartner_user_id() {
		return partner_user_id;
	}
	public void setPartner_user_id(String partner_user_id) {
		this.partner_user_id = partner_user_id;
	}
	public String getItem_name() {
		return item_name;
	}
	public void setItem_name(String item_name) {
		this.item_name = item_name;
	}
	public String getQuantity() {
		return quantity;
	}
	public void setQuantity(String quantity) {
		this.quantity = quantity;
	}
	public String getTotal_amount() {
		return total_amount;
	}
	public void setTotal_amount(String total_amount) {
		this.total_amount = total_amount;
	}
	public String getTax_free_amount() {
		return tax_free_amount;
	}
	public void setTax_free_amount(String tax_free_amount) {
		this.tax_free_amount = tax_free_amount;
	}
	public String getApproval_url() {
		return approval_url;
	}
	public void setApproval_url(String approval_url) {
		this.approval_url = approval_url;
	}
	public String getCancel_url() {
		return cancel_url;
	}
	public void setCancel_url(String cancel_url) {
		this.cancel_url = cancel_url;
	}
	public String getFail_url() {
		return fail_url;
	}
	public void setFail_url(String fail_url) {
		this.fail_url = fail_url;
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
	
}
