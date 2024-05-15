package com.ucl.common.fileupload;

import java.util.List;

public interface FileUpLoadDao {
	// 파일업로드 저장
	public int insertFileUpLoad(FileUpLoadDto dto);
	
	// 파일업로드 수정
	public int updateFileUpLoad(FileUpLoadDto dto);
	
	// 파일업로드 삭제
	public int deleteFileUpLoad(FileUpLoadDto dto);
	
	// 키조회
	public List<FileUpLoadDto> selectListUuidName(FileUpLoadDto dto);	
}
