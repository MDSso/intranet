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
<%@ include file="../common/navigationbar.jsp" %>
<div class="frame">
<div class="title">유저 관리</div>
<form id="user-search" action="${cp}/member/membersearch">
<div class="search-area">
		<table>
			<tr>
				<td>이름</td>
				<td><input name="userName"></td>
			</tr>
		</table>
		<table>
			<tr>
				<td>팀명</td>
				<td><input name="userTeam"></td>
			</tr>
		</table>
		<table>
			<tr>
				<td>직책</td>
				<td>
					<select class="po-select" name="memPo">  
					<option value="사원">사원</option>
					<option value="주임">주임</option>
					<option value="대리">대리</option>
					<option value="과장">과장</option>
					<option value="차장">차장</option>
					<option value="부장">부장</option>
					</select>
				</td>
			</tr>
		</table>
</div>
<div class="button-area">
<button form="user-search">조회</button>
</div>
</form>
<table class="list-area">
	<tr> 
		<th>이름</th>
		<th>직책</th>
		<th>소속팀</th>
		<th>연락처</th>
		<th>입사일</th>
	</tr>
	<tbody>
		<c:forEach items="${member}" var="member"> 
			<tr> 
				<td><a href='${cp}/member/memdetail?idx=${member.idx}'><b>${member.userName}</b></a></td>
				<td>${member.memPo }</td>
				<td>${member.userTeam }</td>
				<td>${member.phoneNum }</td>
				<td>${member.etDate}</td>
			</tr>				
		</c:forEach>
	</tbody>
</table>
<div class="button-area">
<button onclick="location.href='${cp}/member/rgmember'">등록</button>
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