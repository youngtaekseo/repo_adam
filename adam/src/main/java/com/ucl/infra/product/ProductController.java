package com.ucl.infra.product;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.ucl.common.base.BaseService;
import com.ucl.common.constants.Commvar;
import com.ucl.common.fileupload.FileUpLoadDto;
import com.ucl.common.util.UtilDateTime;
import com.ucl.common.util.UtilFunction;
import com.ucl.infra.code.CodeService;
import com.ucl.infra.review.ReviewService;
import com.ucl.infra.review.ReviewVo;
import com.ucl.infra.wishlist.WishlistService;
import com.ucl.infra.wishlist.WishlistVo;

import jakarta.servlet.http.HttpSession;

@Controller
public class ProductController {
	@Value("${file_upload_type}")
	private String fileUploadType;
	
	@Autowired
	BaseService baseService;
	
	@Autowired
	ProductService service;
	
	@Autowired
	ReviewService reviewService;
	
	@Autowired
	WishlistService wishlistService;
	
	@Autowired
	CodeService codeService;
	
	// 파일첨부
	@ResponseBody
	@RequestMapping(value = "/productFileUpload")
	public Map<String, Object> productFileUpload(ProductDto dto, FileUpLoadDto fdto) throws Exception {
		fdto.setCategory("1");
		fdto.setPseq(dto.getPdtSeq());
		
		if(dto.getXpdtImg1() != null) {
			fdto.setSort(0);
		} else if(dto.getXpdtImg2() != null) {
			fdto.setSort(1);
		} else if(dto.getXpdtImg3() != null) {
			fdto.setSort(2);
		} else if(dto.getXpdtImg4() != null) {
			fdto.setSort(3);
		}  
		
		baseService.fileUploadS3(dto.getUploadFile(), fdto);
		
		Map<String, Object> returnMap = new HashMap<>();
		returnMap.put("rt", "success");
		return returnMap;
	}	
	
	// 검색조건 초기화
	@RequestMapping(value = "/productSdmListInit")
	public String memberSdmListInit(@ModelAttribute("vo") ProductVo vo, Model model) throws Exception {
		vo.setShSeq(null);
		vo.setShCarType(null); 	// 1:중형차, 2:대형차
		vo.setShCarBrand(null); 	// 1:현대, 2:제네시스, 3:기아
		vo.setShCarColor(null); 	// 1:검정색, 2:흰색
		vo.setShRecommend(null); 	// 1:추천, 2:비추천
		vo.setShAccident(null); 	// 1:무사고, 2:유사고

		vo.setShOptionRunKm(null);	// 주행기록
		vo.setShOptionRunKmFrom(null);		// 주행기록 시작값
		vo.setShOptionRunKmTo(null);		// 주행기록 종료값
		vo.setShOptionYear(null);	// 연식
		vo.setShOptionYearFrom(null);// 연식(년) 시작값
		vo.setShOptionYearTo(null);// 연식(년) 종료값
		vo.setShOptionMonthFrom(null); 		// 연식(월) 시작값
		vo.setShOptionMonthTo(null);   		// 연식(월) 종료값
		vo.setShPrice(null);   				// 판매가액
		vo.setShPriceFrom(null);   			// 판매가액 시작값
		vo.setShPriceTo(null);   			// 판매가액 종료값
		vo.setShMbrSeq(null);               // 회원순번
		vo.setShPdtName(null);				// 차량명

		vo.setShCheckboxTypeArray(null); // 차종
		vo.setShCheckboxBrandArray(null); // 브랜드
		vo.setShCheckboxColorArray(null); // 색상	
		
		return "redirect:/productSdmList";
	}	

	// 조회화면
	@RequestMapping(value = "/productSdmList")
	public String productSdmList(@ModelAttribute("vo") ProductVo vo, Model model) throws Exception {
		UtilFunction.setSearch(vo);

		int rowCount = service.selectOneDataCount(vo);

		if (rowCount > 0) {
			vo.setPagingVo(rowCount);

			model.addAttribute("list", service.selectList(vo));

			/* setUrl(vo); */
		}
		;

		return Commvar.PATH_PRODUCT + "productSdmList";
	}

	// 조회화면(paging 처리용)
	@RequestMapping(value = "/productSdmPaging")
	public String productSdmListPaging(@ModelAttribute("vo") ProductVo vo, Model model) throws Exception {
		int rowCount = service.selectOneDataCount(vo);

		if (rowCount > 0) {
			vo.setPagingVo(rowCount);

			model.addAttribute("list", service.selectList(vo));
		};

		return Commvar.PATH_PRODUCT + "productSdmListAjax";
	}

