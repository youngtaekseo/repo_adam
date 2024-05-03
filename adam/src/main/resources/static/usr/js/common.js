// 로그아웃
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

// 우편번호조회
function execDaumPostcode() {
	let themeObj = {
	   //bgColor: "", //바탕 배경색
	   searchBgColor: "#0B65C8", //검색창 배경색
	   //contentBgColor: "", //본문 배경색(검색결과,결과없음,첫화면,검색서제스트)
	   //pageBgColor: "", //페이지 배경색
	   //textColor: "", //기본 글자색
	   queryTextColor: "#FFFFFF" //검색창 글자색
	   //postcodeTextColor: "", //우편번호 글자색
	   //emphTextColor: "", //강조 글자색
	   //outlineColor: "", //테두리
	};
	
	$("#divMapValid").css("visibility", "hidden");
			
    new daum.Postcode({
		theme: themeObj
        , oncomplete: function(data) {
            // 팝업에서 검색결과 항목을 클릭했을때 실행할 코드를 작성하는 부분.

            // 각 주소의 노출 규칙에 따라 주소를 조합한다.
            // 내려오는 변수가 값이 없는 경우엔 공백('')값을 가지므로, 이를 참고하여 분기 한다.
            var addr = ''; // 주소 변수
            var extraAddr = ''; // 참고항목 변수

            //사용자가 선택한 주소 타입에 따라 해당 주소 값을 가져온다.
            if (data.userSelectedType === 'R') { // 사용자가 도로명 주소를 선택했을 경우
                addr = data.roadAddress;
            } else { // 사용자가 지번 주소를 선택했을 경우(J)
                addr = data.jibunAddress;
            }
            
            // 사용자가 선택한 주소가 도로명 타입일때 참고항목을 조합한다.
            if(data.userSelectedType === 'R'){
                // 법정동명이 있을 경우 추가한다. (법정리는 제외)
                // 법정동의 경우 마지막 문자가 "동/로/가"로 끝난다.
                if(data.bname !== '' && /[동|로|가]$/g.test(data.bname)){
                    extraAddr += data.bname;
                }
                // 건물명이 있고, 공동주택일 경우 추가한다.
                if(data.buildingName !== '' && data.apartment === 'Y'){
                    extraAddr += (extraAddr !== '' ? ', ' + data.buildingName : data.buildingName);
                }
                // 표시할 참고항목이 있을 경우, 괄호까지 추가한 최종 문자열을 만든다.
                if(extraAddr !== ''){
                    extraAddr = ' (' + extraAddr + ')';
                }
                // 조합된 참고항목을 해당 필드에 넣는다.
                document.getElementById("mbrAddrRemark").value = extraAddr;
            
            } else {
                document.getElementById("mbrAddrRemark").value = '';
            }

            // 우편번호와 주소 정보를 해당 필드에 넣는다.
            document.getElementById('mbrZipCode').value = data.zonecode;
            document.getElementById("mbrAddr").value = addr;
            
            Promise.resolve(data).then(o => {
                const { address } = data;

                return new Promise((resolve, reject) => {
                    const geocoder = new daum.maps.services.Geocoder();

                    geocoder.addressSearch(address, (result, status) =>{
                        if(status === daum.maps.services.Status.OK){
                            const { x, y } = result[0];

                            resolve({ lat: y, lon: x })
                        }else{
                            reject();
                        }
                    });
                })
            }).then(result => {
                // 위, 경도 결과 값
                document.getElementById("mbrPointLat").value = result.lat; // 위도
                document.getElementById("mbrPointLon").value = result.lon; // 경도
            });

            // 커서를 상세주소 필드로 이동한다.
            document.getElementById("mbrAddrD").focus();
        }
    }).open();
}

// 지도보기
function mapDisplay(traffic) {
	let lat = $("#mbrPointLat").val();
	let lon = $("#mbrPointLon").val();
	
	if(lat == null || lat == "" || lon == null || lon == "") {
		$("#divMapValid").css("visibility", "");
		$("#btnMapValid").text("주소 검색 후 진행 바랍니다");
		$("#map").css("display", "none");
		return;
	}
	
	$("#trafficLabel").css("display", "");
	$("#map").css("display", "");
	
	var mapContainer = document.getElementById('map'), // 지도를 표시할 div 
	    mapOption = { 
	        center: new kakao.maps.LatLng(lat, lon), // 지도의 중심좌표
	        level: 3 // 지도의 확대 레벨
	    };
	
	// 지도를 생성합니다
	var map = new kakao.maps.Map(mapContainer, mapOption); 
	
	// 지도에 교통정보를 표시하도록 지도타입을 추가합니다
	if(traffic == true){
		map.addOverlayMapTypeId(kakao.maps.MapTypeId.TRAFFIC);  
	}
	
	// 일반 지도와 스카이뷰로 지도 타입을 전환할 수 있는 지도타입 컨트롤을 생성합니다
	var mapTypeControl = new kakao.maps.MapTypeControl();
	
	// 지도에 컨트롤을 추가해야 지도위에 표시됩니다
	// kakao.maps.ControlPosition은 컨트롤이 표시될 위치를 정의하는데 TOPRIGHT는 오른쪽 위를 의미합니다
	map.addControl(mapTypeControl, kakao.maps.ControlPosition.TOPRIGHT);
	
	// 지도 확대 축소를 제어할 수 있는  줌 컨트롤을 생성합니다
	var zoomControl = new kakao.maps.ZoomControl();
	map.addControl(zoomControl, kakao.maps.ControlPosition.RIGHT);
	
	// 마우스 휠로 지도 확대,축소 가능여부를 설정합니다
	map.setZoomable(false);   
	
	// 마커가 표시될 위치입니다 
	var markerPosition  = new kakao.maps.LatLng(lat, lon); 
	
	// 마커를 생성합니다
	var marker = new kakao.maps.Marker({
	    position: markerPosition
	});
	
	// 마커가 지도 위에 표시되도록 설정합니다
	marker.setMap(map);		
}