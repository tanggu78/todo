//-------------------------------------------------------
// Validate 스크립트
// 앞에  ParamValidate 를 붙여준다.
//-------------------------------------------------------

var ParamValidate = {};

//공백 체크
//ParamValidate.isEmpty(str)  => return true| false
ParamValidate.isEmpty = function(str) {
	return !(/\S/).test(str);
};

//숫자 체크
//ParamValidate.isNum(str)  => return true|false
ParamValidate.isNum = function(str) {
	return (/^[0-9]+$/).test(str);
};

//영문 체크
//ParamValidate.isAlpha()  => return true|false
ParamValidate.isAlpha = function(str){
	return (/^[a-zA-Z]+$/).test(str);	
};

//영문 숫자 체크
//ParamValidate.isAlphaNumber(str)  => return true|false
ParamValidate.isAlphaNumber = function(str){
	return (/^[0-9a-zA-Z]+$/).test(str);	
};


//특수문자 체크
//ParamValidate.isAlphaNumber(str)  => return true|false
ParamValidate.isSpecial = function(str){
	return (/[\[\]`~!@#$%^&*<>+=(){}|\\\'\";:\/?]/gi).test(str);	
};

//한글 포함 여부 체크
//ParamValidate.isHangul(str)  => return true|false	
ParamValidate.isHangul = function(str){
	return (/[ㄱ-ㅎ|ㅏ-ㅣ|가-힣]/).test(str);
	//return (/^[ㄱ-힣]+$/).test(str);	
};

//이메일 체크
//ParamValidate.isEmail(str)  => return true|false	
ParamValidate.isEmail = function(str){
	return (/^[a-zA-Z0-9\-\.\_]+@[a-zA-Z0-9\-\.]+\.([a-zA-Z]{1,5})$/).test(str);
};

//패스워드 체크
//ParamValidate.isPassword(str)  => return true|false	
ParamValidate.isPassword = function(str){
	return (/^[a-zA-Z0-9!@#$%^*()_~]{8,16}$/).test(str);
};

//핸드폰 체크
//ParamValidate.isPhonNum(str)  => return true|false
ParamValidate.isPhonNum = function(str) {
	
	/* - 생략가능*/
	return (/^01([0|1|6|7|8|9]?)-?([0-9]{3,4})-?([0-9]{4})$/).test(str);
	/* - 생략 불가능 */
	//return (/^01([0|1|6|7|8|9]?)-([0-9]{3,4})-([0-9]{4})$/).test(str);
};

//일반전화 체크
//ParamValidate.isTelNum(str)  => return true|false
ParamValidate.isTelNum = function(str) {
	
	/* - 생략가능*/
	return (/^\d{2,3}-?\d{3,4}-?\d{4}$/).test(str);
	/* - 생략 불가능 */
	//return (/^\d{2,3}-\d{3,4}-\d{4}$/).test(str);
};

/* true가 아니면 유효성을 통과하지 못한것 입니다. 리턴값 아래에 정리 */
/* 1: 길이 */
/* 2: 영문, 숫자, 특수문자 2종 이상 혼용 */
/* 3: 연속된 문자 */
/* 4: 아이디 포함 여부 */
/* 1: 길이 */
//ParamValidate.isPowerPassword = function(userId, password){
     
    // 길이
//    if(!/^[a-zA-Z0-9!@#$%^&*()?_~]{6,30}$/.test(password))
//        return 1;
     
    // 영문, 숫자, 특수문자 2종 이상 혼용
//    var chk = 0;
//    if(password.search(/[0-9]/g) != -1 ) chk ++;
//    if(password.search(/[a-z]/ig)  != -1 ) chk ++;
//    if(password.search(/[!@#$%^&*()?_~]/g)  != -1  ) chk ++;
//    if(chk < 2)
//        return 2;
     
    // 동일한 문자/숫자 3이상, 연속된 문자
//    if(/(\w)\1\1/.test(password) || isContinuedValue(password))
//        return 3;
     
    // 아이디 포함 여부
//    if(password.search(userId)>-1)
//        return 4;
     
//    return true;
         
//    function isContinuedValue(value) {
//        console.log("value = " + value);
//        var intCnt1 = 0;
//        var intCnt2 = 0;
//        var temp0 = "";
//        var temp1 = "";
//        var temp2 = "";
 
//        for (var i = 0; i < value.length-2; i++) {

//            temp0 = value.charAt(i);
//            temp1 = value.charAt(i + 1);
//            temp2 = value.charAt(i + 2);
 
//            if (temp0.charCodeAt(0) - temp1.charCodeAt(0) == 1
//                    && temp1.charCodeAt(0) - temp2.charCodeAt(0) == 1) {
//                intCnt1 = intCnt1 + 1;
//            }
 
//            if (temp0.charCodeAt(0) - temp1.charCodeAt(0) == -1
//                    && temp1.charCodeAt(0) - temp2.charCodeAt(0) == -1) {
//                intCnt2 = intCnt2 + 1;
//            }
//        }

//        return (intCnt1 > 0 || intCnt2 > 0);
//    }
//}

ParamValidate.isUserId = function(userId){
    // 길이
   if(!/^[a-zA-Z0-9!@#$%^&*()?_~]{6,30}$/.test(userId))
		return false;
   
   return true
}

/* true가 아니면 유효성을 통과하지 못한것 입니다. 리턴값 아래에 정리 */
/* 1: 길이 */
/* 2: 영문, 숫자, 특수문자 2종 이상 혼용 */
/* 3: 연속된 문자 */
/* 4: 아이디 포함 여부 */
/* 1: 길이 */
ParamValidate.isPowerPassword = function(userId, password){
     
    // 길이
    //if(!/^[a-zA-Z0-9!@#$%^&*()-_=+|[]{}'";:?>,<]{10,20}$/.test(password))
   if(!/^[a-zA-Z0-9!@#$%^*()_~]{10,20}$/.test(password))
		return 1;
     
    // 영문, 숫자 필수
    var chk = 0;
    if(password.search(/[0-9]/g) != -1 ) chk ++;
    if(password.search(/[a-z]/ig)  != -1 ) chk ++;
    if(chk < 2)
        return 2;
     
    // 동일한 문자/숫자 3이상, 연속된 문자
    if(/(\w)\1\1/.test(password) || isContinuedValue(password))
        return 3;
     
    // 아이디 포함 여부
    if(password.search(userId)>-1)
        return 4;
     
    return 0;
         
    function isContinuedValue(value) {

        var intCnt1 = 0;
        var intCnt2 = 0;
        var temp0 = "";
        var temp1 = "";
        var temp2 = "";
 
        for (var i = 0; i < value.length-2; i++) {

            temp0 = value.charAt(i);
            temp1 = value.charAt(i + 1);
            temp2 = value.charAt(i + 2);
 
            if (temp0.charCodeAt(0) - temp1.charCodeAt(0) == 1
                    && temp1.charCodeAt(0) - temp2.charCodeAt(0) == 1) {
                intCnt1 = intCnt1 + 1;
            }
 
            if (temp0.charCodeAt(0) - temp1.charCodeAt(0) == -1
                    && temp1.charCodeAt(0) - temp2.charCodeAt(0) == -1) {
                intCnt2 = intCnt2 + 1;
            }
        }

        return (intCnt1 > 0 || intCnt2 > 0);
    }
}

//미허용 태그 체크 <style <body <head <style 열거나 닫는 태그 포함여부)
//ParamValidate.isIncludeNotPermissionTag(str)  => return true| false
ParamValidate.isIncludeNotPermissionTag = function(str) {
	return (/<(body|head|script|style|\/body|\/head|\/script|\/style)+/).test(str);
};


