package com.ucl.common.fileupload;

import java.util.List;

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
	
	// 파일업로드 삭제
	public int deleteFileUpLoad(FileUpLoadDto dto) {
		return dao.deleteFileUpLoad(dto);
	};	
	
	// 키조회
	public List<FileUpLoadDto> selectListUuidName(FileUpLoadDto dto) {
		return dao.selectListUuidName(dto);
	};
}
