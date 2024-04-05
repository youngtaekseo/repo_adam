package com.ucl.infra.wishlist;

public interface WishlistDao {

	// 등록여부확인
	public int selectOneCount(WishlistDto dto);
	
	// 찜 입력
	public int insertWishlist(WishlistDto dto);
	
	// 찜 삭제
	public int deleteWishlist(WishlistVo vo);
}
