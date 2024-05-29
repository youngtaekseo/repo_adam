package com.ucl.infra.code;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellStyle;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import com.ucl.common.constants.Commvar;
import com.ucl.common.util.UtilDateTime;
import com.ucl.common.util.UtilFunction;
import com.ucl.infra.codegroup.CodeGroupService;

import jakarta.servlet.http.HttpServletResponse;

@Controller
public class CodeController {

	@Autowired
	CodeService service;
	
	@Autowired
	CodeGroupService codeGroupService;
	
	// 검색조건 초기화
	@RequestMapping(value = "/codeSdmListInit")
	public String codeSdmListInit(@ModelAttribute("vo") CodeVo vo, Model model) throws Exception {
		vo.setShDateEnd(null);
		vo.setShDateStart(null);
		vo.setShDelNy(0);
		vo.setShOption(null);
		vo.setShOptionDate(0);
		vo.setShValue(null);
		vo.setSeq(null);
		vo.setShOptionGroup(null);
		
		return "redirect:/codeSdmList";
	}
	
	//	전체리스트 @RequestParam(value = "page", defaultValue = "1") final int page) throws Exception {
	@RequestMapping(value = "/codeSdmList")
	public String codeListSdm(@ModelAttribute("vo") CodeVo vo, Model model) throws Exception {
			 
		UtilFunction.setSearch(vo);
		
		int rowCount = service.getCount(vo);
		
		if(rowCount > 0) {			
			vo.setPagingVo(rowCount);
			
			model.addAttribute("list", service.selectList(vo));
		};

		model.addAttribute("listCodeGroupName", codeGroupService.selectListCodeGroup());
		
		return Commvar.PATH_CODE + "codeSdmList";
	}
	
	@RequestMapping(value = "/codeSdmPaging")
	public String codeSdmPaging(@ModelAttribute("vo") CodeVo vo, Model model) throws Exception {
		int rowCount = service.getCount(vo);
		
		if(rowCount > 0) {			
			vo.setPagingVo(rowCount);
			
			model.addAttribute("list", service.selectList(vo));
		};
		
		return Commvar.PATH_CODE + "codeSdmListAjax";
	}
	
//	등록화면
	@RequestMapping(value = "/codeSdmCreate")
	public String codeSdmCreate(CodeDto dto, Model model) throws Exception {
		model.addAttribute("item", service.selectOne(dto));	
		return Commvar.PATH_CODE + "codeSdmCreate";
	}
	
//	수정화면
	@RequestMapping(value = "/codeSdmForm")
	public String codeSdmForm(CodeDto dto, Model model) throws Exception {
		model.addAttribute("item", service.selectOne(dto));	
		return Commvar.PATH_CODE + "codeSdmForm";
	}
	
//	코드등록
	@RequestMapping(value = "/codeSdmInsert")
	public String codeSdmInsert(CodeDto dto) throws Exception {
		service.insert(dto);
		return "redirect:/codeSdmList";
	}
	
//	코드수정
	@RequestMapping(value = "/codeSdmUpdate")
	public String codeSdmUpdate(CodeDto dto) throws Exception {
		service.update(dto);
		return "redirect:/codeSdmList";	
	}	
	
//	코드삭제
	@ResponseBody
	@RequestMapping(value = "/codeSdmDelete")
	public Map<String, Object> codeSdmDelete(CodeDto dto) throws Exception {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		if(service.delete(dto) == 1) {
			returnMap.put("rt", "success");
		} else {
			returnMap.put("rt", "fail");
		};
		
		return returnMap;
	}
	
//	코드 삭제여부 변경
	@RequestMapping(value = "/codeSdmUdtDelNy")
	public String codeSdmUdtDelNy(CodeDto dto) throws Exception {
		service.updateDelNy(dto);		
		return "redirect:/codeSdmList";	
	}
	
	// 다중 선택자료 삭제
	@ResponseBody
	@RequestMapping(value = "/codeSdmListDelete")
	public Map<String, Object> codeSdmListDelete(CodeVo vo) {
		Map<String, Object> returnMap = new HashMap<String, Object>();
		
		if(service.deleteList(vo) > 0) { 
			returnMap.put("rt", "success"); 
		} else {
			returnMap.put("rt", "fail");
		}
		  
		return returnMap;
	}
	
