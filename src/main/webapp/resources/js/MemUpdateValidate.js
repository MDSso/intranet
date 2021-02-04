

function modal(label,id,elid){
	var zIndex = 9999;
    var modal = document.getElementById(id);
	var elementid = document.getElementById(elid);
	var modaltext = document.getElementById("modal_text");
	
	
    // 모달 div 뒤에 희끄무레한 레이어

	modaltext.innerHTML= label;
    var bg = document.createElement('div');
    bg.setStyle({
        position: 'fixed',
        zIndex: zIndex,
        left: '0px',
        top: '0px',
        width: '100%',
        height: '100%',
        overflow: 'auto',
        // 레이어 색갈은 여기서 바꾸면 됨
        backgroundColor: 'rgba(0,0,0,0.4)'
    });
    document.body.append(bg);
	
	modal.querySelector('.close_btn').addEventListener('click', function() {
        bg.remove();
        modal.style.display = 'none';
		elementid.focus();
		elementid = "";
    });

	modal.setStyle({
        position: 'fixed',
        display: 'block',
        boxShadow: '0 4px 8px 0 rgba(0, 0, 0, 0.2), 0 6px 20px 0 rgba(0, 0, 0, 0.19)',

        zIndex: zIndex + 1,

        top: '50%',
        left: '50%',
        transform: 'translate(-50%, -50%)',
        msTransform: 'translate(-50%, -50%)',
        webkitTransform: 'translate(-50%, -50%)'
    });

	modal.querySelector('.close_btn').focus();
}

Element.prototype.setStyle = function(styles) {
    for (var k in styles) this.style[k] = styles[k];
    return this;
};





function validate(formid) {
	   var re = /^[a-zA-Z0-9]{3,15}$/; //영어 숫자 3~15자
       var re1 = /^[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*@[0-9a-zA-Z]([-_\.]?[0-9a-zA-Z])*\.[a-zA-Z]{2,3}$/i; //이메일 정규식
	   var re2 = /^[가-힣]{2,6}$/ ; //이름
	   var re3 = /^.*(?=^.{8,20}$)(?=.*\d)(?=.*[a-zA-Z])(?=.*[!@#$%^&+=]).*$/; //특수문자 /숫자 /문자 포함 8~20자 
	    

       var userName = document.getElementById("userName");
	   var etDate = document.getElementById("date1");
	   var zipCode = document.getElementById("zipcode");
       var dtAddress = document.getElementById("dtadd");
       var memMail = document.getElementById("memMail");
	   var formID = document.getElementById(formid);

       // ------------ 이메일 까지 -----------
		
       if(!re2.test(userName.value)) {
		   modal("이름은 한글 2~6자로 입력해 주세요.","one_btn_modal","userName");
           return false;
       }
	  
	   if(etDate.value == ""){
		  modal("입사일을 입력해 주세요.","one_btn_modal","date1");
		  return false;
		}
		
		if(zipCode.value == ""){
		  modal("주소를 입력해 주세요.","one_btn_modal","zipcode");
		  return false;
		}
		
		if(dtAddress.value == ""){
		  modal("상세 주소를 입력해 주세요.","one_btn_modal","dtadd");
		  return false;
		}
		
		if(!re1.test(memMail.value)){
			modal("이메일 형식이 바르지 않습니다.","one_btn_modal","memMail");
			return false;
		}
		
		formID.submit();
   }

