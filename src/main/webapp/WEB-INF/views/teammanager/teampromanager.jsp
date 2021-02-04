<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="${cp}/resources/css/protable.css" />
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
		<li ><a href="${cp}/teammanager/attmanager">팀출석 관리</a></li>
		<li style="background:#D5D5D5;"><a href="${cp}/teammanager/promanager">팀자산 관리</a></li>
	</ul>
</nav>
</div>
	<form id="prosearch" action="${cp}/teammanager/prosearch"> 
		<div class="search-area">
			<table>
			<tr> 
			<td>제품명</td>
			<td><input name="proName"></td>
			</tr>
			</table>
			<table>
			<tr> 
			<td>시리얼넘버</td>
			<td><input name="serialNum"></td>
			</tr>
			</table>
			<table>
			<tr> 
			<td>팀명</td>
			<td><input name="team"></td>
			</tr>
			</table>
		</div> 
		<div class="button-area">
		<button form="prosearch">조회</button>
		</div>
	</form>
	<table class="list-area">
		<tr> 
			<th>No.</th>
			<th>이미지</th>
			<th>제품명</th>
			<th>시리얼넘버</th>
			<th>팀</th>
			<th>등록 날짜</th>
		</tr>
		<tbody>
			<c:forEach items="${propertyList}" var="property">
				<tr> 
					<td>${property.pronum}</td>
					<td><img src="../resources/img/${property.imgpath}" width="50px" height="50px"/></td>
					<td><a href="${cp}/property/prodetail?idx=${property.pronum}"><b>${property.proName}</b></a></td>
					<td>${property.serialNum}</td>
					<td>${property.team}</td>
					<td>${property.purcday}</td>
				</tr>				
			</c:forEach>
		</tbody>
	</table>	
	<div class="button-area">
	<button onclick="location.href='${cp}/property/rgproperty'">등록</button>
	</div>
	<c:choose> 
		<c:when test="${!empty searchobj}">
		<%@ include file="./prosearchpaging.jsp" %>
		</c:when>
		<c:otherwise>
		<%@ include file="./proallpaging.jsp" %>
		</c:otherwise>
	</c:choose>		
	</div>
</body>
</html>