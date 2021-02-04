<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<link rel="stylesheet" type="text/css" href="${cp}/resources/css/login.css" />
<meta charset="UTF-8">
<title>Login</title>
</head>
<body>
	<form:form action="${cp}/member/login" method="post" commandName="member">
		<div class="login-form">
			<form:input path="memId" placeholder="ID"/>
      		<form:password path="memPw" placeholder="PassWord"/>
      		<button>login</button>
      		<form:errors path="memId" class = "message"/>
		</div>
	</form:form>
</body>
</html>