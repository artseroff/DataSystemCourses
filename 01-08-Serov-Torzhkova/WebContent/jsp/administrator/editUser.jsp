<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Редактирование пользователя</title>
</head>
<script>
  	function clearErrorMessage() {
  		document.getElementById('existedLogin').textContent = '';
  	}
</script>
<body>
	<jsp:include page="/jsp/headerToMainPage.jsp"></jsp:include>
	<main>
		<form method="POST" action="/01-08-Serov-Torzhkova-2021-12-15/controller">
			<input type="hidden" name="command" value="edit_user" /> 
			<input type="hidden" name="oldLogin" value="${oldLogin}" /> 
			<input type="hidden" name="editedUserId" value="${editedUserId}" />
			Логин:<br>
			<input type="text" oninput="clearErrorMessage()" name="editedUserLogin" value="${editedUserLogin}" required="required"/> <br /> <br />
			Пароль:<br>
			<input type="text" name="editedUserPassword" value="${editedUserPassword}" required="required" />
			<br> <br>
			ФИО:<br>
			<input type="text" name="editedUserFullName" value="${editedUserFullName}" required="required" size="50px"/>
			<br>
			<p id="existedLogin" style="color: red;">${existedLogin}</p>
			<input type="submit" value="Сохранить изменения" ${disableElement}/>
		</form>
	</main>
</body>
</html>