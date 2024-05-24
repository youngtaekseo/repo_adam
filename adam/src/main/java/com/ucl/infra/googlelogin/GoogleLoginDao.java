package com.ucl.infra.googlelogin;

public interface GoogleLoginDao {
	// 로그인 id 확인
	public GoogleLoginDto selectOneLogin(GoogleLoginDto dto);
	
	// 회원등록
	public int insert(GoogleLoginDto dto);
}
