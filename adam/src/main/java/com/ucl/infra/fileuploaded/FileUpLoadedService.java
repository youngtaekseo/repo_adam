package com.ucl.infra.fileuploaded;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileUpLoadedService {
	@Autowired
	FileUpLoadedDao dao;
	
	// 파일업로드 저장
	public int insertFileUpLoaded(FileUpLoadedDto dto) {
		return dao.insertFileUpLoaded(dto);
	}
	
	// 파일업로드 수정
	public int updateFileUpLoaded(FileUpLoadedDto dto) {
		return dao.updateFileUpLoaded(dto);
	};
}
