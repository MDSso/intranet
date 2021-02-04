<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="${cp}/resources/css/register.css" />
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="//code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" />
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="//code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
<script type="text/javascript" src="${cp}/resources/js/datepicker.js"></script>
<script src="${cp}/resources/js/address.js"></script>
<script src="${cp}/resources/js/AutoHypenPhone.js"></script>
<script src="${cp}/resources/js/MemUpdateValidate.js"></script>
</head>
<body>
<jsp:include page="../common/navigationbar.jsp" flush="true" />
	<div class="root-div">
	<div class="title">유저 수정</div>
	<div class="regform">
	<form onsubmit="return false;" id="memupdate" action="${cp}/member/memupdateOK">
	<table>
	<tr>
	<td class="name-td">이름</td>
	<td><input id="userName" value="${member.userName}" name="userName"></td>
	</tr>
	<tr>
	<td class="name-td">직책</td>
	<td><select name="memPo">  
				<option value="사원">사원</option>
				<option value="주임">주임</option>
				<option value="대리">대리</option>
				<option value="과장">과장</option>
				<option value="차장">차장</option>
				<option value="부장">부장</option>
			</select></td>
	</tr>
	<tr>
	<td class="name-td">연락처</td>
	<td><input value="${member.phoneNum}" onkeyup="inputPhoneNumber(this)" name="phoneNum" type="text" maxlength="13"></td>
	</tr>
	<tr>
	<td class="name-td">소속팀</td>
	<td>
			<select name="userTeam"> 
			<c:forEach items="${team}" var="team">
			<c:choose>
			<c:when test="${member.userTeam eq team.teamName}"> 
			<option value="${team.teamName}" selected>${team.teamName}</option>
			</c:when>
			<c:otherwise>
			<option value="${team.teamName}">${team.teamName}</option>
			</c:otherwise>
			</c:choose>
			</c:forEach>
			</select>
	</td>
	</tr>
	<tr>
	<td class="name-td">거주지</td>
	<td>
		<input id="zipcode" value="${member.zipCode}" name="zipCode" readonly><button onclick="daumZipCode()">주소찾기</button><br>
		<input id="snadd" value="${member.snAddress}" name="snAddress" readonly><br>
		<input id="dtadd" value="${member.dtAddress}" name="dtAddress">
	</td>
	</tr>
	
	<tr>
	<td class="name-td">메일</td>
	<td><input id="memMail" value="${member.memMail}" name="memMail"></td>
	</tr>
	
	<tr>
	<td class="name-td">등급</td>
	<td>
		<select name="memGd">
		<c:forEach var="i" begin="1" end="5">
			<c:choose>
			<c:when test="${member.memGd eq i}"> 
			<option value="${i}" selected>${i}</option>
			</c:when>
			<c:otherwise>
			<option value="${i}">${i}</option>
			</c:otherwise>
			</c:choose>
		</c:forEach>
		</select>
	</td>
	</tr>
	
	<tr>
	<td class="name-td">입사일</td>
	<td><input value="${member.etDate}" name="etDate" id="date1" readonly></td>
	</tr>
	</Table>
		<input type="hidden" name="idx" value="${member.idx}">
	</form>
	</div>
	
	<div class="button-area">
	<button type="button" onclick="location.href='${cp}/member/usermanager'">취소</button>
	<button type="button" class="bottom-btn" onclick='validate("memupdate")'>저장</button>
	</div>
</div>
<%@ include file="../common/onebtnModal.jsp" %>
</body>
</html>