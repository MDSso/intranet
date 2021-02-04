<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="${cp}/resources/css/register.css" />
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
<jsp:include page="../common/navigationbar.jsp" flush="true" />
<div class="root-div">
<div class="title">출석 수정</div>
<div class="regform">
<form id="upform" action="${cp}/teammanager/attupdateOK" method="post" >
			<table>
			<tr>
			<td>사용자</td>
			<td>${att.userName}</td>
			</tr>
			<tr>
			<td>날짜</td>
      		<td>${att.cDate}</td>
      		</tr>
      		<tr>
      		<td>상태</td>
      		<td>
      			<select name="status">  
				<option value="정상출근" <c:if test="${att.status eq '정상출근'}">selected</c:if>>정상출근</option>
				<option value="연차" <c:if test="${att.status eq '연차'}">selected</c:if>>연차</option>
				<option value="공가" <c:if test="${att.status eq '공가'}">selected</c:if>>공가</option>
				<option value="병가" <c:if test="${att.status eq '병가'}">selected</c:if>>병가</option>
				<option value="결근" <c:if test="${att.status eq '결근'}">selected</c:if>>결근</option>
				</select> 
			</td>
			</tr>
			<tr>
			<td>사유</td>
      		<td><input name="reason" value="${att.reason}"/></td>
      		</tr>
      		</table>
      		<input type="hidden" name="memId" value="${att.memId}"/>
      		<input type="hidden" name="cDate" value="${att.cDate}"/>
      		</form>
	</div>
	<div class="button-area">
	<button form="upform">저장</button>
	</div>
</div>
</body>
</html>