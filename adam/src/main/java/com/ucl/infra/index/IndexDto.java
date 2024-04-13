package com.ucl.infra.index;

import java.util.Date;

public class IndexDto {
	// 판매현황(계)
	private String brandH;
	private String brandG;
	private String brandK;
	private Integer priceH;
	private Integer priceG;
	private Integer priceK;
	private Integer countH;
	private Integer countG;
	private Integer countK;
	private Integer priceTot;
	private Integer countTot;
	
	// 판매현황(자료수)
	private Integer dataCount;
	
	// 판매현황(리스트)
	private String typeName;
	private String brandName;
	private String colorName;
	private String yearMonth;
	private String pdtName;
	private Integer buyPrice;
	private Date buyDateTime;
	
	// 판매현황(계)
	
	public String getBrandH() {
		return brandH;
	}
	public void setBrandH(String brandH) {
		this.brandH = brandH;
	}
	public String getBrandG() {
		return brandG;
	}
	public void setBrandG(String brandG) {
		this.brandG = brandG;
	}
	public String getBrandK() {
		return brandK;
	}
	public void setBrandK(String brandK) {
		this.brandK = brandK;
	}
	public Integer getPriceH() {
		return priceH;
	}
	public void setPriceH(Integer priceH) {
		this.priceH = priceH;
	}
	public Integer getPriceG() {
		return priceG;
	}
	public void setPriceG(Integer priceG) {
		this.priceG = priceG;
	}
	public Integer getPriceK() {
		return priceK;
	}
	public void setPriceK(Integer priceK) {
		this.priceK = priceK;
	}
	public Integer getCountH() {
		return countH;
	}
	public void setCountH(Integer countH) {
		this.countH = countH;
	}
	public Integer getCountG() {
		return countG;
	}
	public void setCountG(Integer countG) {
		this.countG = countG;
	}
	public Integer getCountK() {
		return countK;
	}
	public void setCountK(Integer countK) {
		this.countK = countK;
	}
	public Integer getPriceTot() {
		return priceTot;
	}
	public void setPriceTot(Integer priceTot) {
		this.priceTot = priceTot;
	}
	
	// 판매현황(자료수)
	
	public Integer getCountTot() {
		return countTot;
	}
	public void setCountTot(Integer countTot) {
		this.countTot = countTot;
	}
	
	// 판매현황(리스트)
	
	public String getTypeName() {
		return typeName;
	}
	public void setTypeName(String typeName) {
		this.typeName = typeName;
	}
	public String getBrandName() {
		return brandName;
	}
	public void setBrandName(String brandName) {
		this.brandName = brandName;
	}
	public String getColorName() {
		return colorName;
	}
	public void setColorName(String colorName) {
		this.colorName = colorName;
	}
	public String getYearMonth() {
		return yearMonth;
	}
	public void setYearMonth(String yearMonth) {
		this.yearMonth = yearMonth;
	}
	public String getPdtName() {
		return pdtName;
	}
	public void setPdtName(String pdtName) {
		this.pdtName = pdtName;
	}
	public Integer getBuyPrice() {
		return buyPrice;
	}
	public void setBuyPrice(Integer buyPrice) {
		this.buyPrice = buyPrice;
	}
	public Date getBuyDateTime() {
		return buyDateTime;
	}
	public void setBuyDateTime(Date buyDateTime) {
		this.buyDateTime = buyDateTime;
	}
	
}
