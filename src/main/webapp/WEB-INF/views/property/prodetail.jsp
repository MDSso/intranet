<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="${cp}/resources/css/prodetail.css" />
<meta charset="UTF-8">
<title>제품상세</title>
</head>
<body>
<jsp:include page="../common/navigationbar.jsp" flush="true" />
<div class="root-div">
	<div class="title">자산 상세</div>
	<div class="info-div">
	<Table>
	<tr>
	<td class="prodetail-td">제품명</td>
	<td>${PropertyVO.proName}</td>
	</tr>
	
	<tr>
	<td class="prodetail-td">시리얼 넘버</td>
	<td>${PropertyVO.serialNum}</td>
	</tr>
	
	<tr>
	<td class="prodetail-td">소유팀</td>
	<td>${PropertyVO.team}</td>
	</tr>
	
	<tr>
	<td class="prodetail-td">등록일</td>
	<td>${PropertyVO.purcday}</td>
	</tr>
	</Table>
	</div>
	<div class="bottom-btn">
	<button type="button" onclick="location.href='${cp}/property/proremove?idx=${PropertyVO.pronum}'">삭제</button>
	<button type="button" onclick="location.href='${cp}/property/proupdate?idx=${PropertyVO.pronum}'">수정</button>
	</div>
</div>
</body>
</html>