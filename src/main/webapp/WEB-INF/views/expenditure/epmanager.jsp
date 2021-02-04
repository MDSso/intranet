<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
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
<%@ include file="../common/navigationbar.jsp"%>
<div class="frame">
<div class="title">지출관리</div>
<form id="search" action="${cp}/expenditure/epsearch">
	<div class="search-area">
		<table>
			<tr>
				<td>조회기간</td>
				<td><input id="date1" name="start"></td>
			</tr>
		</table>
		<table>
			<tr>
				<td><input id="date2" name="end"></td>
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
		<th>총액</th>
	</tr>
	<tbody>
			<c:forEach items="${eplist}" var="eplist"> 
			<tr onclick="location.href='${cp}/expenditure/detail?idx=${eplist.idx}'" > 
				<td>${eplist.userName}</td>
				<td>${fn:substring(eplist.rgDate,0,10)}</td>
				<td>${eplist.tprice }원</td>
			</tr>				
		</c:forEach>
	</tbody>
</table>
<div class="button-area">
<button onclick="location.href='${cp}/expenditure/rgexpenditure'">등록</button>
</div>
<c:choose> 
		<c:when test="${!empty searchobj}">
		<%@ include file="./searchpaging.jsp" %>
		</c:when>
		<c:otherwise>
		<%@ include file="./listallpaging.jsp" %>
		</c:otherwise>
	</c:choose>
</div>
</body>
</html>