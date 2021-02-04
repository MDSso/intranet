<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="${cp}/resources/css/prodetail.css" />
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="../common/navigationbar.jsp" flush="true" />
<div class="root-div">
	<div class="title">유저 상세</div>
	<div class="info-div">
	<Table>
	
	<tr>
	<td class="name-td">이름</td>
	<td>${member.userName}</td>
	</tr>
	
	<tr>
	<td class="name-td">직책</td>
	<td>${member.memPo}</td>
	</tr>
	
	<tr>
	<td class="name-td">연락처</td>
	<td>${member.phoneNum}</td>
	</tr>
	
	<tr>
	<td class="name-td">소속팀</td>
	<td>${member.userTeam}</td>
	</tr>
	
	<tr>
	<td class="name-td">거주지</td>
	<td>
		${member.zipCode}<br>
		${member.snAddress}<br>
		${member.dtAddress}
	</td>
	</tr>
	
	<tr>
	<td class="name-td">메일</td>
	<td>${member.memMail}</td>
	</tr>
	
	<tr>
	<td class="name-td">등급</td>
	<td>${member.memGd}</td>
	</tr>
	
	<tr>
	<td class="name-td">입사일</td>
	<td>${member.etDate}</td>
	</tr>
	</Table>
	</div>
	
	<div class="bottom-btn">
	<button type="button" onclick="location.href='${cp}/member/memremove?idx=${member.idx}'">삭제</button>
	<button type="button" onclick="location.href='${cp}/member/memupdate?idx=${member.idx}'">수정</button>
	</div>
</div>
<%@ include file="../common/twobtnModal.jsp" %>
</body>
</html>