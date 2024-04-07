package com.ucl.infra.review;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

	@Autowired
	ReviewDao dao;
	
	// 댓글조회
	public List<ReviewDto> selectList(ReviewVo vo) {
		return dao.selectList(vo);
	}
	
	// 댓글등록
	public int insertReview(ReviewDto dto) {
		return dao.insertReview(dto);
	};	

}
