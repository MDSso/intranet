var i=0; 
//' + i + '
function addRow() {
	if(i==0){
		i=1;
	}
    $('#input_table > tbody:last').append('<tr><td><input id="date' + i + '" name="ExpenditureVoList[' + i + '].useDate" class="test1" /></td>' +
'<td><input name="ExpenditureVoList[' + i + '].wtUse"/></td>' +
'<td><input name="ExpenditureVoList[' + i + '].price"/></td>' +
'<td><input name="ExpenditureVoList['+ i + '].poUse"/></td></tr>');  
	i++; 
  };

$(document).on("focus",".test1",function(){
        
       $(this).datepicker({
  		dayNamesMin: ['월', '화', '수', '목', '금', '토', '일'],
		changeMonth: true,
  		changeYear: true,
		dateFormat: 'yy-mm-dd',
		minDate: '-30y',
		maxDate: '0',
		yearRange: 'c-30',
		showMonthAfterYear: true ,
  		monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월']
    });

});

$(document).on("click","button[id=delete_row]",function(){
        if(i>1){
		i--;
        $('#input_table > tbody:last > tr:last').remove(); //tr 테그 삭제
		}	
		else{
			alert('최소 한개의 Row는 필수입니다.')
		}
    });

$(function() {
    $( "#date" ).datepicker({
  		dayNamesMin: ['월', '화', '수', '목', '금', '토', '일'],
		changeMonth: true,
  		changeYear: true,
		dateFormat: 'yy-mm-dd',
		minDate: '-30y',
		maxDate: '0',
		yearRange: 'c-30',
		showMonthAfterYear: true ,
  		monthNamesShort: ['1월','2월','3월','4월','5월','6월','7월','8월','9월','10월','11월','12월']
    });
});