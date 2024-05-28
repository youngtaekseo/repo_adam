package com.ucl.infra.member;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class MemberImageController {
	@Value("${file_upload_type}")
	private String fileUploadType;
	
	@Autowired
	MemberService service;
	
	// 이미지조회
	@RequestMapping(value = "/memberLoadImage")
	public List<MemberDto> productLoadImage(MemberDto dto, MemberDto dto2) throws Exception {
		// 파일갯수확인
		dto2 = service.selectOneImageCount(dto);
		
		if(dto2 != null) {
			if(fileUploadType.toLowerCase().equals("1")) { //nas
				return service.getBase64ExternalImage(dto);
			} else {
				return service.selectListImages(dto);
			}
		} else {
			return null;
		}
	}
}
