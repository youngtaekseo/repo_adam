package com.ucl.infra.review;

import java.util.List;

public interface ReviewDao {
	public List<ReviewDto> selectList(ReviewVo vo);
	
	public int insert(ReviewDto dto);
	public int update(ReviewDto dto);
	public int delete(ReviewDto dto);
}
