package com.ucl.infra.fileuploaded;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class FileUpLoadedService {
	@Autowired
	FileUpLoadedDao dao;
	
	public int insertFileUpLoaded(FileUpLoadedDto dto) {
		return dao.insertFileUpLoaded(dto);
	}
}
