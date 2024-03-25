		// 필수입력항목 확인
		fnValidation = function() {
			let obj;
			let text;
			let validName;
			let valid;
			let loopChk;
			let submitChk = true;			
			let validChk = true;
			
			for(let i = 0; i < nameArr.length; i++) {
				for (let j = 0; j < form.elements.length; j++) {
					
					// 날짜옵션
					if((nameArr[i] === "shDateStart") || (nameArr[i] === "shDateEnd")) {
						if(shOptionDate.value != "") {
							loopChk = true;
						} else {
							loopChk = false;
						};	
					} else if(nameArr[i] === "shValue") { // 검색어옵션
						if(shOption.value != "") {
							loopChk = true;
						} else {
							loopChk = false;
						};							
					} else if(nameArr[i] === "shOptionRunKmFrom" || nameArr[i] === "shOptionRunKmTo") { // 주행기록
						if(shOptionRunKm.value != "") { // 주행기록 옵션
							loopChk = true;
						} else {
							loopChk = false;
						};							
					} else if(nameArr[i] === "shOptionYearFrom" || nameArr[i] === "shOptionMonthFrom" || nameArr[i] === "shOptionYearTo" || nameArr[i] === "shOptionMonthTo") { // 연식
						if(shOptionYear.value != "") { // 연식 옵션
							loopChk = true;
						} else {
							loopChk = false;
						};							
					} else {
						loopChk = true;
					};
					
					if(loopChk) {
						if (nameArr[i] == form.elements[j].name) {
							obj = form.elements[j];
							text = textArr[i];
							validName = nameArr[i]+"Valid";
							objValid = document.querySelector("span[name="+validName+"]");
							
 							if((nameArr[i] === "shOptionYearTo" || nameArr[i] === "shOptionMonthTo") && (shOptionYear.value != "1")){ // 연식
 								validChk = true;
 							} else {
 								validChk = fnValidationIn(obj, text, objValid);	
 							};

							// 이메일
							if((nameArr[i].match("Email") != null) && (nameArr[i].match("email") != null)){
								validChk = fnEmailCheck(obj, objValid);
							};
						
							if(submitChk) {
								submitChk = validChk;
							};
							
							break;						
						};						
					};
				};
			};
			
			return submitChk;
		};	
		
		// 입력시 필수입력 확인
//	    fnValidationIn = function(obj, text, objValid) {
//			return fnValidationUdt(obj, text, objValid);
//		};

		// 수정시 필수입력 확인
	    fnValidationUdt = function(obj, text, objValid) {
		    if(!fnNullToEmpty(obj.value)) {
		    	obj.setAttribute("class","block w-full mt-1 text-sm border-red-600 dark:text-gray-300 dark:bg-gray-700 focus:border-red-400 focus:outline-none focus:shadow-outline-red form-input");
		    	objValid.innerHTML = text;
		    	objValid.setAttribute("style", "");
		    	return false;
		    } else {
		    	obj.setAttribute("class","block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input");
		    	objValid.setAttribute("style", "visibility:hidden;");
		    	return true;
		    };
	    };	
		
		// 날짜항목에 대한 특수문자처리
		fnSpecialDate = function(obj) {
			// 특수문자가 속해있는지 확인하는 정규식 /[/{\}\[\]\/?.,;:|\)*~`!^\-_+<>@\#$%&\\\=\(\'\"]/g;
 			let regExp = /[/{\}\[\]\/?,.;:|\)*~`!^\_+<>@\#$%&\\\=\(\'\"]/g;
			let text = "특수문자는 입력할 수 없습니다";
			
			fnSpecialCharCheck(obj, text, regExp);
			return false;
		};
	
		// 특수문자확인
    	fnSpecialCharCheck = function(obj, text, regExp) {
			let validName = obj.name+"Valid";
			let objValid = document.querySelector("span[name="+validName+"]");

			if (regExp.test(obj.value) == true) {
				obj.setAttribute("class","block w-full mt-1 text-sm border-red-600 dark:text-gray-300 dark:bg-gray-700 focus:border-red-400 focus:outline-none focus:shadow-outline-red form-input");
				objValid.innerHTML = text;
				objValid.setAttribute("style", "");				
			} else {
				obj.setAttribute("class","block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input");
				objValid.setAttribute("style", "visibility:hidden;");				
			};		
    	};	
    	
	    // null, 공백 확인
	    fnNullToEmpty = function(value) {
	    	let str = value.replace(/\s/g, ""); // 공백제저
	    	
	    	if((str === null) || (str.length === 0)) {
	    		return false; // null 또는 공백 
	    	} else {
	    		return true; // 값 존재
	    	};
	    };
	    
	    // 이메일 유효성 검사
	    fnEmailCheck = function(obj, objValid) {
	    	let regExp = /^[a-zA-Z0-9._-]+@[a-zA-Z0-9.-]+\.[a-zA-Z]{2,4}$/i;

	    	if(!regExp.test(obj.value)) {
	    		obj.setAttribute("class","block w-full mt-1 text-sm border-red-600 dark:text-gray-300 dark:bg-gray-700 focus:border-red-400 focus:outline-none focus:shadow-outline-red form-input");
	    		objValid.innerHTML = "이메일 형식이 아닙니다";
	    		objValid.setAttribute("style", "");
		    	
	    		return false;
	    	} else {
	    		obj.setAttribute("class","block w-full mt-1 text-sm dark:border-gray-600 dark:bg-gray-700 focus:border-purple-400 focus:outline-none focus:shadow-outline-purple dark:text-gray-300 dark:focus:shadow-outline-gray form-input");
	    		objValid.setAttribute("style", "visibility:hidden;");
		    	
	    		return true;
	    	};
	    };