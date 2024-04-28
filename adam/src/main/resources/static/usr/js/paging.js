// 사용자용 페이징
fnGoList = function(pgPage) {
	// 조회조건:차종, 제조사, 연식, 색상
	fnCheckboxClick();
	
    document.querySelector("input[name=pgPage]").value = pgPage;
    form.action = goUrlUsrList;
    form.submit();
}