package com.ucl.infra.member;

import java.util.List;

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
		return dao.selectOne(dto);
	}
	
	public MemberDto selectOneLogin(MemberDto dto) {
		return dao.selectOneLogin(dto);
	}
	
	public int selectOneCount(MemberVo vo) {
		return dao.selectOneCount(vo);
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
		
		fDto.setCategory("0"); // 0:회원, 1:상품
		fDto.setDefaultNy("0");
		fDto.setSort(0);
		fDto.setPseq(dto.getMbrSeq());

		if(fileUploadType.toLowerCase().equals("aws")) {
			// AWS S3 파일첨부
			baseService.fileUploadS3(dto.getUploadFile(), fDto);
		} else if(fileUploadType.toLowerCase().equals("nas")) {
			// NAS 파일첨부
			baseService.fileUploadNas(dto.getUploadFile(), fDto);			
		}
		
		return 0; 
	}
	
	// 수정
	public int update(MemberDto dto, FileUpLoadDto fDto) throws Exception {
		dao.update(dto);
		
		fDto.setCategory("0"); // 0:회원, 1:상품
		fDto.setDefaultNy("0");
		fDto.setSort(0);
		fDto.setPseq(dto.getMbrSeq());		
		
		if(fileUploadType.toLowerCase().equals("aws")) {
			// AWS S3 파일첨부
			baseService.fileUploadS3(dto.getUploadFile(), fDto);		
		} else if(fileUploadType.toLowerCase().equals("nas")) {
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
}
