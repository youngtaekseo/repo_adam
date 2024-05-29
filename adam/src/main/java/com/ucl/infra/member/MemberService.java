package com.ucl.infra.member;

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
import com.ucl.infra.mail.SendGmail;

@Service
public class MemberService {
	@Value("${file_upload_type}")
	private String fileUploadType;
	
	@Autowired
	BaseService baseService;
	
	@Autowired
	MemberDao dao;
	
	@Autowired
	SendGmail sendGmail;
	
	public List<MemberDto> selectList(MemberVo vo) {
		return dao.selectList(vo);
	}
	
	public List<MemberDto> selectListCode(MemberVo vo) {
		return dao.selectListCode(vo);
	}
	
	public MemberDto selectOne(MemberDto dto) {
		dto.setXstorage(fileUploadType);
		return dao.selectOne(dto);
	}
	
	public MemberDto selectOneLogin(MemberDto dto) {
		return dao.selectOneLogin(dto);
	}
	
	public int selectOneCount(MemberVo vo) {
		return dao.selectOneCount(vo);
	}	
	
	// 저장
	public int restInsert(MemberDto dto) throws Exception {
		return dao.insert(dto);
	}
	
	// 수정
	public int restUpdate(MemberDto dto) throws Exception {
		return dao.update(dto);
	}
		
	// 저장
	public int insert(MemberDto dto, FileUpLoadDto fDto) throws Exception {
		dao.insert(dto);

		// 메일전송
		//sendGmail.sendMail();
		
		// 메일전송(Thread 이용)
		//==========================================
		Thread thread = new Thread(new Runnable() {
			@Override
			public void run() {
				sendGmail.sendMail(dto);
			}
		});
		
		thread.start();
		//==========================================
		
		fDto.setStorage(fileUploadType);
		fDto.setCategory("0"); // 0:회원, 1:상품
		fDto.setDefaultNy("0");
		fDto.setSort(0);
		fDto.setPseq(dto.getMbrSeq());

		if(fileUploadType.equals("0")) { //aws
			// AWS S3 파일첨부
			baseService.fileUploadS3(dto.getUploadFile(), fDto);
		} else if(fileUploadType.equals("1")) { //nas
			// NAS 파일첨부
			baseService.fileUploadNas(dto.getUploadFile(), fDto);			
		}
		
		return 0; 
	}
	
	// 수정
	public int update(MemberDto dto, FileUpLoadDto fDto) throws Exception {
		dao.update(dto);
		
		fDto.setStorage(fileUploadType);
		fDto.setCategory("0"); // 0:회원, 1:상품
		fDto.setDefaultNy("0");
		fDto.setSort(0);
		fDto.setPseq(dto.getMbrSeq());	
		
		if(fileUploadType.equals("0")) { //aws
			// AWS S3 파일첨부
			baseService.fileUploadS3(dto.getUploadFile(), fDto);		
		} else if(fileUploadType.equals("1")) { //nas
			// NAS 파일첨부
			baseService.fileUploadNas(dto.getUploadFile(), fDto);			
		}
		
		return 0;
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
	
	//이미지갯수
	public MemberDto selectOneImageCount(MemberDto dto) throws Exception {
		dto.setXstorage(fileUploadType);
		return dao.selectOneImageCount(dto);
	};
	
	//상품이미지조회
	public List<MemberDto> selectListImages(MemberDto dto) {
		dto.setXstorage(fileUploadType);
		return dao.selectListImages(dto);
	};
	
	//이미지변환
	public List<MemberDto> getBase64ExternalImage(MemberDto dto) throws Exception {
    	List<MemberDto> returnList = new ArrayList<>();
    	
    	// 이미지파일조회
    	List<MemberDto> listDto = selectListImages(dto);
    	
    	for(MemberDto forDto : listDto) {
        	// 이미지 파일을 파일 시스템에서 로드
            File imgPath = new File(forDto.getXpathUpload());
            BufferedImage bufferedImage = ImageIO.read(imgPath);

            // 이미지를 byte 배열로 변환
            ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
            ImageIO.write(bufferedImage, forDto.getXext(), outputStream); // 이미지 확장자:forDto.getxExt()
            byte[] imageBytes = outputStream.toByteArray();

            MemberDto dto2 = new MemberDto();
            
            // byte 배열을 Base64 문자열로 인코딩하여 반환
            dto2.setXpathUpload(Base64.getEncoder().encodeToString(imageBytes));
            dto2.setXfileName(forDto.getXfileName());
            dto2.setXext(forDto.getXext().toLowerCase());
            returnList.add(dto2);		
    	}
        return returnList;
    }
}
