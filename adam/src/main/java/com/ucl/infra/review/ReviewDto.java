package com.ucl.infra.review;

import java.util.Date;

public class ReviewDto {
	private String  rvwSeq; // 댓글순번
	private String  mbrSeq; // 회원순번
	private String  rvwRemark; // 댓글내용
	private Integer rvwStar;  // 댓글별점
	private Date    rvwDateTime; // 댓글등록일시
	private String  pdtSeq; // 제품순번
	
	private Integer xrvwCount; // 댓글갯수
	private Integer xrvwStar; // 댓글별점
	private String xmbrName; // 회원명

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
	
	public Integer getXrvwStar() {
		return xrvwStar;
	}

	public void setXrvwStar(Integer xrvwStar) {
		this.xrvwStar = xrvwStar;
	}

	public String getXmbrName() {
		return xmbrName;
	}

	public void setXmbrName(String xmbrName) {
		this.xmbrName = xmbrName;
	}	

}
