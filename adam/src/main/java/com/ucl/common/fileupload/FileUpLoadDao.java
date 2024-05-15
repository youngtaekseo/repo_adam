package com.ucl.common.fileupload;

public interface FileUpLoadDao {
	// 파일업로드 저장
	public int insertFileUpLoad(FileUpLoadDto dto);
	
	// 파일업로드 수정
	public int updateFileUpLoad(FileUpLoadDto dto);
}
