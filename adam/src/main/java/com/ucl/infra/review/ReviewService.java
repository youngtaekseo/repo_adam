package com.ucl.infra.review;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ReviewService {

	@Autowired
	ReviewDao dao;
	
	public List<ReviewDto> selectList(ReviewVo vo) {
		return dao.selectList(vo);
	}

}
