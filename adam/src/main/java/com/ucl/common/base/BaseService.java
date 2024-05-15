package com.ucl.common.base;

import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.ucl.common.fileupload.FileUpLoadDto;
import com.ucl.common.fileupload.FileUpLoadService;

@Service
public class BaseService {
	@Autowired
	AmazonS3Client amazonS3Client;
	
	@Autowired
	FileUpLoadService fileUpLoadService;
	
	@Value("${cloud.aws.s3.bucket}")
	private String bucket;
	
	// 단일파일 업로드(AWS S3)
	public void fileUploadS3(MultipartFile multipartFile, FileUpLoadDto dto) throws Exception {
		if(!multipartFile.isEmpty() && multipartFile != null) {
			String originalName = multipartFile.getOriginalFilename();
			String ext = originalName.substring(originalName.lastIndexOf(".") + 1); // 파일 이름에서 확장자 추출하기	
			String uuidName = UUID.randomUUID().toString()+"."+ext;		
			
			ObjectMetadata metadata = new ObjectMetadata();
			metadata.setContentLength(multipartFile.getSize());
			metadata.setContentType(multipartFile.getContentType());
			amazonS3Client.putObject(bucket, uuidName, multipartFile.getInputStream(), metadata);
			String pathName = amazonS3Client.getUrl(bucket, uuidName).toString();
			
			dto.setPathName(pathName);
			dto.setPath(pathName);
			dto.setOriginalName(originalName);
			dto.setUuidName(uuidName);
			dto.setExt(ext);
			dto.setSize(multipartFile.getSize());
			
			// 수정:대표이미값을 1로 변경
			if(dto.getDefaultNy() == "0") {
				fileUpLoadService.updateFileUpLoad(dto);				
			}
			
			// 저장
			fileUpLoadService.insertFileUpLoad(dto);			
		}
	}
	
	// 여러파일 업로드(AWS S3)
	public void fileUploadsS3(MultipartFile[] multipartFiles, FileUpLoadDto dto) throws Exception {
		String originalName;
		String ext;
		String uuidName;
		String defaultNy;
		String pathName;
		
		for(int i = 0; i < multipartFiles.length; i++) {
			if(!multipartFiles[i].isEmpty()) {
				originalName = multipartFiles[i].getOriginalFilename();
				ext = originalName.substring(originalName.lastIndexOf(".") + 1); // 파일 이름에서 확장자 추출하기	
				uuidName = UUID.randomUUID().toString()+"."+ext;
				
				if(i == 0) {
					defaultNy = "0";
				} else {
					defaultNy = "1";
				}	
				
				ObjectMetadata metadata = new ObjectMetadata();
				metadata.setContentLength(multipartFiles[i].getSize());
				metadata.setContentType(multipartFiles[i].getContentType());
				amazonS3Client.putObject(bucket, uuidName, multipartFiles[i].getInputStream(), metadata);
				pathName = amazonS3Client.getUrl(bucket, uuidName).toString();	
				
				dto.setDefaultNy(defaultNy);
				dto.setSort(i);
				dto.setPathName(pathName);
				dto.setPath(pathName);
				dto.setOriginalName(originalName);
				dto.setUuidName(uuidName);
				dto.setExt(ext);
				dto.setSize(multipartFiles[i].getSize());
				
				// 수정:대표이미값을 1로 변경
				if(defaultNy == "0") {
					fileUpLoadService.updateFileUpLoad(dto);
				}
				
				// 저장
				fileUpLoadService.insertFileUpLoad(dto);	
			}
		}
	}	
	
	// 단일파일 업로드(NAS)
	public void fileUploadNas(MultipartFile multipartFile, FileUpLoadDto dto) throws Exception {
		
	}
	
	// 여러파일 업로드(NAS)
	public void fileUploadsNas(MultipartFile[] multipartFiles, FileUpLoadDto dto) throws Exception {
		
	}	
}
