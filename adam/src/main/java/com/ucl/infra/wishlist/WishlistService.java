package com.ucl.infra.wishlist;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class WishlistService {

	@Autowired
	WishlistDao dao;
	
	// 등록여부확인
	public int selectOneCount(WishlistDto dto) {
		return dao.selectOneCount(dto);
	}
		
	// 등록
	public int insertWishlist(WishlistDto dto) {
		return dao.insertWishlist(dto);
	}
	
	// 삭제
	public int deleteWishlist(WishlistVo vo) {
		return dao.deleteWishlist(vo);
	}
}
