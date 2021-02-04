<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="${cp}/resources/css/register.css" />
<meta charset="UTF-8">
<title>Property Update</title>
</head>
<body>
<body>
<jsp:include page="../common/navigationbar.jsp" flush="true" />
<div class="root-div">
<div class="title">자산 등록</div>
<div class="regform">
<form:form id="upform" action="${cp}/property/proupdateok" method="post" commandName="PropertyVO" enctype="multipart/form-data">
			<table>
			<tr>
			<td>제품명</td>
			<td><form:input path="proName" value="${PropertyVO.proName}"/></td>
			</tr>
			<tr>
			<td>시리얼 넘버</td>
      		<td><form:input path="serialNum" value="${PropertyVO.serialNum}"/></td>
      		</tr>
      		<tr>
      		<td>소유팀</td>
      		<td>
			<form:select path="Team"> 
			<c:forEach items="${teams}" var="team">
			<c:choose>
			<c:when test="${PropertyVO.team eq team.teamName}"> 
			<option value="${team.teamName}" selected>${team.teamName}</option>
			</c:when>
			<c:otherwise>
			<option value="${team.teamName}">${team.teamName}</option>
			</c:otherwise>
			</c:choose>
			</c:forEach>
			</form:select>
			</td>
			</tr>
			<tr>
			<td>이미지</td>
      		<td><input type="file" name="file"/></td>
      		</tr>
      		</table>
      		<input type="hidden" name="idx" value="${PropertyVO.pronum}"/>
			<input type="hidden" name="imgpath" value="${PropertyVO.imgpath}"/>
      		</form:form>
	</div>
	<div class="button-area">
	<button form="upform">저장</button>
	</div>
</div>
</body>
</html>