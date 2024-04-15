
// 모달폼 띄우기
fnModalFormOneBtnShow = function(paramTitle, paramContent) {
	$("#title").html(paramTitle);
	$("#modalFormOneBtnContent").html(paramContent);
	$("#modalFormOneBtn").modal('show');
}

// 모달폼 내용 수정
fnModalCaption = function(paramTitle, paramContent, paramButton) {
	$("#title").html(paramTitle);
	$("#content").html(paramContent);
	$("#btnName1").html(paramButton);
}