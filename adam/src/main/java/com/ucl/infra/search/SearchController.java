package com.ucl.infra.search;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.ucl.common.constants.Commvar;
import com.ucl.common.util.UtilFunction;
import com.ucl.common.util.UtilPagingResponse;

@Controller
public class SearchController {

	@Autowired
	SearchService service;
	
    // 게시글 리스트 페이지
    @RequestMapping(value ="/searchList")
    public String searchList(@ModelAttribute("vo") SearchVo vo, Model model) throws Exception {
    	System.out.println("======================================= searchList");
    	UtilFunction.setSearch(vo);
    	
//        UtilPagingResponse<service.findAll(vo)> response = service.findAll(vo);
//        model.addAttribute("response", response);
        
//        model.addAttribute("list", service.findAll(vo));
        return Commvar.PATH_CODE_GROUP + "codeGroupSdmList";
    }  
}
