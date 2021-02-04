

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
	   
		var formID = document.getElementById(formid);
	   var proName = document.getElementById("proName");
       var serialNum = document.getElementById("serialNum");
  
	   if(proName.value == ""){
		  modal("제품명을 입력해 주세요.","one_btn_modal","proName");
		  return false;
		}

		if(serialNum.value == ""){
		  modal("시리얼 넘버를 입력해 주세요.","one_btn_modal","serialNum");
		  return false;
		}
		
		
		formID.submit();
   }

