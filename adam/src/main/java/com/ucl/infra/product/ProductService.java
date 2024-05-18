package com.ucl.infra.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ucl.common.base.BaseService;
import com.ucl.common.fileupload.FileUpLoadDto;

@Service
public class ProductService {
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
		
		fDto.setCategory("1");
		fDto.setPseq(dto.getPdtSeq());

		// 파일첨부:4개파일을 멀티로 선택했을 경우
		baseService.fileUploadsS3(dto.getUploadFiles(), fDto, fDto);
		
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
		
		fDto.setCategory("1");
		fDto.setPseq(dto.getPdtSeq());

		// 파일첨부:4개파일을 멀티로 선택했을 경우
		baseService.fileUploadsS3(dto.getUploadFiles(), fDto, fDto);
		
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
		return dao.selectOneUsrDataCount(vo);
	}
	
	// 제조사 리스트
	public List<ProductDto> selectListBrand() {
		return dao.selectListBrand();
	}

	// 차량정보
	public List<ProductDto> selectListCarInfo(ProductVo vo) {
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
		return dao.selectListSale(vo);
	}
	
	// 찜목록
	public List<ProductDto> selectListWishList(ProductVo vo){
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
	
	//상품이미지조회
	public List<ProductDto> selectListImages(ProductDto dto) {
		return dao.selectListImages(dto);
	};
}
