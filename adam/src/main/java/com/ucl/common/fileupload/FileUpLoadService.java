package com.ucl.common.fileupload;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileUpLoadService {
	@Autowired
	FileUpLoadDao dao;
	
	// 파일업로드 저장
	public int insertFileUpLoad(FileUpLoadDto dto) {
		return dao.insertFileUpLoad(dto);
	}
	
	// 파일업로드 수정
	public int updateFileUpLoad(FileUpLoadDto dto) {
		return dao.updateFileUpLoad(dto);
	};
}
