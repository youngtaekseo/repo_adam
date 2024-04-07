package com.ucl.infra.review;

import java.util.Date;

public class ReviewDto {
	private String  rvwSeq;
	private String  mbrSeq;
	private String  rvwRemark;
	private Integer rvwStar;
	private Date    rvwDateTime;
	private String  pdtSeq;
	
	private Integer xrvwCount;
	private String xmbrName;

	//=============================================================================

	public String getRvwSeq() {
		return rvwSeq;
	}

	public void setRvwSeq(String rvwSeq) {
		this.rvwSeq = rvwSeq;
	}

	public String getMbrSeq() {
		return mbrSeq;
	}

	public void setMbrSeq(String mbrSeq) {
		this.mbrSeq = mbrSeq;
	}

	public String getRvwRemark() {
		return rvwRemark;
	}

	public void setRvwRemark(String rvwRemark) {
		this.rvwRemark = rvwRemark;
	}

	public Integer getRvwStar() {
		return rvwStar;
	}

	public void setRvwStar(Integer rvwStar) {
		this.rvwStar = rvwStar;
	}

	public Date getRvwDateTime() {
		return rvwDateTime;
	}

	public void setRvwDateTime(Date rvwDateTime) {
		this.rvwDateTime = rvwDateTime;
	}

	public String getPdtSeq() {
		return pdtSeq;
	}

	public void setPdtSeq(String pdtSeq) {
		this.pdtSeq = pdtSeq;
	}

	public Integer getXrvwCount() {
		return xrvwCount;
	}

	public void setXrvwCount(Integer xrvwCount) {
		this.xrvwCount = xrvwCount;
	}
	
	public String getXmbrName() {
		return xmbrName;
	}

	public void setXmbrName(String xmbrName) {
		this.xmbrName = xmbrName;
	}	

}
