package com.ucl.common.base;

import java.io.File;
import java.net.InetAddress;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.ucl.common.constants.Commvar;
import com.ucl.common.fileupload.FileUpLoadDto;
import com.ucl.common.fileupload.FileUpLoadService;
import com.ucl.common.util.UtilDateTime;

import jakarta.annotation.PostConstruct;

@Service
public class BaseService {
	@Autowired
	AmazonS3Client amazonS3Client;
	
	@Autowired
	FileUpLoadService fileUpLoadService;
	
	@Value("${cloud.aws.s3.bucket}")
	private String bucket;
	
	// 단일파일 업로드(AWS S3)
	//========================================================================= AWS S3
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
			if(dto.getCategory() == "0") { // 회원이미지
				if(dto.getDefaultNy() == "0") {
					fileUpLoadService.updateDefaultNy(dto);				
				}
			} else if(dto.getCategory() == "1") { // 상품이미지
				fileUpLoadService.updateUuidName(dto);
			}
						
			// 저장
			fileUpLoadService.insertFileUpLoad(dto);			
		}
	}
	
	// 여러파일 업로드(AWS S3)
	//========================================================================= AWS S3
	public void fileUploadsS3(MultipartFile[] multipartFiles, FileUpLoadDto dto, FileUpLoadDto fDto) throws Exception {
		String originalName;
		String ext;
		String uuidName;
		String pathName;
		String defaultName = dto.getDefaultNy();
		int index = 1;
		
		for(int i = 0; i < multipartFiles.length; i++) {
			if(!multipartFiles[i].isEmpty()) {
				// 파일명 예)img01.jpg
				originalName = multipartFiles[i].getOriginalFilename();
				
				if(dto.getCategory() == "0") {
					// 회원이미지
					//=========================================================
					// 대표이미지 설정
					dto.setDefaultNy("0");
					// 순서
					dto.setSort(0);
					
					// 수정:이전 대표값을 1로 변경
					fileUpLoadService.updateDefaultNy(dto);
				} else if(dto.getCategory() == "1") {
					// 상품이미지
					//=========================================================
					// 대표이미지 설정
					if(originalName.equals(defaultName)) {
						// 대표이미지 설정
						dto.setDefaultNy("0");
						// 순서
						dto.setSort(0);
						
						index--;
					} else {
						// 대표이미지 아님설정
						dto.setDefaultNy("1");
						// 순서
						dto.setSort(index);
					}
					
					// 삭제는 처음 1번만 실행
					if(i == 0) {
						// 키조회
						List<FileUpLoadDto> list = fileUpLoadService.selectListUuidName(fDto);
						
						// 상품이미지 삭제
						for(FileUpLoadDto dto2 : list) {
							// AWS S3 삭제
							amazonS3Client.deleteObject(bucket, dto2.getUuidName());
							// DB 삭제
							fDto.setUuidName(dto2.getUuidName());
							fileUpLoadService.deleteFileUpLoad(fDto);
						}
					}
				}	
				
				ext = originalName.substring(originalName.lastIndexOf(".") + 1); // 파일 이름에서 확장자 추출하기	
				uuidName = UUID.randomUUID().toString()+"."+ext;
				
				ObjectMetadata metadata = new ObjectMetadata();
				metadata.setContentLength(multipartFiles[i].getSize());
				metadata.setContentType(multipartFiles[i].getContentType());
				amazonS3Client.putObject(bucket, uuidName, multipartFiles[i].getInputStream(), metadata);
				pathName = amazonS3Client.getUrl(bucket, uuidName).toString();	
				
				dto.setPathName(pathName);
				dto.setPath(pathName);
				dto.setOriginalName(originalName);
				dto.setUuidName(uuidName);
				dto.setExt(ext);
				dto.setSize(multipartFiles[i].getSize());
				
				// 저장
				fileUpLoadService.insertFileUpLoad(dto);
				
				index++;
			}
		}
	}
	
	// AWS S3 파일삭제
	//========================================================================= AWS S3
	public void fileDeleteS3(FileUpLoadDto dto, FileUpLoadDto fDto) throws Exception {
		// 키조회
		List<FileUpLoadDto> list = fileUpLoadService.selectListUuidName(fDto);
		if(list != null && list.size() != 0) {
			// 상품이미지 삭제
			for(FileUpLoadDto dto2 : list) {
				// AWS S3 삭제
				amazonS3Client.deleteObject(bucket, dto2.getUuidName());
				// DB 삭제
				fDto.setUuidName(dto2.getUuidName());
				fileUpLoadService.deleteFileUpLoad(fDto);
			}			
		}
	}
	
	// 단일파일 업로드(NAS)
	//========================================================================= NAS
	public void fileUploadNas(MultipartFile multipartFile, FileUpLoadDto dto) throws Exception {
		if(!multipartFile.isEmpty() && multipartFile != null) {
			String className    = dto.getClass().getSimpleName().toString().toLowerCase();		
			String fileName     = multipartFile.getOriginalFilename();
			String ext          = fileName.substring(fileName.lastIndexOf(".") + 1);
			String uuid         = UUID.randomUUID().toString();
			String uuidFileName = uuid + "." + ext;
			String pathModule   = className;
			String nowString    = UtilDateTime.nowString();
			String pathDate     = nowString.substring(0,4) + "/" + nowString.substring(5,7) + "/" + nowString.substring(8,10); 
			String pathUpload;
			String pathLoad;
			
			if(BaseService.getOs().equals("win")) {
				pathUpload = Commvar.UPLOADED_PATH_PREFIX_LOCAL;// + "/" + pathModule + "/" + type + "/" + pathDate + "/";
				pathLoad   = Commvar.UPLOADED_PATH_PREFIX_FOR_VIEW_LOCAL;// + "/" + pathModule + "/" + type + "/" + pathDate + "/";					
			} else {
				pathUpload = Commvar.UPLOADED_PATH_PREFIX_LOCAL_MAC;// + "/" + pathModule + "/" + type + "/" + pathDate + "/";
				pathLoad   = Commvar.UPLOADED_PATH_PREFIX_FOR_VIEW_LOCAL_MAC;// + "/" + pathModule + "/" + type + "/" + pathDate + "/";										
			}
			
			File uploadPath = new File(pathUpload);
			
			if (!uploadPath.exists()) {
				uploadPath.mkdirs();
			} else {
				// by pass
			}
			  
			multipartFile.transferTo(new File(pathUpload + uuidFileName));
					
			dto.setPath(pathLoad);      // 불러올위치
			dto.setPathName(pathUpload); // 원격저장위치
			dto.setOriginalName(fileName);
			dto.setUuidName(uuidFileName);
			dto.setExt(ext);
			dto.setSize(multipartFile.getSize());
			
//			dto.setTableName(tableName);
//			dto.setType(type);
//			dto.setDefaultNy();
//			dto.setSort(maxNumber + i);
//			dto.setPseq(pSeq);
			
			// 수정:대표이미값을 1로 변경
			if(dto.getCategory() == "0") { // 회원이미지
				if(dto.getDefaultNy() == "0") {
					fileUpLoadService.updateDefaultNy(dto);				
				}
			} else if(dto.getCategory() == "1") { // 상품이미지
				fileUpLoadService.updateUuidName(dto);
			}
			
			fileUpLoadService.insertFileUpLoad(dto);
		}
	}
	
	// 여러파일 업로드(NAS)
	//========================================================================= NAS
	public void fileUploadsNas(MultipartFile[] multipartFiles, FileUpLoadDto dto, FileUpLoadDto fDto) throws Exception {
		String className;
		String pathModule;
		String nowString;
		String pathDate;
		
		String originalName;
		String ext;
		String uuidName;
		String loadPath, loadPathName;
		String writePath, writePathName;
		String defaultName = dto.getDefaultNy();
		int index = 1;
		
		for(int i=0; i<multipartFiles.length; i++) {
			if(!multipartFiles[i].isEmpty()) {
				// 파일명 예)img01.jpg
				originalName = multipartFiles[i].getOriginalFilename();
				
				if(dto.getCategory() == "0") {
					// 회원이미지
					//=========================================================
					// 대표이미지 설정
					dto.setDefaultNy("0");
					// 순서
					dto.setSort(0);
					
					// 수정:이전 대표값을 1로 변경
					fileUpLoadService.updateDefaultNy(dto);
				} else if(dto.getCategory() == "1") {
					// 상품이미지
					//=========================================================
					// 대표이미지 설정
					if(originalName.equals(defaultName)) {
						// 대표이미지 설정
						dto.setDefaultNy("0");
						// 순서
						dto.setSort(0);
						
						index--;
					} else {
						// 대표이미지 아님설정
						dto.setDefaultNy("1");
						// 순서
						dto.setSort(index);
					}
					
					// 삭제는 처음 1번만 실행
					if(i == 0) {
						// 키조회
						List<FileUpLoadDto> list = fileUpLoadService.selectListUuidName(fDto);
						
						// 상품이미지 삭제
						for(FileUpLoadDto dto2 : list) {
							// NAS 삭제
							// 파일 경로를 Path 객체로 변환
							Path deletePpath = Paths.get(dto2.getPath());
							// 파일 삭제
							Files.delete(deletePpath);
							
							// DB 삭제
							fDto.setUuidName(dto2.getUuidName());
							fileUpLoadService.deleteFileUpLoad(fDto);
						}
					}
				}
				
				className    = dto.getClass().getSimpleName().toString().toLowerCase();
				ext          = originalName.substring(originalName.lastIndexOf(".") + 1);
				uuidName     = UUID.randomUUID().toString() + "." + ext;
				pathModule   = className;
				nowString    = UtilDateTime.nowString();
				pathDate     = nowString.substring(0,4) + "/" + nowString.substring(5,7) + "/" + nowString.substring(8,10); 
				
				if(BaseService.getOs().equals("win")) {
					writePath = Commvar.UPLOADED_PATH_PREFIX_LOCAL;// + "/" + pathModule + "/" + type + "/" + pathDate + "/";
					loadPath  = Commvar.UPLOADED_PATH_PREFIX_FOR_VIEW_LOCAL;// + "/" + pathModule + "/" + type + "/" + pathDate + "/";					
				} else {
					writePath = Commvar.UPLOADED_PATH_PREFIX_LOCAL_MAC;// + "/" + pathModule + "/" + type + "/" + pathDate + "/";
					loadPath  = Commvar.UPLOADED_PATH_PREFIX_FOR_VIEW_LOCAL_MAC;// + "/" + pathModule + "/" + type + "/" + pathDate + "/";										
				}
				
				File uploadPath = new File(writePath);
				
				if (!uploadPath.exists()) {
					uploadPath.mkdirs();
				} else {
					// by pass
				}
				  
				multipartFiles[i].transferTo(new File(writePath + uuidName));
				writePathName = writePath + uuidName;
				loadPathName  = loadPath  + uuidName;
				
				dto.setPath(loadPathName);
				dto.setPathName(writePathName);
				dto.setOriginalName(originalName);
				dto.setUuidName(uuidName);
				dto.setExt(ext);
				dto.setSize(multipartFiles[i].getSize());
				
//				dto.setTableName(tableName);
//				dto.setType(type);
	//			dto.setDefaultNy();
//				dto.setSort(maxNumber + i);
//				dto.setPseq(pSeq);
				
				fileUpLoadService.insertFileUpLoad(dto);
			}
		}
	}	
	
	// os, ip 정보
	//=========================================================================
	@PostConstruct
	public void setOsVersion() throws Exception {
        // 운영 체제 이름을 가져옵니다.
        String osVersion = System.getProperty("os.name").toLowerCase();
        String osName;
        System.out.println("================================================================");
        // 운영 체제를 확인하고 메시지를 출력합니다.
        if (osVersion.contains("win")) {
        	osName = "win";
            System.out.println("현재 운영 체제는 Windows 입니다.");
        } else if (osVersion.contains("mac")) {
        	osName = "mac";
        	System.out.println("현재 운영 체제는 MacOS 입니다.");
        } else if (osVersion.contains("nix") || osVersion.contains("nux") || osVersion.contains("aix")) {
        	osName = "ulx";
        	System.out.println("현재 운영 체제는 Unix 또는 Linux 입니다.");
        } else if (osVersion.contains("sunos")) {
        	osName = "sun";
        	System.out.println("현재 운영 체제는 Solaris 입니다.");
        } else {
        	osName = "etc";
            System.out.println("알 수 없는 운영 체제입니다.");
        }

        // 시스템 속성 예제: Java 버전
        String javaVersion = System.getProperty("java.version");
        System.out.println("현재 자바 버전: " + javaVersion);
        
        // osm 정보 설정
        BaseDto.setInfoOs(osName);
        
        // ip 정보 설정
    	InetAddress ip = InetAddress.getLocalHost();
    	BaseDto.setInfoIp(ip.getHostAddress());
        System.out.println("HostAddress : " + ip.getHostAddress());
        System.out.println("================================================================");
    }
	
	// os 정보리턴
	public static String getOs() {
		return BaseDto.getInfoOs();
	}
	
	// ip 정보리턴
	public static String getIp() {
		return BaseDto.getInfoIp();
	}
}
