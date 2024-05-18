package com.ucl.infra.product;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ProductImageController {
	@Autowired
	ProductService productService;
	
	// 이미지조회
	@RequestMapping(value = "/productLoadImage")
	public List<ProductDto> productLoadImage(ProductDto dto) throws Exception {
		List<ProductDto> resultList = productService.selectListImages(dto);
		
		List<ProductDto> returnList = new ArrayList<>();
		for(ProductDto dto2 : resultList) {
			ProductDto Images = new ProductDto();
			Images.setXpath(dto2.getXpath());
			Images.setXdefaultNy(dto2.getXdefaultNy());
			Images.setXoriginalName(dto2.getXoriginalName());
			returnList.add(Images);
		}
		return returnList;
	}
}
