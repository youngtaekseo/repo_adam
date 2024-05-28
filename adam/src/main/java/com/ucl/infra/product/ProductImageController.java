package com.ucl.infra.product;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductImageController {
	@Value("${file_upload_type}")
	private String fileUploadType;
	
	@Autowired
	ProductService service;
	
	// 이미지조회
	@RequestMapping(value = "/productLoadImage")
	public List<ProductDto> productLoadImage(ProductDto dto, ProductDto dto2) throws Exception {
		// 파일갯수확인
		dto2 = service.selectOneImageCount(dto);
		
		if(dto2 != null) {
			List<ProductDto> returnList = new ArrayList<>();
			if(fileUploadType.toLowerCase().equals("nas")) {
				returnList = service.getBase64ExternalImage(dto);				
			} else {
				returnList = service.selectListImages(dto);
			}
			
			return returnList;
		} else {
			return null;
		}
	}
}
