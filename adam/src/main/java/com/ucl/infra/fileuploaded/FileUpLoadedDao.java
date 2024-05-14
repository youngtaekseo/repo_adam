package com.ucl.infra.fileuploaded;

public interface FileUpLoadedDao {
	// 파일업로드 저장
	public int insertFileUpLoaded(FileUpLoadedDto dto);
	
	// 파일업로드 수정
	public int updateFileUpLoaded(FileUpLoadedDto dto);
}
