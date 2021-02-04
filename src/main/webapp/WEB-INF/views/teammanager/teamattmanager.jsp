<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions" %>
<html>
<head>
<link rel="stylesheet" type="text/css" href="${cp}/resources/css/protable.css" />
<link rel="stylesheet" href="//code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" />
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="//code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
<script type="text/javascript" src="${cp}/resources/js/datepicker.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<body>
<%@ include file="../common/navigationbar.jsp" %>
<div class="frame">
<div class="teammanager-title">
<nav class="title-list">
	<ul>
		<li style="background:#D5D5D5;"><a href="${cp}/teammanager/attmanager">팀출석 관리</a></li>
		<li><a href="${cp}/teammanager/promanager">팀자산 관리</a></li>
	</ul>
</nav>
</div>
<form id="search" action="${cp}/teammanager/attSearch">
<div class="search-area">
		<table>
			<tr>
				<td>조회기간</td>
				<td><input id="date1" name="start" ></td>
			</tr>
		</table>
		<table>
			<tr>
				<td><input id="date2" name="end" ></td>
			</tr>
		</table>
		<table class = "user">
			<tr>
				<td>사용자</td>
				<td><input name="userName" ></td>
			</tr>
		</table>
</div>
<div class="button-area">
<button form="search">조회</button>
</div>
</form>
	<table class="list-area">
		<tr> 
			<th>사용자</th>
			<th>날짜</th>
			<th>출근시간</th>
			<th>퇴근시간</th>
			<th>상태</th>
			<th>사유</th>
			<th>수정</th>
		</tr>
		<tbody>
			<c:forEach items="${attlist}" var="attlist">
				<tr> 
					<td>${attlist.userName}</td>
					<td>${attlist.cDate}</td>
					<td>${fn:substring(attlist.onWork,10,19)}</td>
					<td>${fn:substring(attlist.offWork,10,19)}</td>
					<td>${attlist.status}</td>
					<td>${attlist.reason}</td>
					<td><button class = "update" onclick="location.href='${cp}/teammanager/attupdate?memId=${attlist.memId}&cDate=${attlist.cDate}'">수정</button></td>
				</tr>				
			</c:forEach>
		</tbody>
	</table>
	<c:choose> 
		<c:when test="${!empty searchobj}">
		<%@ include file="./attsearchpaging.jsp" %>
		</c:when>
		<c:otherwise>
		<%@ include file="./attlistallpaging.jsp" %>
		</c:otherwise>
	</c:choose>
</div>	
</body>
</html>