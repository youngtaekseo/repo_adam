package com.ucl.infra.excel;

import org.apache.poi.ss.usermodel.DataFormatter;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.multipart.MultipartFile;

import com.ucl.infra.codegroup.CodeGroupDto;
import com.ucl.infra.codegroup.CodeGroupService;

@Controller
public class ExcelController {
	@Autowired
	CodeGroupService service;
	
	@RequestMapping(value = "/excelUpload")
	public String excelUpload(CodeGroupDto dto) throws Exception {
		// 선택된 엑셀파일
		MultipartFile multipartFile = dto.getUploadFile();
		if(!multipartFile.isEmpty() && multipartFile != null) {
			XSSFWorkbook workbook = new XSSFWorkbook(multipartFile.getInputStream());
			XSSFSheet workSheet = workbook.getSheetAt(0);
			
			for(int i = 0; i < workSheet.getPhysicalNumberOfRows(); i++) {
				DataFormatter dataFormatter = new DataFormatter();
				XSSFRow row = workSheet.getRow(i);
				
				dto.setCdgName(dataFormatter.formatCellValue(row.getCell(0)));
				
				service.insert(dto);
			}
			
			workbook.close();
		}
		return "redirect:/codeGroupSdmList";
	}
}
