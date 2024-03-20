package com.ucl.infra.product;

import com.ucl.common.base.BaseVo;

public class ProductVo extends BaseVo {
	private String shSeq;
	private Integer shCarType; // 1:중형차, 2:대형차
	private Integer shCarBrand; // 1:현대, 2:제네시스, 3:기아
	private Integer shCarColor; // 1:검정색, 2:흰색
	private Integer shRecommend; // 1:추천, 2:비추천
	private Integer shAccident; // 1:무사고, 2:유사고
	
//=============================================================================
	
	public String getShSeq() {
		return shSeq;
	}
	public void setShSeq(String shSeq) {
		this.shSeq = shSeq;
	}
	public Integer getShCarType() {
		return shCarType;
	}
	public void setShCarType(Integer shCarType) {
		this.shCarType = shCarType;
	}
	public Integer getShCarBrand() {
		return shCarBrand;
	}
	public void setShCarBrand(Integer shCarBrand) {
		this.shCarBrand = shCarBrand;
	}
	public Integer getShCarColor() {
		return shCarColor;
	}
	public void setShCarColor(Integer shCarColor) {
		this.shCarColor = shCarColor;
	}
	public Integer getShRecommend() {
		return shRecommend;
	}
	public void setShRecommend(Integer shRecommend) {
		this.shRecommend = shRecommend;
	}
	public Integer getShAccident() {
		return shAccident;
	}
	public void setShAccident(Integer shAccident) {
		this.shAccident = shAccident;
	}
	
}
