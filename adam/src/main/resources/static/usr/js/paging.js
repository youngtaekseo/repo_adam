// 사용자용 페이징
fnGoList = function(pgPage) {
    document.querySelector("input[name=pgPage]").value = pgPage;
    form.action = goUrlUsrList;
    form.submit();
}