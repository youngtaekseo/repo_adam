// 입력항목 확인
fnValidation = function() {
	let obj;
	let validType;
	let validName;
	let validText;
	let valid;
	let validChk;

    if(nameArr.length == 0) {
		validChk = true;
	} else {
		for(let i = 0; i < nameArr.length; i++) {
			validName = nameArr[i];
			obj = document.querySelector("input[name="+validName+"]");
			
			try {
				if((obj.getAttribute("style") == null) || (obj.getAttribute("style") == "")) {
					validType = typeArr[i];
					validText = textArr[i];
					validName = nameArr[i]+"Valid";
					objValid = document.querySelector("span[name="+validName+"]");
					
					if(!fnNullToEmpty(obj, objValid, validText)) {
				    	validChk = false;
				    } else {
						// 정규식 확인
						validChk = fnValidType(obj, objValid, validText, validType);
				    };	
			    			
					if(!validChk) {
						obj.focus();
						break;
					};				
				};				
			}
			catch(e) {
				continue;
			};
		};		
	};
 	
	return validChk;
};

// 정규식 확인
// function(obj:input tag객체, objValid:메시지표시 tag명, validText:objValid tag에 표시할 메시지, validType:정규식종류)
fnValidType = function(obj, objValid, validText, validType) {
	// 정규식 적용
	// validType : 1(한글), 2(영문), 3(한글영문), 4(숫자)
	//             5(한글숫자), 6(영문숫자), 7(한글영문숫자)
	//			   10(비밀번호), 11(날짜), 12(이메일), 13(필수입력아님)
	let rtReturn = false;
			
	switch(validType) {
		case 1: // 한글
			rtReturn = fnKorean(obj, objValid, validText);
			break;
		case 2: // 영문
			rtReturn = fnEnglish(obj, objValid, validText);
			break;
		case 3: // 한글영문
			rtReturn = fnKoreanEnglish(obj, objValid, validText);
			break;
		case 4: // 숫자
			rtReturn = fnNumber(obj, objValid, validText);
			break;
		case 5: // 한글숫자
			rtReturn = fnKoreanNumber(obj, objValid, validText);
			break;
		case 6: // 영문숫자
			rtReturn = fnEnglishNumber(obj, objValid, validText);
			break;
		case 7: // 한글영문숫자
			rtReturn = fnKoreanEnglishNumber(obj, objValid, validText);
			break;			
		case 10: // 비밀번호
			rtReturn = fnPassword(obj, objValid, validText);
			break;
		case 11: // 날짜
			rtReturn = fnDate(obj, objValid, validText);
			break;
		case 12: // 이메일
			rtReturn = fnEmail(obj, objValid, validText);
			break;
	};
	
	return rtReturn;
};

// null, 공백 확인
fnNullToEmpty = function(obj, objValid, validText) {
	let objValue = obj.value.trim(); // replace(/\s/g, ""); // 공백제저
    let dispText = "입력값이 없습니다";
    
    if(validText != "") {dispText = validText;};

	if((objValue == null) || (objValue == 'null') || (objValue.length == 0)) {
    	obj.setAttribute("class","block w-full mt-1 text-sm border-red-600 dark:text-gray-300 dark:bg-gray-700 focus:border-red-400 focus:outline-none focus:shadow-outline-red form-input");
    	objValid.innerHTML = dispText;
    	objValid.setAttribute("style", "");
		return false; // null 또는 공백
	} else {
		return true; // 값 존재
	};
};

