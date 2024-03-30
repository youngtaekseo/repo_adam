package com.ucl.infra.member;

import java.util.List;

public interface MemberDao {
	public List<MemberDto> selectList(MemberVo vo);
	public List<MemberDto> selectListCode(MemberVo vo);
	public MemberDto selectOne(MemberDto dto);
	public MemberDto selectOneLogin(MemberDto dto); 
	public int selectOneCount(MemberVo vo); 
	
	public int insert(MemberDto dto);
	public int update(MemberDto dto);
	public int delete(MemberDto dto);
	public int updatePassword(MemberDto dto);
	public int updateDelNy(MemberDto dto);
}
