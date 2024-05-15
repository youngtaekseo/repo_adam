package com.ucl.common.base;

import org.springframework.web.multipart.MultipartFile;

public class BaseDto {
	// fileupload
	private MultipartFile uploadFile;
	private MultipartFile[] uploadFiles;

	// fileupload
	//=========================================================================
	public MultipartFile getUploadFile() {
		return uploadFile;
	}
	public void setUploadFile(MultipartFile uploadFile) {
		this.uploadFile = uploadFile;
	}
	public MultipartFile[] getUploadFiles() {
		return uploadFiles;
	}
	public void setUploadFiles(MultipartFile[] uploadFiles) {
		this.uploadFiles = uploadFiles;
	}

}
