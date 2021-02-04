<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<!-- <link rel="stylesheet" type="text/css" href="${cp}/resources/css/nvbar.css" /> -->
<style type="text/css">
body { 
	margin: 0;
	padding:0; 
}
.navdiv{
	overflow: auto;
	background: #F6F6F6;
	border-bottom:1px solid #BDBDBD;
	padding:0px 20px 0px 0px;
}
.navbar{
overflow: auto;
}

.navbar>ul {
	float:right;
    background: #F6F6F6;
    margin: 0;
    padding: 0;
    list-style: none;
  }
.navbar>ul>li {
	font-size:12px;
    display: inline-block;
    padding: 10px 20px;
    font-family:'Malgun Gothic';
  }
.navbar>ul>li>a {
    display: inline-block;
    text-decoration: none;
   	margin-left:5px;
    font-weight:600;
    color:#000000;
	text-decoration:none;
  }
.navbar>ul>li:hover { background: #5D5D5D; }

</style>
<script src="https://use.fontawesome.com/releases/v5.2.0/js/all.js"></script>
<meta charset="UTF-8">
<title>top</title>
</head>
<body>
<div class="navdiv">
<nav class="navbar">
  <ul>
  	<li><a href="${cp}/attendance/attendanceMain">출석관리</a></li>
  	<li><a href="${cp}/expenditure/epmanager">지출결의</a></li>
    <li><a href="${cp}/property/propertylist">자산관리</a></li>
    <li><a href="${cp}/teammanager/attmanager">팀관리</a></li>
    <li><a href="${cp}/member/usermanager">유저 관리</a></li>
  </ul>
</nav>
</div>
</body>
</html>