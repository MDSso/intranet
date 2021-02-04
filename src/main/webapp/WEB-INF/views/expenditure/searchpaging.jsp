<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style type="text/css">
.paging {
	display: block; 
	text-align: center;
	width:100%;
	margin-top:18px;
}
a{
	color:#000000;
	text-decoration:none;
}
</style>
</head>
<body>
<div style="display: block; text-align: center;" class="paging">		
		<c:if test="${paging.startPage != 1 }">
			<a href="${cp}/expenditure/epsearch?nowPage=${paging.startPage - 1 }&start=${searchobj.start}&end=${searchobj.end}">&lt;</a>
		</c:if>
		<c:forEach begin="${paging.startPage}" end="${paging.endPage}" var="p">
			<c:choose>
				<c:when test="${p == paging.nowPage }">
					<b>${p }</b>
				</c:when>
				<c:when test="${p != paging.nowPage }">
					<a href="${cp}/expenditure/epsearch?nowPage=${p }&start=${searchobj.start}&end=${searchobj.end}">${p }</a>	
				</c:when>
			</c:choose>
		</c:forEach>
		<c:if test="${paging.endPage != paging.lastPage}">
			<a href="${cp}/expenditure/epsearch?nowPage=${paging.endPage+1 }&start=${searchobj.start}&end=${searchobj.end}">&gt;</a>
		</c:if>
		</div>
</body>
</html>