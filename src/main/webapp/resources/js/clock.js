function m_clock(){
	var date = new Date(); 

	 // 그 지역의 날짜 (locale date). 
	 var dateString = date .toLocaleDateString(); 

	 // 그 지역의 시간 (locale time). 
	 var timeString = date .toLocaleTimeString(); 

	 var text = dateString + " " + timeString; 

	 // 'text'만 저장하고, 이 함수 끝내기. 
	 return text; 
}

function get_timer(){ 
	 // 함수값 불러와서, 태그 안에 집어넣기. 
	 document.getElementById("timeInput").innerHTML = m_clock(); 
	 // 1000 밀리초(=1초) 후에, 이 함수를 실행하기 (반복 실행 효과). 
	 setTimeout( "get_timer()", 1000 ); 
}
