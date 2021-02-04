<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<script type="text/javascript"
	src="https://cdnjs.cloudflare.com/ajax/libs/Chart.js/2.1.6/Chart.bundle.min.js"></script>
<link rel="stylesheet" href="//code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" />
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="//code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
<script type="text/javascript" src="${cp}/resources/js/datepicker.js"></script>
<link rel="stylesheet" type="text/css" href="${cp}/resources/css/protable.css" />
<script src="${cp}/resources/js/clock.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>

</head>
<body onload="get_timer()">
<%@ include file="../common/navigationbar.jsp"%>
<div class="frame">
		<div class="title">출석 관리</div>
		<div class="attendance-area">
			<div class="clock" id="timeInput"></div>
			<div class="attendance-button">
				<c:choose>
					<c:when test="${empty lastondate}">
						<button class="onoff"
							onclick="location.href='${cp}/attendance/onwork'">출근</button>
					</c:when>
					<c:otherwise>
						<button class="already-on">출근</button>
					</c:otherwise>
				</c:choose>
				<c:choose>
					<c:when test="${empty lastondate || !empty lastoffdate}">
						<button class="already-on">퇴근</button>
					</c:when>
					<c:otherwise>
						<button class="onoff"
							onclick="location.href='${cp}/attendance/offwork'">퇴근</button>
					</c:otherwise>
				</c:choose>
			</div>
		</div>
		<form id="search" action="${cp}/attendance/attSearch">
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
				<th>출근시간</th>
				<th>퇴근시간</th>
				<th>상태</th>
			</tr>
			<tbody>
				<c:forEach items="${attendances}" var="attendance">
					<tr>
						<td>${attendance.userName}</td>
						<td>${attendance.cDate}</td>
						<td>${fn:substring(attendance.onWork,10,19)}</td>
						<td>${fn:substring(attendance.offWork,10,19)}</td>
						<td>${attendance.status}</td>
					</tr>
				</c:forEach>
			</tbody>
		</table>
		<c:choose>
			<c:when test="${!empty searchobj}">
				<%@ include file="./searchpaging.jsp"%>
			</c:when>
			<c:otherwise>
				<%@ include file="./listallpaging.jsp"%>
			</c:otherwise>
		</c:choose>
	</div>
</body>
</html>
