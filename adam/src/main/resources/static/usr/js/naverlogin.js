// 네이버 로그인
const naverLogin = new naver.LoginWithNaverId({
    clientId: "r1aTHRBql6Ny_r2b60bZ",
    callbackUrl: "http://localhost:8081/indexUsr",
    //loginButton: {color: "green", type: 2, height: 40}
});

naverLogin.init();
    
fnNaverLogin = function() {
    naverLogin.getLoginStatus(function (status) {
		alert(status);
		if (status) {
			const nickName = naverLogin.user.getNickName();
			const age      = naverLogin.user.getAge();
			const birthday = naverLogin.user.getBirthday();
			
			/*if(nickName===null||nickName===undefined ){
			    alert("별명이 필요합니다. 정보제공을 동의해주세요.");
			    naverLogin.reprompt();
			    return ;  
			 } else{
			  setLoginStatus();
			 }*/
			
			setLoginStatus();
		}
	});
	
	//console.log(naverLogin);
	
	function setLoginStatus(){
		let email = naverLogin.user.email;
		let name  = naverLogin.user.name;
		
		$.ajax({
			async: true
			, cache: false
			, type: "post"
			, data: {"name":name, "email":email}
			, url: "/naverLoginInsert"
			, success: function(response) {
				if(response.rt == "success") {
					location.href = "/indexUsr";
				} else if(response.rt == "fail") {
					alert("실패");
				}
			}
			, error : function(jqXHR, textStatus, errorThrown){
				alert("ajaxUpdate " + jqXHR.textStatus + " : " + jqXHR.errorThrown);
			}
		});
	}
}    
    
