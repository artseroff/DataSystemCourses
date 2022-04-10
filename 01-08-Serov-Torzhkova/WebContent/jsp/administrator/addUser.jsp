<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Добавление пользователя</title>
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
		<input type="hidden" name="command" value="add_user" /> 
		Логин:<br>
		<input type="text" oninput="clearErrorMessage()" name="login" value="${login}" required/><br /><br /> 
		Пароль:<br>
		<input type="text" name="password" value="${password}" required/><br /><br /> 
		Группа:<br> 
		<c:set var="userGroup" scope="page" value="${userGroup}"/>
		<c:choose>
			<c:when test="${userGroup.equals('администратор')}">
				<c:set var="administratorSelected" scope="page" value="selected"/>
			</c:when>
			<c:when test="${userGroup.equals('модератор')}">
				<c:set var="moderatorSelected" scope="page" value="selected"/>
			</c:when>
			<c:when test="${userGroup.equals('преподаватель')}">
				<c:set var="teacherSelected" scope="page" value="selected"/>
			</c:when>
				<c:when test="${userGroup.equals('студент')}">
			<c:set var="studentSelected" scope="page" value="selected"/>
				</c:when>									
			</c:choose>
		<select name="group">
			<option value="администратор" ${administratorSelected}>администратор</option>
			<option value="модератор" ${moderatorSelected}>модератор</option>
			<option value="преподаватель" ${teacherSelected}>преподаватель</option>
			<option value="студент" ${studentSelected}>студент</option>
		</select> 
		<br>
		<br> 
		ФИО:<br> 
		<input type="text" name="fullName" value="${fullName}" required size="50px"/>
		<br>
		<p id="existedLogin" style="color: red;">${existedLogin}</p>
		<input type="submit" value="Добавить" ${disableElement}/>
	</form>
	</main>
</body>
</html>