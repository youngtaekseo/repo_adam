package com.ucl.infra.product;

import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;

import javax.imageio.ImageIO;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import com.ucl.common.base.BaseService;
import com.ucl.common.fileupload.FileUpLoadDto;

@Service
public class ProductService {
	@Value("${file_upload_type}")
	private String fileUploadType;
	
	@Autowired
	BaseService baseService;
	
	@Autowired
	ProductDao dao;
	
	// 조회조건으로 자료 조회
	public List<ProductDto> selectList(ProductVo vo) {
		return dao.selectList(vo);
	}
	
	// 1건 자료조회
	public ProductDto selectOne(ProductDto dto) {
		return dao.selectOne(dto);
	}
	
	// 등록
	public int insert(ProductDto dto, FileUpLoadDto fDto) throws Exception {
		dao.insert(dto);
		
		fDto.setStorage(fileUploadType);
		fDto.setCategory("1"); // 0:회원, 1:상품
		fDto.setPseq(dto.getPdtSeq());

		if(fileUploadType.equals("0")) { // aws
			// 파일첨부:4개파일을 멀티로 선택했을 경우
			baseService.fileUploadsS3(dto.getUploadFiles(), fDto, fDto);		
		} else if(fileUploadType.equals("1")) { // nas
			// NAS 파일첨부
			baseService.fileUploadsNas(dto.getUploadFiles(), fDto, fDto);				
		}
		
		// 파일첨부:4개파일 각각 선택했을 경우
		/*
		fDto.setDefaultNy("0");
		fDto.setSort(0);
		baseService.fileUploadS3(dto.getUploadFile1(), fDto);
		fDto.setDefaultNy("1");
		fDto.setSort(1);
		baseService.fileUploadS3(dto.getUploadFile2(), fDto);
		fDto.setSort(2);
		baseService.fileUploadS3(dto.getUploadFile3(), fDto);
		fDto.setSort(3);
		baseService.fileUploadS3(dto.getUploadFile4(), fDto);
		*/
		return 0;
	}
	
	// 수정
	public int update(ProductDto dto, FileUpLoadDto fDto) throws Exception {
		dao.update(dto);
		
		fDto.setStorage(fileUploadType);
		fDto.setCategory("1"); // 0:회원, 1:상품
		fDto.setPseq(dto.getPdtSeq());

		if(fileUploadType.equals("0")) { // aws
			// AWS S3 파일첨부
			baseService.fileUploadsS3(dto.getUploadFiles(), fDto, fDto);		
		} else if(fileUploadType.equals("1")) { // nas
			// NAS 파일첨부
			baseService.fileUploadsNas(dto.getUploadFiles(), fDto, fDto);			
		}
		
		// 파일첨부:4개파일 각각 선택했을 경우
		/*
		fDto.setDefaultNy("0");
		fDto.setSort(0);
		baseService.fileUploadS3(dto.getUploadFile1(), fDto);
		fDto.setDefaultNy("1");
		fDto.setSort(1);
		baseService.fileUploadS3(dto.getUploadFile2(), fDto);
		fDto.setSort(2);
		baseService.fileUploadS3(dto.getUploadFile3(), fDto);
		fDto.setSort(3);
		baseService.fileUploadS3(dto.getUploadFile4(), fDto);
		*/
		
		return 0; 
	}
	
	// 삭제
	public int delete(ProductDto dto) {
		return dao.delete(dto);
	}
	
	// 회원정보 삭제여부 수정
	public int updateDelNy(ProductDto dto) {
		return dao.updateDelNy(dto);
	}
	
	// 관리자화면 페이징위한 전체자료 건수
	public int selectOneDataCount(ProductVo vo) {
		return dao.selectOneDataCount(vo);
	}
	
	// 차량리스트 페이징위한 전체 자료건수
	public int selectOneUsrDataCount(ProductVo vo) {
		vo.setXstorage(fileUploadType);
		return dao.selectOneUsrDataCount(vo);
	}
	
	// 제조사 리스트
	public List<ProductDto> selectListBrand() {
		return dao.selectListBrand();
	}

	// 차량정보
	public List<ProductDto> selectListCarInfo(ProductVo vo) {
		vo.setXstorage(fileUploadType);
		return dao.selectListCarInfo(vo);
	}
	
	// 차량명으로 검색
	public List<ProductDto> selectListCarName(ProductVo vo) {
		return dao.selectListCarName(vo);
	}
	
	
	// 차량상세정보
	public ProductDto selectOneCarInfo(ProductVo vo) {
		return dao.selectOneCarInfo(vo);
	}
	
	// 구매목록
	public List<ProductDto> selectListSale(ProductVo vo) {
		vo.setXstorage(fileUploadType);
		return dao.selectListSale(vo);
	}
	
	// 찜목록
	public List<ProductDto> selectListWishList(ProductVo vo){
		vo.setXstorage(fileUploadType);
		return dao.selectListWishList(vo);
	}
	
	// 찜삭제
	/*
	 * public int deleteWishList(ProductVo vo) { return dao.deleteWishList(vo); }
	 */
	
	// 찜내역 건수 및 합계금액
	public ProductDto selectOneWisilistCount(ProductVo vo) {
		return dao.selectOneWisilistCount(vo);
	}
	
	// 다중선택자료 삭제
	public int deleteList(ProductVo vo) {
		return dao.deleteList(vo);
	};	
	
	//이미지갯수
	public ProductDto selectOneImageCount(ProductDto dto) throws Exception {
		dto.setXstorage(fileUploadType);
		return dao.selectOneImageCount(dto);
	};
	
	//상품이미지조회
	public List<ProductDto> selectListImages(ProductDto dto) {
		dto.setXstorage(fileUploadType);
		return dao.selectListImages(dto);
	};
	
	//이미지변환
	public List<ProductDto> getBase64ExternalImage(ProductDto dto) throws Exception {
    	List<ProductDto> returnList = new ArrayList<>();
    	
    	// 이미지 확장자
    	String ext = null;
    	
    	// 이미지파일조회
    	List<ProductDto> listDto = selectListImages(dto);
    	
    	for(ProductDto forDto : listDto) {
        	// 이미지 확장자
        	ext = forDto.getXext().toLowerCase();
        	
        	// 이미지 파일을 파일 시스템에서 로드
            File imgPath = new File(forDto.getXpathUpload());
            BufferedImage bufferedImage = ImageIO.read(imgPath);

            // 이미지를 byte 배열로 변환
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();         
            ImageIO.write(bufferedImage, ext, outputStream);
            byte[] imageBytes = outputStream.toByteArray();

            ProductDto dto2 = new ProductDto();
            
            // byte 배열을 Base64 문자열로 인코딩하여 반환
            dto2.setXdefaultNy(forDto.getXdefaultNy());
            dto2.setXpathUpload(Base64.getEncoder().encodeToString(imageBytes));
            dto2.setXfileName(forDto.getXfileName());
            dto2.setXext(ext);
            
            returnList.add(dto2);		
    	}
        
        return returnList;
    }
}
