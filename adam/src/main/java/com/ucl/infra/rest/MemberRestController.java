package com.ucl.infra.rest;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ucl.common.base.BaseController;
import com.ucl.common.util.UtilFunction;
import com.ucl.infra.member.MemberDto;
import com.ucl.infra.member.MemberService;
import com.ucl.infra.member.MemberVo;

@RestController
@RequestMapping(value = "/rest")
public class MemberRestController extends BaseController {
	
	@Autowired
	MemberService service;
	
	//조회화면
	//@RequestMapping(value = "", method = RequestMethod.GET)
	@GetMapping("/selectListMember")
	public List<MemberDto> selectListMember(MemberVo vo) throws Exception {
		UtilFunction.setSearch(vo);
		List<MemberDto> list = service.selectList(vo);;
		return list;
	}
	
	//@RequestMapping(value = "", method = RequestMethod.GET)
	@GetMapping("/selectOneMember")
	public MemberDto selectOneMember(MemberDto dto) throws Exception {
		MemberDto rtDto = service.selectOne(dto);
		return rtDto;
	}
	
	//@RequestMapping(value = "/{seq}", method = RequestMethod.GET)
	@GetMapping("/{seq}")
	public MemberDto selectOnepMember(@PathVariable("seq") String seq, MemberDto dto) throws Exception {
		dto.setMbrSeq(seq);
		MemberDto rtDto = service.selectOne(dto);
		return rtDto;
	}
	
	// 저장
	@PostMapping("/insertMember")
	public void insertMember(MemberDto dto) throws Exception {
		service.restInsert(dto);
	}
	
	// 수정
	@PutMapping("/updateMember")
	public void updateMember(MemberDto dto) throws Exception {
		service.restUpdate(dto);
	}
}
