<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="${cp}/resources/css/epdetail.css" />
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="../common/navigationbar.jsp" flush="true" />
<div class="root-div">
	<div class="title">지출결의 상세</div>
	<div class="info-div">
	<table class="input_table">
	<tr>
		<th>날짜</th>
		<th>사용처</th>
		<th>금액</th>
		<th>사용목적</th>
	</tr>
	<tbody>
			<c:forEach items="${expenditure}" var="expenditure"> 
			<tr> 
				<td>${fn:substring(expenditure.useDate,0,10)}</td>
				<td>${expenditure.wtUse}</td>
				<td>${expenditure.price}</td>
				<td>${expenditure.poUse}</td>
			</tr>				
		</c:forEach>
	</tbody>
	</table>
	</div>
	<div class="bottom-btn">
	<button type="button" onclick="location.href='${cp}/expenditure/excel?idx=${eplist.idx}'">엑셀</button>
	<button type="button" onclick="location.href='${cp}/expenditure/epremove?idx=${eplist.idx}'">삭제</button>
	<button type="button" onclick="location.href='${cp}/expenditure/epupdate?idx=${eplist.idx}'">수정</button>
	</div>
</div>
</body>
</html>