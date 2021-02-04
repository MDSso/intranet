<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="${cp}/resources/css/register.css" />
<link rel="stylesheet" href="//code.jquery.com/ui/1.8.18/themes/base/jquery-ui.css" />
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.7.1/jquery.min.js"></script>
<script src="//code.jquery.com/ui/1.8.18/jquery-ui.min.js"></script>
<script type="text/javascript" src="${cp}/resources/js/datepicker.js"></script>
<script src="${cp}/resources/js/address.js"></script>
<script src="${cp}/resources/js/AutoHypenPhone.js"></script>
<script src="${cp}/resources/js/MemberValidate.js"></script>
<script>
function idCheck(id){
	var mem = document.getElementById(id);
	var list = new Array();
	<c:forEach items="${idList}" var="item">
	list.push("${item.memId}");
	</c:forEach>
	for (var i = 0; i < list.length; i++) {
                if (mem.value == list[i]) {
                    return true;
                }
            }
}
</script>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<%@ include file="../common/navigationbar.jsp" %>
<div class="root-div">
	<div class="title">유저 등록</div>
	<div class="regform">
	<form:form commandName="member" id="rgmember" action="${cp}/member/rgmemberOK" method="post">
	<table>
	<tr>
		<td>유저 이름</td>
		<td><input id="userName" name="userName"/>
		</td>
	</tr>
	<tr>
		<td>직책</td>
		<td>
			<select name="memPo">  
				<option value="사원">사원</option>
				<option value="주임">주임</option>
				<option value="대리">대리</option>
				<option value="과장">과장</option>
				<option value="차장">차장</option>
				<option value="부장">부장</option>
			</select>
		</td>
	</tr>
	<tr>
		<td>소속팀</td>
		<td>
			<select name="userTeam">
			<c:forEach items="${team}" var="team">
			<option value="${team.teamName}">${team.teamName}</option>
			</c:forEach>
			</select>
		</td>
	</tr>
	<tr>
		<td>입사일</td>
		<td><input name="etDate" id="date1" readonly></td>
	</tr>
	<tr>
		<td>아이디</td>
		<td><input id="memId" name="memId"></td>
	</tr>
	<tr>
		<td>비밀번호</td>
		<td><input id="memPw" name="memPw" ></td>
	</tr>
	<tr>
		<td>비밀번호 확인</td>
		<td><input id="confirmPw" name="confirmPw"></td>
	</tr>
	<tr>
		<td>연락처</td>
		<td><input onkeyup="inputPhoneNumber(this)" name="phoneNum" type="text" maxlength="13"></td>
	</tr>
	<tr>
		<td>주소</td>
		<td>
		<input name="zipCode" id= "zipcode" class="zipcode" placeholder=" *우편주소" readonly><button type="button" onclick="daumZipCode()">주소찾기</button><br>
		<input name="snAddress" id="snadd" class="snadd" placeholder=" *주소" readonly> <br>
		<input name="dtAddress" id="dtadd" class="dtadd"placeholder=" *상세 주소"> 
		</td>
	</tr>
	<tr>
		<td>이메일</td>
		<td><input id="memMail" name="memMail"></td>
	</tr>
	<tr>
		<td>유저등급</td>
		<td>
			<select name="memGd">  
				<option value="1">1</option>
				<option value="2">2</option>
				<option value="3">3</option>
				<option value="4">4</option>
				<option value="5">5</option>
			</select>
		</td>
	</tr>
	</table>
	</form:form>
	</div>
	<div class="button-area">
	<button type="button" class="bottom-btn" onclick='validate("rgmember")'>저장</button>
	</div>
</div>
<%@ include file="../common/onebtnModal.jsp" %>
</body>
</html>