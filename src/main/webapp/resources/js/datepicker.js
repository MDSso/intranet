//<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />
//<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
//<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>

$(function() {
    $( "#date1" ).datepicker({
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

$(function() {
    $( "#date2" ).datepicker({
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