<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Авторизация</title>
</head>
<style type="text/css">
<c:import url = "/style/style.css"/>
p {
	color:red;
}
</style>
<script>
  	function clearErrorMessages() {
  		document.getElementById('errorLoginPassMessage').textContent = '';
  		document.getElementById('wrongAction').textContent = '';
  		document.getElementById('nullPage').textContent = '';
  		document.getElementById('errorAlreadyAuthorized').textContent = '';
  	}
</script>
<body>
	<main>
		<form name="loginForm" method="POST" action="/01-08-Serov-Torzhkova-2021-12-15/controller">
			<input type="hidden" name="command" value="login" />
			Логин:<br />
			<input type="text" oninput="clearErrorMessages()" name="login" value="" required="required"/><br /><br />
			Пароль:<br />
			<input type="password" oninput="clearErrorMessages()" name="password" value="" required="required"/><br />
			<p id="errorLoginPassMessage">${errorLoginPassMessage}</p>
			<p id="wrongAction">${wrongAction}</p>
			<p id="nullPage">${nullPage}</p>
			<p id="errorAlreadyAuthorized"> ${errorAlreadyAuthorized}</p>
			<input type="hidden" name="method" value="send" />
			<input type="submit" value="Войти"/>
		</form>
	</main>
</body>
</html>