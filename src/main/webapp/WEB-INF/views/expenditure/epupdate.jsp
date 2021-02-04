<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib prefix="fn" uri="http://java.sun.com/jsp/jstl/functions"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="${cp}/resources/css/rgexpenditure.css" />
<link rel="stylesheet" href="http://code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" type="text/css" />
<script src="//code.jquery.com/jquery.min.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body onload="seti(${fn:length(expenditure)})">
<jsp:include page="../common/navigationbar.jsp" flush="true" />
<div class="root-div">
<div class="title">지출결의 등록</div>
<div class="regform">
<form:form commandName="ExpenditureVO" id="upform" action="${cp}/expenditure/epupdateOK" >
<div class="button-area2">
	<button id="delete_row" type="button">Row-</button>
	<button id="add_row" type="button" onclick="addRow()">Row+</button> 
</div>
<table class="input_table" id="input_table">
	<tr>
		<th>날짜</th>
		<th>사용처</th>
		<th>금액</th>
		<th>사용목적</th>
	</tr>
	<tbody>
		<c:forEach items="${expenditure}" var="expenditures" varStatus="status"> 
			<tr> 
				<td><input id="date${status.index}" name="ExpenditureVoList[${status.index}].useDate" value="${fn:substring(expenditures.useDate,0,10)}" class="test1" /></td>
				<td><input name="ExpenditureVoList[${status.index}].wtUse" value="${expenditures.wtUse}"/></td>
				<td><input name="ExpenditureVoList[${status.index}].price" value="${expenditures.price}"/></td>
				<td><input name="ExpenditureVoList[${status.index}].poUse" value="${expenditures.poUse}"/></td>
			</tr>				
		</c:forEach>
	</tbody>
</table>
<input type="hidden" name="idx" value="${eplist.idx}"/>
<script src="http://ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="http://code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
<script src="${cp}/resources/js/EpUpdate.js"></script>
</form:form>
	</div>
	<div class="button-area">
	<button form="upform">저장</button>
	</div>
</div>
</body>
</html>