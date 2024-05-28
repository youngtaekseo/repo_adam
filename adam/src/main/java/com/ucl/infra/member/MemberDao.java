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
	
	// 다중선택자료 삭제
	public int deleteList(MemberVo vo);
	//이미지갯수
	public MemberDto selectOneImageCount(MemberDto dto);
	//상품이미지조회
	public List<MemberDto> selectListImages(MemberDto dto);
}
