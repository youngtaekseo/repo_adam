package com.ucl.infra.search;

import java.util.List;

import com.ucl.infra.codegroup.CodeGroupDto;

public interface SearchDao {
    /**
     * 게시글 리스트 조회
     * @return 게시글 리스트
     */	
	public List<CodeGroupDto> findAll(SearchVo vo);
	
    /**
     * 게시글 수 카운팅
     * @return 게시글 수
     */
	public int count(SearchVo vo);    
}
