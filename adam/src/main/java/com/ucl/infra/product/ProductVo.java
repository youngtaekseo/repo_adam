package com.ucl.infra.product;

import com.ucl.common.base.BaseVo;

public class ProductVo extends BaseVo {
	private String  shSeq;
	private Integer shCarType     = null; 	// 1:중형차, 2:대형차
	private Integer shCarBrand    = null; 	// 1:현대, 2:제네시스, 3:기아
	private Integer shCarColor    = null; 	// 1:검정색, 2:흰색
	private Integer shRecommend   = null; 	// 1:추천, 2:비추천
	private Integer shAccident    = null; 	// 1:무사고, 2:유사고
	
	private Integer shOptionRunKm = null;	// 주행기록
	private Integer shOptionRunKmFrom;		// 주행기록 시작값
	private Integer shOptionRunKmTo;		// 주행기록 종료값
	private Integer shOptionYear  = null;	// 연식
	private Integer shOptionYearFrom; 		// 연식(년) 시작값
	private Integer shOptionYearTo;   		// 연식(년) 종료값
	private Integer shOptionMonthFrom; 		// 연식(월) 시작값
	private Integer shOptionMonthTo;   		// 연식(월) 종료값
	private Integer shPrice;   				// 판매가액
	private Integer shPriceFrom;   			// 판매가액 시작값
	private Integer shPriceTo;   			// 판매가액 종료값
	private Integer shDispCount = 10;       // 사용자 메인화면에 리스트 표시 갯수
	private Integer shNewRegNy = 0;         // 최근등록여부 0:해당없음, 1:최근등록조회
	private String  shMbrSeq;               // 회원순번

	private String[] shCheckboxTypeArray;  // 차종
	private String[] shCheckboxBrandArray; // 브랜드
	private String[] shCheckboxColorArray; // 색상
	
	private Integer shFromYear;   // 현재년도
	private Integer shToYear;     // 현재년도 - shRange 결과 년도
	private Integer shRange = 20; // 현재년도 이전 몇년까지 표시할것이가 : 예) 2024 - 3 하면 2024, 2023, 2022
	
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
	public Integer getShOptionRunKm() {
		return shOptionRunKm;
	}
	public void setShOptionRunKm(Integer shOptionRunKm) {
		this.shOptionRunKm = shOptionRunKm;
	}
	public Integer getShOptionRunKmFrom() {
		return shOptionRunKmFrom;
	}
	public void setShOptionRunKmFrom(Integer shOptionRunKmFrom) {
		this.shOptionRunKmFrom = shOptionRunKmFrom;
	}
	public Integer getShOptionRunKmTo() {
		return shOptionRunKmTo;
	}
	public void setShOptionRunKmTo(Integer shOptionRunKmTo) {
		this.shOptionRunKmTo = shOptionRunKmTo;
	}
	public Integer getShOptionYear() {
		return shOptionYear;
	}
	public void setShOptionYear(Integer shOptionYear) {
		this.shOptionYear = shOptionYear;
	}
	public Integer getShOptionYearFrom() {
		return shOptionYearFrom;
	}
	public void setShOptionYearFrom(Integer shOptionYearFrom) {
		this.shOptionYearFrom = shOptionYearFrom;
	}
	public Integer getShOptionYearTo() {
		return shOptionYearTo;
	}
	public void setShOptionYearTo(Integer shOptionYearTo) {
		this.shOptionYearTo = shOptionYearTo;
	}
	public Integer getShOptionMonthFrom() {
		return shOptionMonthFrom;
	}
	public void setShOptionMonthFrom(Integer shOptionMonthFrom) {
		this.shOptionMonthFrom = shOptionMonthFrom;
	}
	public Integer getShOptionMonthTo() {
		return shOptionMonthTo;
	}
	public void setShOptionMonthTo(Integer shOptionMonthTo) {
		this.shOptionMonthTo = shOptionMonthTo;
	}
	public Integer getShDispCount() {
		return shDispCount;
	}
	public void setShDispCount(Integer shDispCount) {
		this.shDispCount = shDispCount;
	}
	public Integer getShNewRegNy() {
		return shNewRegNy;
	}
	public void setShNewRegNy(Integer shNewRegNy) {
		this.shNewRegNy = shNewRegNy;
	}
	public String[] getShCheckboxTypeArray() {
		return shCheckboxTypeArray;
	}
	public void setShCheckboxTypeArray(String[] shCheckboxTypeArray) {
		this.shCheckboxTypeArray = shCheckboxTypeArray;
	}
	public String[] getShCheckboxBrandArray() {
		return shCheckboxBrandArray;
	}
	public void setShCheckboxBrandArray(String[] shCheckboxBrandArray) {
		this.shCheckboxBrandArray = shCheckboxBrandArray;
	}
	public Integer getShPrice() {
		return shPrice;
	}
	public void setShPrice(Integer shPrice) {
		this.shPrice = shPrice;
	}
	public Integer getShPriceFrom() {
		return shPriceFrom;
	}
	public void setShPriceFrom(Integer shPriceFrom) {
		this.shPriceFrom = shPriceFrom;
	}
	public Integer getShPriceTo() {
		return shPriceTo;
	}
	public void setShPriceTo(Integer shPriceTo) {
		this.shPriceTo = shPriceTo;
	}
	public String[] getShCheckboxColorArray() {
		return shCheckboxColorArray;
	}
	public void setShCheckboxColorArray(String[] shCheckboxColorArray) {
		this.shCheckboxColorArray = shCheckboxColorArray;
	}
	public Integer getShFromYear() {
		return shFromYear;
	}
	public void setShFromYear(Integer shFromYear) {
		this.shFromYear = shFromYear;
	}
	public Integer getShToYear() {
		return shToYear;
	}
	public void setShToYear(Integer shToYear) {
		this.shToYear = shToYear;
	}
	public Integer getShRange() {
		return shRange;
	}
	public void setShRange(Integer shRange) {
		this.shRange = shRange;
	}
	public String getShMbrSeq() {
		return shMbrSeq;
	}
	public void setShMbrSeq(String shMbrSeq) {
		this.shMbrSeq = shMbrSeq;
	}	
}