	// 조회조건 및 페이징정보 포함된 url 생성
	public void setUrl(CodeVo vo) throws Exception {
		UriComponents uri = UriComponentsBuilder.newInstance()
				.queryParam("shDelNy"      , vo.getShDelNy())
				.queryParam("shOptionDate" , vo.getShOptionDate())
				.queryParam("shDateStart"  , vo.getShDateStart())
				.queryParam("shDateEnd"    , vo.getShDateEnd())
				.queryParam("shOptionGroup", vo.getShOptionGroup())
				.queryParam("shOption"     , vo.getShOption())
				.queryParam("shValue"      , vo.getShValue())
				.build();
		vo.setUri("&"+uri.toUriString().substring(1, uri.toUriString().length()));
	}
	
	// 엑셀다운
	@RequestMapping(value = "/excelDown")
	public void excelDown(CodeVo vo, HttpServletResponse httpServletResponse) throws Exception {
		// 일자설정
		UtilFunction.setSearch(vo);
		
		// 조회자료 전체건수
		int rowCount = service.getCount(vo);
		
		if(rowCount > 0) {	
			// 페이지설정
			vo.setPagingVo(rowCount);
			
			// 자료조회
			List<CodeDto> list = service.selectList(vo);
			
			Workbook workbook = new XSSFWorkbook();
			Sheet sheet = workbook.createSheet("첫시트");
			CellStyle cellStyle = workbook.createCellStyle();
			Row row = null;
			Cell cell  = null;
			int rowNum = 0;
			
			sheet.setColumnWidth(0, 2100);
			sheet.setColumnWidth(1, 3100);
			
			String[] tableHead = {"순번","그룹순번","그룹명","코드순번","코드명","삭제여부","등록일시","수정일시"};
			
			for(int i=0; i < list.size(); i++) {
				row = sheet.createRow(rowNum++);
//	            String type: null 전달 되어도 ok
//	            int, date type: null 시 오류 발생 하므로 null check
//	            String type 이지만 정수형 데이터가 전체인 seq 의 경우 캐스팅
				
				// 순번
				cell = row.createCell(0);
				cellStyle.setAlignment(HorizontalAlignment.CENTER);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(list.get(i).getXrowSeq());
				
				// 그룹순번
				cell = row.createCell(1);
				cellStyle.setAlignment(HorizontalAlignment.CENTER);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(list.get(i).getCdgSeq());
				
				// 그룹명
				cell = row.createCell(2);
				cellStyle.setAlignment(HorizontalAlignment.LEFT);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(list.get(i).getCdgName());
				
				// 코드순번
				cell = row.createCell(3);
				cellStyle.setAlignment(HorizontalAlignment.CENTER);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(list.get(i).getCdcSeq());
				
				// 코드명
				cell = row.createCell(4);
				cellStyle.setAlignment(HorizontalAlignment.LEFT);
				cell.setCellStyle(cellStyle);
				cell.setCellValue(list.get(i).getCdcName());
				
				// 삭제여부
				cell = row.createCell(5);
				cellStyle.setAlignment(HorizontalAlignment.CENTER);
				cell.setCellStyle(cellStyle);
				if(list.get(i).getCdcDelNy() != null) {
					cell.setCellValue(list.get(i).getCdcDelNy() == 0 ? "N" : "Y");
				}
				
				// 등록일시
				cell = row.createCell(6);
				cellStyle.setAlignment(HorizontalAlignment.CENTER);
				cell.setCellStyle(cellStyle);
				if(list.get(i).getCdcRegDt() != null) {
					cell.setCellValue(UtilDateTime.dateTimeToString(list.get(i).getCdcRegDt()));
				}
				
				// 수정일시
				cell = row.createCell(7);
				cellStyle.setAlignment(HorizontalAlignment.CENTER);
				cell.setCellStyle(cellStyle);
				if(list.get(i).getCdcUdtDt() != null) {
					cell.setCellValue(UtilDateTime.dateTimeToString(list.get(i).getCdcUdtDt()));
				}
			}
			
			httpServletResponse.setContentType("ms-vnd/excel");
			httpServletResponse.setHeader("Content-Disposition", "attachment;filename=example.xlsx");
			
			workbook.write(httpServletResponse.getOutputStream());
			workbook.close();
		};
	}
}
