<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="${cp}/resources/css/register.css" />
<script src="${cp}/resources/js/propertyValidate.js"></script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="../common/navigationbar.jsp" flush="true" />
<div class="root-div">
<div class="title">자산 등록</div>
<div class="regform">
<form:form id="rgform" action="${cp}/property/rgpropertyok" method="post" commandName="PropertyVO" enctype="multipart/form-data">
		<table>
			<tr>
			<td class="value-td">제품명</td>
			<td><form:input id="proName" path="proName"/></td>
			</tr>
			<tr>
			<td class="value-td">시리얼 넘버</td>
      		<td><form:input id="serialNum" path="serialNum"/></td>
      		</tr>
      		<tr>
      		<td class="value-td">소유팀</td>
      		<td>
			<form:select path="Team"> 
			<c:forEach items="${teams}" var="team">
			<option value="${team.teamName}">${team.teamName}</option>
			</c:forEach>
			</form:select>
			</td>
			</tr>
			<tr>
			<td class="value-td">이미지</td>
      		<td><input type="file" name="file"/></td>
      		</tr>
      	</table>
      	</form:form>
	</div>
	<div class="button-area">
	<button onclick='validate("rgform")'>저장</button>
	</div>
</div>
<%@ include file="../common/onebtnModal.jsp" %>
</body>
</html>