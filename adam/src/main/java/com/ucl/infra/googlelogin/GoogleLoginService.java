package com.ucl.infra.googlelogin;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class GoogleLoginService {
	@Autowired
	GoogleLoginDao dao;

	// 로그인 id 확인
	public GoogleLoginDto selectOneLogin(GoogleLoginDto dto) {
		return dao.selectOneLogin(dto);
	};
	
	// 회원등록
	public int insert(GoogleLoginDto dto) {
		return dao.insert(dto);
	};	
}
