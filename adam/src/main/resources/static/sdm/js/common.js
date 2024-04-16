 fnLogout = function() {
	  $.ajax({
		  async: true
		  , cache: false
		  , type: "post"
		  , url: "/memberSdmLogOut"
		  , success: function(response){
			  if(response.rt == "success") {
				  location.href = "/loginSdm";
			  } else {
				  alert("로그아웃 실패");
			  }
		  }
		  , error : function(jqXHR, textStatus, errorThrown){
		 	  alert("ajaxUpdate " + jqXHR.textStatus + " : " + jqXHR.errorThrown);
		  }
	  });			 
 }
 
// 리스트 체크박스 체크수 확인
fnDeleteChkeckBox = function() {
	let checkedList = document.querySelectorAll("input[name=checkbox]");
	let resultValue = 0;
	
	for(let i = 0; i < checkedList.length; i++) {
		if(checkedList[i].checked) {
			resultValue++;
		}
	}
	return resultValue;	
}