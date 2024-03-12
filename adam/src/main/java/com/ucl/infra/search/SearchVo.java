package com.ucl.infra.search;

import com.ucl.common.base.BaseVo;
import com.ucl.common.util.UtilPaging;

public class SearchVo extends BaseVo {
	private String seq;

    private int page = 1;             // 현재 페이지 번호
    private int recordSize = 10;       // 페이지당 출력할 데이터 개수
    private int pageSize = 10;         // 화면 하단에 출력할 페이지 사이즈
    private String keyword;       // 검색 키워드
    private String searchType;    // 검색 유형
    private UtilPaging pagination;    // 페이지네이션 정보
    
    public int getOffset() {
        return (page - 1) * recordSize;
    }

	public UtilPaging getPagination() {
		return pagination;
	}

	public void setPagination(UtilPaging pagination) {
		this.pagination = pagination;
	}

	public String getSeq() {
		return seq;
	}

	public void setSeq(String seq) {
		this.seq = seq;
	}

	public int getPage() {
		return page;
	}

	public void setPage(int page) {
		this.page = page;
	}

	public int getRecordSize() {
		return recordSize;
	}

	public void setRecordSize(int recordSize) {
		this.recordSize = recordSize;
	}

	public int getPageSize() {
		return pageSize;
	}

	public void setPageSize(int pageSize) {
		this.pageSize = pageSize;
	}

	public String getKeyword() {
		return keyword;
	}

	public void setKeyword(String keyword) {
		this.keyword = keyword;
	}

	public String getSearchType() {
		return searchType;
	}

	public void setSearchType(String searchType) {
		this.searchType = searchType;
	}
    
    
}
