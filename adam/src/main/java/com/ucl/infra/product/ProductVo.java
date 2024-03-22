package com.ucl.infra.product;

import com.ucl.common.base.BaseVo;

public class ProductVo extends BaseVo {
	private String shSeq;
	private Integer shCarType     = null; 	// 1:중형차, 2:대형차
	private Integer shCarBrand    = null; 	// 1:현대, 2:제네시스, 3:기아
	private Integer shCarColor    = null; 	// 1:검정색, 2:흰색
	private Integer shRecommend   = null; 	// 1:추천, 2:비추천
	private Integer shAccident    = null; 	// 1:무사고, 2:유사고
	private Integer shOptionRunKm = null;	// 주행기록
	private Integer shOptionRunKmFrom; 		// 주행기록 시작값
	private Integer shOptionRunKmTo;   		// 주행기록 종료값
	private Integer shOptionYear  = null;	// 연식
	private Integer shOptionYearFrom; 		// 연식 시작값
	private Integer shOptionYearTo;   		// 연식 종료값
	
//=============================================================================
    /*
     * 주석에 사용된 단어 정의
     * 페이지: 화면 아래에 위치하는 특정 페이지를 지칭하는 숫자
     * 인덱스: 특정 페이지에서 특정 게시물을 지시하는 숫자
     */

	private Integer     rowCount  = 5;     // 한 페이지 당 보여줄 게시물 개수
	private Integer     pageCount = 3;     // 한 블럭에 몇 개의 페이지 개수
	private Integer     totalCount;        // 총 게시물 개수
	private Integer     page;              // 현재 페이지
	
	private Integer     startPage = 1;     // 한 블럭의 시작 페이지: 기본 값 1 // ex) 1 2 3 4 5 일 때 1을 의미.
	private Integer     endPage;           // 한 블럭의 끝 페이지

	private Integer     totalPageCount;    // 총 페이지 개수

	private Boolean isPrev    = false; // 다음 페이지로 이동하는 버튼 유무
	private Boolean isNext    = false; // 이전 페이지로 이동하는 버튼 유무

	private Integer     offset = 0;            // 얼만큼 끊어서 가져올 것인가.

    public void setProductVo(final int totalCount, final int page) {
		// 총 페이지 개수 구하기
		setTotalPageCount(totalCount, this.rowCount); 
		
		// 한 블럭의 첫 페이지 구하기
		setStartPage(this.startPage, page, this.pageCount);
	
		// 한 블럭의 끝 페이지 구하기
		setEndpage(this.startPage, this.pageCount, this.totalPageCount); 
	
		// 이전 블록 버튼 유무 판별하기
		isPrev(page, this.pageCount); 
	
		// 다음 블록 버튼 유무 판별하기
		isNext(this.endPage, this.totalPageCount); 
	
		// offset 구하기
		setOffset(page, this.rowCount); 
    }

    // 총 페이지 개수 구하기
    private void setTotalPageCount(final int totalCount, final int rowCount) {
        this.totalPageCount = (int) Math.ceil(totalCount * 1.0 / rowCount);
    }

    // 한 블럭의 첫 페이지 구하기
    private void setStartPage(final int startPage, final int page, final int pageCount) {
        this.startPage = startPage + (((page - startPage) / pageCount) * pageCount);
    }

    // 한 블럭의 끝 페이지 구하기
    private void setEndpage(final int startPage, final int pageCount, final int totalPageCount) {
        this.endPage = ((startPage - 1) + pageCount) < totalPageCount ? (startPage - 1) + pageCount : totalPageCount;
    }

    // 이전 블럭으로 이동할 버튼 생성 유무
    private void isPrev(final int page, final int pageCount) {
        this.isPrev = 1 < ((page * 1.0) / pageCount);
    }

    // 다음 블럭으로 이동할 버튼 생성 유무
    private void isNext(final int endPage, final int totalPageCount) {
        this.isNext = endPage < totalPageCount;
    }

    // offset 구하기 // 쿼리 select 시 끊어서 가져오기
    private void setOffset(final int page, final int rowCount) {
    	if((page - 1) == 0) {
    		System.out.println("page : "+page+", (page - 1) == 0)");
    		this.offset = page;
    	} else {
    		System.out.println("(page - 1) != 0)");
    		this.offset = (page - 1) * rowCount;	
    	}
    }
    
	public Integer getRowCount() {
		return rowCount;
	}

	public Integer getPageCount() {
		return pageCount;
	}

	public Integer getTotalCount() {
		return totalCount;
	}

	public Integer getPage() {
		return page;
	}

	public Integer getStartPage() {
		return startPage;
	}

	public Integer getEndPage() {
		return endPage;
	}

	public Integer getTotalPageCount() {
		return totalPageCount;
	}

	public Boolean getIsPrev() {
		return isPrev;
	}

	public Boolean getIsNext() {
		return isNext;
	}

	public Integer getOffset() {
		return offset;
	}
	
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
    
}
