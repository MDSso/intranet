<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<style>
.modal {
		display:none;
    	box-sizing: border-box;
        width: 300px;
        height: 200px;
        padding: 10px 10px;
        border: 1px solid #000000;
        border-radius: 5px;
        background-color:white;
    }
    
.modal div:first-child{
		margin:10px auto;
		height:110px;
    	text-aline:center;
 }

 .btn-area{
 	margin:0px auto;
	box-sizing: border-box;
	padding:4px;
	width: intrinsic;
	
 }
  .btn-area button:first-child{
	margin-right:5px;
	
 }
 
 .btn {
 		display:inline-block;
 		margin:0px;
        border:1px solid #000000;
		width: 130px;
		height:50px;
		font-size:12px;
		background-color:white;
		border-radius:5px;
    }
</style>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<div id="two_btn_modal" class="modal">
	<div id="modal_text">
	
	</div>
	<div class = "btn-area">
	<button id="close_btn" class="btn"> 닫기 </button>
	<button id="confirm_btn" class="btn"> 확인 </button>
	</div>
</div>
</body>
</html>