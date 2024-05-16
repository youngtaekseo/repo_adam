package com.ucl.common.fileupload;

import java.util.List;

public interface FileUpLoadDao {
	// 파일업로드 저장
	public int insertFileUpLoad(FileUpLoadDto dto);
	
	// 대표수정
	public int updateDefaultNy(FileUpLoadDto dto);
	
	// 파일정보수정
	public int updateUuidName(FileUpLoadDto dto);
	
	// 파일업로드 삭제
	public int deleteFileUpLoad(FileUpLoadDto dto);
	
	// 키조회
	public List<FileUpLoadDto> selectListUuidName(FileUpLoadDto dto);	
}
