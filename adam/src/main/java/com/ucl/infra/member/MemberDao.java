package com.ucl.infra.member;

import java.util.List;

public interface MemberDao {
	public List<MemberDto> selectList(MemberVo vo);
	public List<MemberDto> selectListCode(MemberVo vo);
	public MemberDto selectOne(MemberDto dto);
	public MemberDto selectPassword(MemberDto dto);
	
	public int insert(MemberDto dto);
	public int update(MemberDto dto);
	public int delete(MemberDto dto);
	
	public int updatePw(MemberDto dto);
	public int updateDelNy(MemberDto dto);
	
	public int getCount(MemberVo vo); 
	
	public MemberDto selectLoginId(MemberDto dto); 
	public MemberDto selectLoginPassword(MemberDto dto); 
}
