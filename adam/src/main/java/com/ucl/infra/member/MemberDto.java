package com.ucl.infra.member;

import java.util.Date;

import org.springframework.web.multipart.MultipartFile;

public class MemberDto {
	
	private String  mbrSeq;
	private Integer mbrType; // 1:관리자, 2:사용자
	private String  mbrName;
	private Integer mbrSex;
	private String  mbrBirthday;
	private String  mbrEmail;
	private String  mbrPassword;
	private String  mbrZipCode;
	private String  mbrAddr;
	private String  mbrAddrD;
	private String  mbrAddrRemark;
	private Double  mbrPointLat;
	private Double  mbrPointLon;
	private Date    mbrRegDt;
	private Date    mbrUdtDt;
	private Integer mbrDelNy; // 0:미삭제, 1:삭제
	
	private Integer xmbrCount;
	private String  xmbrPwConfirm;
	private String  xmbrPasswordPre;
	private Integer xrowSeq;
	private String  xmbrImgName;
	private String  xmbrImgPathName;
	
	private MultipartFile[] uploadFiles;
	
//	===================================
	
	public String getMbrSeq() {
		return mbrSeq;
	}
	public void setMbrSeq(String mbrSeq) {
		this.mbrSeq = mbrSeq;
	}
	public Integer getMbrType() {
		return mbrType;
	}
	public void setMbrType(Integer mbrType) {
		this.mbrType = mbrType;
	}
	public String getMbrName() {
		return mbrName;
	}
	public void setMbrName(String mbrName) {
		this.mbrName = mbrName;
	}
	public Integer getMbrSex() {
		return mbrSex;
	}
	public void setMbrSex(Integer mbrSex) {
		this.mbrSex = mbrSex;
	}
	public String getMbrBirthday() {
		return mbrBirthday;
	}
	public void setMbrBirthday(String mbrBirthday) {
		this.mbrBirthday = mbrBirthday;
	}
	public String getMbrEmail() {
		return mbrEmail;
	}
	public void setMbrEmail(String mbrEmail) {
		this.mbrEmail = mbrEmail;
	}
	public String getMbrPassword() {
		return mbrPassword;
	}
	public void setMbrPassword(String mbrPassword) {
		this.mbrPassword = mbrPassword;
	}
	public String getMbrZipCode() {
		return mbrZipCode;
	}
	public void setMbrZipCode(String mbrZipCode) {
		this.mbrZipCode = mbrZipCode;
	}
	public String getMbrAddr() {
		return mbrAddr;
	}
	public void setMbrAddr(String mbrAddr) {
		this.mbrAddr = mbrAddr;
	}
	public String getMbrAddrD() {
		return mbrAddrD;
	}
	public void setMbrAddrD(String mbrAddrD) {
		this.mbrAddrD = mbrAddrD;
	}
	public String getMbrAddrRemark() {
		return mbrAddrRemark;
	}
	public void setMbrAddrRemark(String mbrAddrRemark) {
		this.mbrAddrRemark = mbrAddrRemark;
	}
	public Double getMbrPointLat() {
		return mbrPointLat;
	}
	public void setMbrPointLat(Double mbrPointLat) {
		this.mbrPointLat = mbrPointLat;
	}
	public Double getMbrPointLon() {
		return mbrPointLon;
	}
	public void setMbrPointLon(Double mbrPointLon) {
		this.mbrPointLon = mbrPointLon;
	}
	public Date getMbrRegDt() {
		return mbrRegDt;
	}
	public void setMbrRegDt(Date mbrRegDt) {
		this.mbrRegDt = mbrRegDt;
	}
	public Date getMbrUdtDt() {
		return mbrUdtDt;
	}
	public void setMbrUdtDt(Date mbrUdtDt) {
		this.mbrUdtDt = mbrUdtDt;
	}
	public Integer getMbrDelNy() {
		return mbrDelNy;
	}
	public void setMbrDelNy(Integer mbrDelNy) {
		this.mbrDelNy = mbrDelNy;
	}
	public Integer getXmbrCount() {
		return xmbrCount;
	}
	public void setXmbrCount(Integer xmbrCount) {
		this.xmbrCount = xmbrCount;
	}
	public String getXmbrPasswordPre() {
		return xmbrPasswordPre;
	}
	public void setXmbrPasswordPre(String xmbrPasswordPre) {
		this.xmbrPasswordPre = xmbrPasswordPre;
	}
	public String getXmbrPwConfirm() {
		return xmbrPwConfirm;
	}
	public void setXmbrPwConfirm(String xmbrPwConfirm) {
		this.xmbrPwConfirm = xmbrPwConfirm;
	}
	public Integer getXrowSeq() {
		return xrowSeq;
	}
	public void setXrowSeq(Integer xrowSeq) {
		this.xrowSeq = xrowSeq;
	}
	public MultipartFile[] getUploadFiles() {
		return uploadFiles;
	}
	public void setUploadFiles(MultipartFile[] uploadFiles) {
		this.uploadFiles = uploadFiles;
	}
	public String getXmbrImgName() {
		return xmbrImgName;
	}
	public void setXmbrImgName(String xmbrImgName) {
		this.xmbrImgName = xmbrImgName;
	}
	public String getXmbrImgPathName() {
		return xmbrImgPathName;
	}
	public void setXmbrImgPathName(String xmbrImgPathName) {
		this.xmbrImgPathName = xmbrImgPathName;
	}
	
}
