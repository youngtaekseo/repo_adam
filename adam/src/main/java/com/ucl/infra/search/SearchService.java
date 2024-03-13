package com.ucl.infra.search;

import java.util.Collections;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ucl.common.util.UtilPaging;
import com.ucl.common.util.UtilPagingResponse;
import com.ucl.infra.codegroup.CodeGroupDto;

@Service
public class SearchService {

	@Autowired
	SearchDao dao;
	
    /**
     * 게시글 리스트 조회
     * @param params - search conditions
     * @return 게시글 리스트
     */
//    public List<CodeGroupDto> findAll(SearchVo vo) {
//        // 조건에 해당하는 데이터가 없는 경우, 응답 데이터에 비어있는 리스트와 null을 담아 반환
//        int count = dao.count(vo);
//        if (count < 1) {
//            return new UtilPagingResponse<>(Collections.emptyList(), null);
//        }
//
//        // Pagination 객체를 생성해서 페이지 정보 계산 후 SearchDto 타입의 객체인 params에 계산된 페이지 정보 저장
//        UtilPaging pagination = new UtilPaging(count, vo);
//        vo.setPagination(pagination);
//
//        // 계산된 페이지 정보의 일부(limitStart, recordSize)를 기준으로 리스트 데이터 조회 후 응답 데이터 반환
////        List<PostResponse> list = dao.findAll(vo);
//        
//        
//        return new UtilPagingResponse<>(dao.findAll(vo), pagination);
//
////        return dao.findAll(vo);
//    }
}