	// 등록화면
	@RequestMapping(value = "/productSdmCreate")
	public String productSdmCreate(ProductDto dto, Model model) throws Exception {
		model.addAttribute("item", service.selectOne(dto));
		return Commvar.PATH_PRODUCT + "productSdmCreate";
	}

	// 수정화면
	@RequestMapping(value = "/productSdmForm")
	public String productSdmForm(ProductDto dto, Model model) throws Exception {
		model.addAttribute("item", service.selectOne(dto));
		model.addAttribute("uploadType", fileUploadType.toLowerCase());	
		return Commvar.PATH_PRODUCT + "productSdmForm";
	}

	// 입력
	@RequestMapping(value = "/productSdmInsert")
	public String productSdmInsert(ProductDto dto, FileUpLoadDto fDto) throws Exception {
		service.insert(dto, fDto);
		return "redirect:/productSdmList";
	}

	// 수정
	@RequestMapping(value = "/productSdmUpdate")
	public String productSdmUpdate(ProductDto dto, FileUpLoadDto fDto) throws Exception {
		service.update(dto, fDto);
		return "redirect:/productSdmList";
	}

	// 삭제
	@ResponseBody
	@RequestMapping(value = "/productSdmDelete")
	public Map<String, Object> productSdmDelete(ProductDto dto, FileUpLoadDto fDto) throws Exception {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		if(service.delete(dto) > 0) {
			fDto.setCategory("1");
			fDto.setPseq(dto.getPdtSeq());
			baseService.fileDeleteS3(fDto, fDto);
			returnMap.put("rt", "success"); 
		} else {
			returnMap.put("rt", "fail");
		}
		
		return returnMap;
	}

	// 삭제여부수정
	@RequestMapping(value = "/productSdmDelNy")
	public String productSdmDelNy(ProductDto dto) throws Exception {
		service.updateDelNy(dto);
		return "redirect:/productSdmList";
	}
	
	// 다중 선택자료 삭제
	@ResponseBody
	@RequestMapping(value = "/productSdmListDelete")
	public Map<String, Object> codeSdmListDelete(ProductVo vo) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		if(service.deleteList(vo) > 0) { 
			returnMap.put("rt", "success"); 
		} else {
			returnMap.put("rt", "fail");
		}
		  
		return returnMap;
	}	

	// 조회조건 및 페이징정보 포함된 url 생성
	public void setUrl(ProductVo vo) throws Exception {
		UriComponents uri = UriComponentsBuilder.newInstance().queryParam("shDelNy", vo.getShDelNy())
				.queryParam("shOptionDate", vo.getShOptionDate()).queryParam("shDateStart", vo.getShDateStart())
				.queryParam("shDateEnd", vo.getShDateEnd()).queryParam("shCarType", vo.getShCarType())
				.queryParam("shCarBrand", vo.getShCarBrand()).queryParam("shCarColor", vo.getShCarColor())
				.queryParam("shRecommend", vo.getShRecommend()).queryParam("shOptionRunKm", vo.getShOptionRunKm())
				.queryParam("shOptionRunKmFrom", vo.getShOptionRunKmFrom())
				.queryParam("shOptionRunKmTo", vo.getShOptionRunKmTo()).queryParam("shOptionYear", vo.getShOptionYear())
				.queryParam("shOptionYearFrom", vo.getShOptionYearFrom())
				.queryParam("shOptionYearTo", vo.getShOptionYearTo())
				.queryParam("shOptionMonthFrom", vo.getShOptionMonthFrom())
				.queryParam("shOptionMonthTo", vo.getShOptionMonthTo()).queryParam("shAccident", vo.getShAccident())
				.queryParam("shOption", vo.getShOption()).queryParam("shValue", vo.getShValue()).build();
		vo.setUri("&" + uri.toUriString().substring(1, uri.toUriString().length()));
	}

