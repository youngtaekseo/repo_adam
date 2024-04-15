 fnLogout = function() {
	  $.ajax({
		  async: true
		  , cache: false
		  , type: "post"
		  , url: "/memberSdmLogOut"
		  , success: function(response){
			  if(response.rt == "success") {
				  location.href = "/indexUsr";
			  } else {
				  alert("로그아웃 실패");
			  }
		  }
		  , error : function(jqXHR, textStatus, errorThrown){
		 	  alert("ajaxUpdate " + jqXHR.textStatus + " : " + jqXHR.errorThrown);
		  }
	  });			 
}

// 로그인확인
fnLoginChecked = function() {
	let returnChk;
	let loginId = sessionStorage.getItem("sessMbrSeq");
	
	if(loginId == null || loginId.length == 0) {
		fnModalFormOneBtnShow("확인", "로그인 후 진행 가능합니다");
		returnChk = false;
	} else {
		returnChk = true;
	}
	
	return returnChk;		 
}
 	
// 찜클릭		
fnDataDelete = function(obj) {
	// 로그인확인
	if(!fnLoginChecked()) {return;}
			
	// 제품순번
	let wishlist = document.querySelector("input[name="+obj.name+"]");
	let pdtSeq   = wishlist.value;			
	let heart    = document.querySelector("i[name="+obj.name+"]");
	
	// 찜순번
	let wishlistWshSeq = document.querySelector("input[name="+obj.name+"_]");
	let wshSeq         = wishlistWshSeq.value;
	
	$.ajax({
		async: true
		, cache: false
		, type: "post"
		, data: {"pdtSeq":pdtSeq, "wshSeq":wshSeq}
		, url: "/insertWishlist"
		, success: function(response) {
			if(response.rt == "success") {
				fnModalFormOneBtnShow("등록", "찜 등록 성공");
				heart.setAttribute("style", "color:pink;");
				wishlistWshSeq.setAttribute("value", response.wshSeq);
			} else if(response.rt == "fail") {
				fnModalFormOneBtnShow("등록", "찜 등록중 오류가 있습니다");
			} else if(response.rt == "exist") {
				heart.setAttribute("class", "fa fa-heart");
				fnModalFormOneBtnShow("취소", "찜 등록 취소");
				heart.setAttribute("style", "");
				wishlistWshSeq.setAttribute("value", "x");
			} else if(response.rt == "login") {
				fnModalFormOneBtnShow("확인", "로그인 후 진행 가능합니다");
			}
		}
		, error : function(jqXHR, textStatus, errorThrown){
			fnModalFormOneBtnShow("오류", "ajaxUpdate " + jqXHR.textStatus + " : " + jqXHR.errorThrown);
		}
	});
} 		 