// 비밀번호 정규식
// 영문 숫자 특수기호 조합 8자리 이상
fnPassword = function(obj, objValid, validText) {
	let regExp = /^(?=.*[a-zA-Z])(?=.*[!@#$%^*+=-])(?=.*[0-9]).{1,5}$/;
	return fnValidationCheck(obj, objValid, validText, regExp);	
};

// 날짜 정규식
fnDate = function(obj, objValid, validText) {
	try {
			if((shOptionDate.value == null) || (shOptionDate.value == "") || (shOptionDate.value.length == 0)) {
				return true;
			} else {
				let regExp = /^\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$/;
				return fnValidationCheck(obj, objValid, validText, regExp);			
			};	
	}
	catch(e){
		return true;
	};
}; 

// 한글
fnKorean = function(obj, objValid, validText) {
	let regExp = /^[ㄱ-ㅎ가-힣]+$/;
	return fnValidationCheck(obj, objValid, validText, regExp);	
}; 

// 영문
fnEnglish = function(obj, objValid, validText) {
	let regExp = /^[a-zA-Z]+$/;
	return fnValidationCheck(obj, objValid, validText, regExp);	
}; 

// 한글영문
fnKoreanEnglish = function(obj, objValid, validText) {
	let regExp = /^[ㄱ-ㅎ가-힣a-zA-Z]+$/;
	return fnValidationCheck(obj, objValid, validText, regExp);	
};
 
// 숫자
fnNumber = function(obj, objValid, validText) {
	let regExp = /^[0-9]+$/;
	return fnValidationCheck(obj, objValid, validText, regExp);	
};
 
// 한글숫자
fnKoreanNumber = function(obj, objValid, validText) {
	let regExp = /^[ㄱ-ㅎ가-힣0-9]+$/;
	return fnValidationCheck(obj, objValid, validText, regExp);	
}; 

// 영문숫자
fnKoreanNumber = function(obj, objValid, validText) {
	let regExp = /^[a-zA-Z0-9]+$/;
	return fnValidationCheck(obj, objValid, validText, regExp);	
}; 

// 한글영문숫자
fnKoreanEnglishNumber = function(obj, objValid, validText) {
	let regExp = /^[ㄱ-ㅎ가-힣a-zA-Z0-9]+$/;
	return fnValidationCheck(obj, objValid, validText, regExp);	
}; 

// 이메일 정규식
fnEmail = function(obj, objValid, validText) {
	let regExp = /^[a-zA-Z0-9+-_.]+@[a-zA-Z0-9-]+\.[a-zA-Z0-9-.]+$/;
	return fnValidationCheck(obj, objValid, validText, regExp);	
};

// 유효성 검사
fnValidationCheck = function(obj, objValid, validText, regExp) {	
	let objValue = obj.value.trim();
    let dispText = "입력 형식 불일치";
    let validName;
    
    if(objValid == "" || objValid == null) {
	    validName = obj.name+"Valid";
		objValid = querySelector("span[name="+validName+"]");
	};
    
    if(objValue == "") {
		obj.setAttribute("class","block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input");
		objValid.setAttribute("style", "visibility:hidden;");
		return true;		
	} else {
	    if(validText != "") {dispText = validText;};
	    
		if(!regExp.test(objValue)) {
			obj.setAttribute("class","block w-full mt-1 text-sm border-red-600 dark:text-gray-300 dark:bg-gray-700 focus:border-red-400 focus:outline-none focus:shadow-outline-red form-input");
			objValid.innerHTML = dispText;
			objValid.setAttribute("style", "");
			return false;
		} else {
			obj.setAttribute("class","block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input");
			objValid.setAttribute("style", "visibility:hidden;");
			return true;
		};		
	};

};

// 날짜항목에 대한 특수문자처리
fnSpecialDate = function(obj, objValid, validText) {
	let regExp = /^\d{4}-(0[1-9]|1[012])-(0[1-9]|[12][0-9]|3[01])$/;
	fnValidationCheck(obj, validText, regExp);
	return false;
};

// 특수문자확인
fnSpecialCharCheck = function(obj, text, regExp) {
	let validName = obj.name+"Valid";
	let objValid = document.querySelector("span[name="+validName+"]");

	if (regExp.test(obj.value.trim()) == true) {
		obj.setAttribute("class","block w-full mt-1 text-sm border-red-600 dark:text-gray-300 dark:bg-gray-700 focus:border-red-400 focus:outline-none focus:shadow-outline-red form-input");
		objValid.innerHTML = text;
		objValid.setAttribute("style", "");
	} else {
		obj.setAttribute("class","block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input");
		objValid.setAttribute("style", "visibility:hidden;");
	};
};
