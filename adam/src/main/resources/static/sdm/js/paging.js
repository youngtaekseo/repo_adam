fnGoList = function(pgPage) {
    document.querySelector("input[name=pgPage]").value = pgPage;
    form.action = goUrlSdmList;
    form.submit();
}