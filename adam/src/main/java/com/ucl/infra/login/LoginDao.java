package com.ucl.infra.login;

public interface LoginDao {
	// 회원정보조회
	public LoginDto selectOne(LoginDto dto);
}
