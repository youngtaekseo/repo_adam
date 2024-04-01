fnGoList = function(pgPage) {
    document.querySelector("input[name=pgPage]").value = pgPage;
    form.action = goUrlSdmList;
    form.submit();
}

fnPagingProduct = function() {
    let script = "";

	$.ajax({
		async: true
        , caches: false
        , type: "post"
        , data: {}
        , url: "/productSdmListPaging"
        , datatype: "json"
        , success: function(response) {
            let list = response.list;
            let vo = response.vo;

            if(vo.pgTotalCount == 0) {
                script =
                    '<tr>'
                    + '    <td colspan="15" class="text-center text-purple-600">'
                    + '        There is no data !!!'
                    + '    </td>'
                    + '</tr>';
            } else {
                for(let idx = vo.pgStartPage; idx < vo.pgEndPage; idx++) {
                    script =
                        '<tr class="text-gray-700 dark:text-gray-400">'
                        // 체크박스 
                        + '    <td class="px-2 py-1">'
                        + '        <input class="checkboxList text-purple-600 form-checkbox focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:focus:shadow-outline-gray mr-3"'
                        + '            id = "checkbox" name="checkbox" type="checkbox"'
                        + '        style="height: 15px; width: 15px">'
                        + '</td>'
                        // 순번
                        + '<td id="seq" class="px-1 py-1" th:text="${'+list.pdtSeq+'}></td>'
                        // 차종
                        + '<td class="px-4 py-1" th:text="${@codeService.selectOneCachedCode('+list.pdtTypeCd+')}"></td>'
                        // 브랜드
                        + '<td class="px-4 py-1" th:text="${@codeService.selectOneCachedCode('+list.pdtBrandCd+')}"></td>'
                        // 상품명
                        + '<td class="px-4 py-1" th:text="${'+list.pdtName+'}"></td>'
                        // 색상
                        + '<td class="px-4 py-1" th:text="${@codeService.selectOneCachedCode('+list.pdtColorCd+')}"></td>'
                        // 연식
                        + '<td class="px-4 py-1" th:text="${'+list.xpdtYearMonth+'}"></td>'
                        // 주행기록
                        + '<td class="px-4 py-1" th:text="${#numbers.formatInteger('+list.pdtRunKm+', 1, &#39;COMMA&#39;)}" style="text-align:right"></td>'
                        // 판매가격
                        + '<td class="px-4 py-1" th:text="${#numbers.formatInteger('+list.pdtPrice+', 1, &#39;COMMA&#39;)}" style="text-align:right"></td>'
        
                        // 사고유무
                        + '<td class="px-4 py-1">'
                        + '        <span class="px-2 py-1 font-semibold leading-tight text-blue-500 bg-blue-100 rounded-full dark:bg-blue-500 dark:text-blue-100"'
                        + '        th:text="${#strings.substring(@codeService.selectOneCachedCode('+list.pdtAccidentCd+'),0,1)}">'
                        + '        </span>'
                        + '</td>'
                        // 추천여부
                        + '<td class="px-4 py-1">'
                        + '        <span class="px-2 py-1 font-semibold leading-tight text-blue-500 bg-blue-100 rounded-full dark:bg-blue-500 dark:text-blue-100"'
                        + '        th:text="${#strings.substring(@codeService.selectOneCachedCode('+list.pdtRecommendCd+'),0,1)}">'
                        + '        </span>'
                        + '</td>'
                        // 삭제여부
                        + '<td class="px-4 py-1" th:if="${'+list.pdtDelNy+' eq 0}">'
                        + '        <span class="px-2 py-1 font-semibold leading-tight text-green-700 bg-green-100 rounded-full dark:bg-green-700 dark:text-green-100">'
                        + '             N'
                        + '        </span>'
                        + '</td>'
                        + '<td class="px-4 py-1" th:unless="${'+list.pdtDelNy+' eq 0}">'
                        + '        <span class="px-2 py-1 font-semibold leading-tight text-orange-700 bg-orange-100 rounded-full dark:text-white dark:bg-orange-600">'
                        + '             Y'
                        + '        </span>'
                        + '</td>'
                        // 작업버튼
                        + '<td class="px-6 py-1">'
                        + '    <button class="px-2 py-1 text-sm font-medium leading-5 text-white transition-colors duration-150 bg-purple-600 border border-transparent rounded-md active:bg-purple-600 hover:bg-purple-700 focus:outline-none focus:shadow-outline-purple">'
                        + '        <a th:href="@{/productSdmForm(pdtSeq=${'+list.pdtSeq+'}, pdtName=${'+list.pdtName+'})}">'
                        + '            <svg class="w-5 h-5" data-slot="icon" fill="currentColor" viewBox="0 0 20 20" xmlns="http://www.w3.org/2000/svg" aria-hidden="true">'
                        + '                <path d="m5.433 13.917 1.262-3.155A4 4 0 0 1 7.58 9.42l6.92-6.918a2.121 2.121 0 0 1 3 3l-6.92 6.918c-.383.383-.84.685-1.343.886l-3.154 1.262a.5.5 0 0 1-.65-.65Z"></path>'
                        + '                <path d="M3.5 5.75c0-.69.56-1.25 1.25-1.25H10A.75.75 0 0 0 10 3H4.75A2.75 2.75 0 0 0 2 5.75v9.5A2.75 2.75 0 0 0 4.75 18h9.5A2.75 2.75 0 0 0 17 15.25V10a.75.75 0 0 0-1.5 0v5.25c0 .69-.56 1.25-1.25 1.25h-9.5c-.69 0-1.25-.56-1.25-1.25v-9.5Z"></path>'
                        + '            </svg>'
                        + '        </a>'
                        + '        </button>'
                        + '</td>'
                        // 등록일시
                        + '<td class="px-4 py-1" th:text="${#dates.format('+list.pdtRegDt+', &#39;yyyy-MM-dd HH:mm:ss&#39;)}"></td>'
                        // 수정일시
                        + '<td class="px-4 py-1" th:text="${#dates.format('+list.pdtUdtDt+', &#39;yyyy-MM-dd HH:mm:ss&#39;)}"></td>'
                        + '</tr>';
                };

                $(".trList").html(script);
            };
        }
        , error : function(jqXHR, textStatus, errorThrown){
            alert("ajaxUpdate " + jqXHR.textStatus + " : " + jqXHR.errorThrown);
        }
	});
};