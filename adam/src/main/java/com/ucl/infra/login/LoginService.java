package com.ucl.infra.login;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class LoginService {

	@Autowired
	LoginDao dao;
	
	// 회원정보조회
	public LoginDto selectOne(LoginDto dto) {
		return dao.selectOne(dto);
	};
}
