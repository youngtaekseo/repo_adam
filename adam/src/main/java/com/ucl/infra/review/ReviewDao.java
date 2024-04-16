package com.ucl.infra.review;

import java.util.List;

public interface ReviewDao {
	
	// 댓글조회
	public List<ReviewDto> selectList(ReviewVo vo);
	
	// 댓글갯수, 별점평균
	public ReviewDto selectListCount(ReviewVo vo);
	
	// 댓글등록
	public int insertReview(ReviewDto dto);
}
