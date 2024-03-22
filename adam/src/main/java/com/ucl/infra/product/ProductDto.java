package com.ucl.infra.product;

import java.util.Date;

public class ProductDto {
	private String pdtSeq;
	private Integer pdtTypeCd;
	private Integer pdtBrandCd;
	private String pdtName;
	private Integer pdtAccidentCd;
	private Integer pdtYear;
	private Double pdtRunKm;
	private Integer pdtColorCd;
	private Double pdtPrice;
	private Integer pdtRecommendCd;
	private Date pdtRegDt;
	private Date pdtUdtDt;
	private Integer pdtDelNy;
	
	private Integer xpdtCount;
//=============================================================================
	public String getPdtSeq() {
		return pdtSeq;
	}
	public void setPdtSeq(String pdtSeq) {
		this.pdtSeq = pdtSeq;
	}
	public Integer getPdtTypeCd() {
		return pdtTypeCd;
	}
	public void setPdtTypeCd(Integer pdtTypeCd) {
		this.pdtTypeCd = pdtTypeCd;
	}
	public Integer getPdtBrandCd() {
		return pdtBrandCd;
	}
	public void setPdtBrandCd(Integer pdtBrandCd) {
		this.pdtBrandCd = pdtBrandCd;
	}
	public String getPdtName() {
		return pdtName;
	}
	public void setPdtName(String pdtName) {
		this.pdtName = pdtName;
	}
	public Integer getPdtAccidentCd() {
		return pdtAccidentCd;
	}
	public void setPdtAccidentCd(Integer pdtAccidentCd) {
		this.pdtAccidentCd = pdtAccidentCd;
	}
	public Integer getPdtYear() {
		return pdtYear;
	}
	public void setPdtYear(Integer pdtYear) {
		this.pdtYear = pdtYear;
	}
	public Double getPdtRunKm() {
		return pdtRunKm;
	}
	public void setPdtRunKm(Double pdtRunKm) {
		this.pdtRunKm = pdtRunKm;
	}
	public Integer getPdtColorCd() {
		return pdtColorCd;
	}
	public void setPdtColorCd(Integer pdtColorCd) {
		this.pdtColorCd = pdtColorCd;
	}
	public Double getPdtPrice() {
		return pdtPrice;
	}
	public void setPdtPrice(Double pdtPrice) {
		this.pdtPrice = pdtPrice;
	}
	public Integer getPdtRecommendCd() {
		return pdtRecommendCd;
	}
	public void setPdtRecommendCd(Integer pdtRecommendCd) {
		this.pdtRecommendCd = pdtRecommendCd;
	}
	public Date getPdtRegDt() {
		return pdtRegDt;
	}
	public void setPdtRegDt(Date pdtRegDt) {
		this.pdtRegDt = pdtRegDt;
	}
	public Date getPdtUdtDt() {
		return pdtUdtDt;
	}
	public void setPdtUdtDt(Date pdtUdtDt) {
		this.pdtUdtDt = pdtUdtDt;
	}
	public Integer getPdtDelNy() {
		return pdtDelNy;
	}
	public void setPdtDelNy(Integer pdtDelNy) {
		this.pdtDelNy = pdtDelNy;
	}
	public Integer getXpdtCount() {
		return xpdtCount;
	}
	public void setXpdtCount(Integer xpdtCount) {
		this.xpdtCount = xpdtCount;
	}
}
