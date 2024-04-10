
// 차량조회
fnKeyupSearch = function() {
	if(window.event.keyCode == 13) {
		fnSearch();
	}
}

// 차량조회
fnSearch = function() {
	// controller 호출
	location.href="/productUsrList?shPdtName="+$("#shPdtName").val();
}