// ============================================================================
// 사용자
// ============================================================================

	// 조회화면
	@RequestMapping(value = "/productUsrList")
	public String productUsrList(@ModelAttribute("vo") ProductVo vo, Model model, HttpSession httpSession) throws Exception {

		// 현재년도
		if(vo.getShFromYear() == null) {
			vo.setShFromYear(UtilDateTime.nowYearInteger());
		}
		
		// 과거년도
		if(vo.getShToYear() == null) {	
			vo.setShToYear(vo.getShFromYear() - vo.getShRange());
		}
		
		// 로그인 회원순번 설정
		vo.setShMbrSeq((String) httpSession.getAttribute("sessMbrSeq"));
		
		// 전체자료건수
		int rowCount = service.selectOneUsrDataCount(vo);

		if (rowCount > 0) {
			vo.setPgRowCount(vo.getPgRowCountUsr());
			vo.setPgPageCount(vo.getPgPageCountUsr());
			
			vo.setPagingVo(rowCount);

			model.addAttribute("list", service.selectListCarInfo(vo));
			//model.addAttribute("listBrand", service.selectListBrand());
		}

		return Commvar.PATH_PRODUCT + "productUsrList";
	}
	
	// 차량재조회
	@RequestMapping(value = "/selectListReload")
	public String selectListReload(@ModelAttribute("vo") ProductVo vo, Model model, HttpSession httpSession) throws Exception {

		// 로그인 회원순번 설정
		vo.setShMbrSeq((String) httpSession.getAttribute("sessMbrSeq"));
		
		// 전체자료건수
		int rowCount = service.selectOneUsrDataCount(vo);

		if (rowCount > 0) {
			vo.setPgRowCount(vo.getPgRowCountUsr());
			vo.setPgPageCount(vo.getPgPageCountUsr());
			
			vo.setPagingVo(rowCount);

			model.addAttribute("list", service.selectListCarInfo(vo));
		}

		return Commvar.PATH_PRODUCT + "productUsrList"; // :: listCar";
	}

	// 사용자 상세화면
	@RequestMapping(value = "/productUsrDetail")
	public String productUsrDetail(@ModelAttribute("vo") ProductVo vo, ReviewVo rvo, Model model, HttpSession httpSession) throws Exception {
		// 로그인 회원순번 설정
		vo.setShMbrSeq((String) httpSession.getAttribute("sessMbrSeq"));
		
		// 상세조회
		model.addAttribute("item", service.selectOneCarInfo(vo));
		
		// 댓글조회
		rvo.setShSeq(vo.getShSeq());
		model.addAttribute("list", reviewService.selectList(rvo));

		return Commvar.PATH_PRODUCT + "productUsrDetail";
	}

	// 사용자 구매내역
	@RequestMapping(value = "/productUsrSale")
	public String productUsrSale(ProductVo vo, Model model, HttpSession httpSession) throws Exception {

		// 로그인 회원순번 설정
		vo.setShSeq((String) httpSession.getAttribute("sessMbrSeq"));

		// 구매내역조회
		model.addAttribute("list", service.selectListSale(vo));

		return Commvar.PATH_PRODUCT + "productUsrSale";
	}

	// 찜목록
	@RequestMapping(value = "/productUsrWishlist")
	public String productUsrWishlist(@ModelAttribute("vo") ProductVo vo, HttpSession httpSession, Model model)
			throws Exception {
		// 로그인 회원순번 설정
		vo.setShSeq((String) httpSession.getAttribute("sessMbrSeq"));

		// 자료조회
		model.addAttribute("list", service.selectListWishList(vo));

		return Commvar.PATH_PRODUCT + "productUsrWishlist";
	}

	// 찜목록 삭제
	// ResponseBody 어노테이션은 쓰지 않습니다
	// ResponseBody 사용시 return 값의 String이 고대로 출력되는 일 발생됨
	@RequestMapping(value = "/productUsrWishlistDelete")
	public String productUsrWishlistDelete(ProductVo vo, WishlistVo wvo, Model model, HttpSession httpSession) throws Exception {
		
		// 찜 삭제
		// 로그인 회원순번 설정
		wvo.setShMbrSeq((String) httpSession.getAttribute("sessMbrSeq"));
		// 찜 순번
		wvo.setShSeq(vo.getShSeq());
		wishlistService.deleteWishlist(wvo);
		
		// 로그인 회원순번 설정
		vo.setShSeq((String) httpSession.getAttribute("sessMbrSeq"));
		
		// 자료조회
		model.addAttribute("list", service.selectListWishList(vo));
		
		// :: #list -> productUsrWishlist 소스에서 id값으로 이동한다
		return Commvar.PATH_PRODUCT + "productUsrWishlist :: #list"; 
	}
}
