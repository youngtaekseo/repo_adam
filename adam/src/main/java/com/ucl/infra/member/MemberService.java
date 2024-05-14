package com.ucl.infra.member;

import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3Client;
import com.amazonaws.services.s3.model.ObjectMetadata;
import com.ucl.infra.fileuploaded.FileUpLoadedDto;
import com.ucl.infra.fileuploaded.FileUpLoadedService;

@Service
public class MemberService {
	@Autowired
	FileUpLoadedService upLoadedService;
	
	@Autowired
	MemberDao dao;
	
	@Autowired
	AmazonS3Client amazonS3Client;
	
	@Value("${cloud.aws.s3.bucket}")
	private String bucket;
	
	public List<MemberDto> selectList(MemberVo vo) {
		return dao.selectList(vo);
	}
	
	public List<MemberDto> selectListCode(MemberVo vo) {
		return dao.selectListCode(vo);
	}
	
	public MemberDto selectOne(MemberDto dto) {
		return dao.selectOne(dto);
	}
	
	public MemberDto selectOneLogin(MemberDto dto) {
		return dao.selectOneLogin(dto);
	}
	
	public int selectOneCount(MemberVo vo) {
		return dao.selectOneCount(vo);
	}	
	
	public int insert(MemberDto dto) {
		return dao.insert(dto);
	}
	
	public int update(MemberDto dto) {
		return dao.update(dto);
	}
	
	public int delete(MemberDto dto) {
		return dao.delete(dto);
	}
	
	public int updatePassword(MemberDto dto) {
		return dao.updatePassword(dto);
	}
	
	public int updateDelNy(MemberDto dto) {
		return dao.updateDelNy(dto);
	}
		
	// 다중선택자료 삭제
	public int deleteList(MemberVo vo) {
		return dao.deleteList(vo);
	};	
	
	// 파일업로드
	public int fileUploads(MemberDto dto, FileUpLoadedDto fDto) throws Exception {
		int index = 0;
		int newSeq;
		String defaultNy;
		
		// 회원번호
		fDto.setPseq(dto.getMbrSeq());
		
		for(MultipartFile multipartFile : dto.getUploadFiles()) {
			if(!multipartFile.isEmpty()) {
				String originalName = multipartFile.getOriginalFilename();
				String ext = originalName.substring(originalName.lastIndexOf(".") + 1); // 파일 이름에서 확장자 추출하기	
				String uuidName = UUID.randomUUID().toString()+"."+ext;
				
				if(index == 0) {
					defaultNy = "0";
				} else {
					defaultNy = "1";
				}
				
				ObjectMetadata metadata = new ObjectMetadata();
				metadata.setContentLength(multipartFile.getSize());
				metadata.setContentType(multipartFile.getContentType());
				amazonS3Client.putObject(bucket, uuidName, multipartFile.getInputStream(), metadata);
				String pathName = amazonS3Client.getUrl(bucket, uuidName).toString();
				//String path = pathName.replaceAll("/"+uuidName, "");
				
				fDto.setCategory("1");
				fDto.setDefaultNy(defaultNy);
				fDto.setSort(index);
				fDto.setPathName(pathName);
				fDto.setPath(pathName);
				fDto.setOriginalName(originalName);
				fDto.setUuidName(uuidName);
				fDto.setExt(ext);
				fDto.setSize(multipartFile.getSize());
				
				// 수정:대표이미값을 1로 변경
				if(defaultNy == "0") {
					upLoadedService.updateFileUpLoaded(fDto);
				}
				
				// 저장
				upLoadedService.insertFileUpLoaded(fDto);
				
				index++;
			}
		}
		

		
		return 1;
	}
}
