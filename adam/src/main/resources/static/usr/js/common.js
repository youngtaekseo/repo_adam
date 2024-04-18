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
	let loginId = $("#sessMbrSeq").val(); // window.sessionStorage.getItem("sessMbrSeq");

	if(loginId == null || loginId.length == 0) {
		fnModalFormOneBtnShow("확인", "로그인 후 진행 가능합니다");
		returnChk = false;
	} else {
		returnChk = true;
	}
	
	return returnChk;		 
}
 	
// 찜클릭		
fnWishlistClick = function(obj) {
	if(!fnLoginChecked()) {return false;};
	
	// 제품순번
	let wishlist = document.querySelector("input[name="+obj.name+"]");
	let pdtSeq   = wishlist.value;
	
	// 하트이미지			
	let heart    = document.querySelector("i[name="+obj.name+"]");
	
	$.ajax({
		async: true
		, cache: false
		, type: "post"
		, data: {"pdtSeq":pdtSeq}
		, url: "/insertWishlist"
		, success: function(response) {
			if(response.rt == "success") {
				$(heart).css("color", "pink");
				fnModalFormOneBtnShow("등록", "찜 등록 성공");
			} else if(response.rt == "fail") {
				fnModalFormOneBtnShow("등록", "찜 등록중 오류가 있습니다");
			} else if(response.rt == "exist") {
				$(heart).css("color", "");
				fnModalFormOneBtnShow("취소", "찜 등록 취소");
			} else if(response.rt == "login") {
				fnModalFormOneBtnShow("확인", "로그인 후 진행 가능합니다");
			}
		}
		, error : function(jqXHR, textStatus, errorThrown){
			fnModalFormOneBtnShow("오류", "ajaxUpdate " + jqXHR.textStatus + " : " + jqXHR.errorThrown);
		}
	});
}
		 