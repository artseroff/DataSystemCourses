<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Добавление курса</title>
</head>
<style type="text/css">
#descriptionMenuActiveButton {
	background: #6633FF;
}
#description {
	resize: none;
	width: 643px;
	height: 150px;
}
.smallP {
	height: 10px;
}
</style>
<script>
  	function checkDates(obj) {
    	if (obj.startDate.value > obj.endDate.value) {
    		document.getElementById('errorMessage').textContent = 'Начальная дата не может быть больше конечной!';
    		return false;
    	} else if (obj.startDate.valueAsDate <= new Date()) {
    		document.getElementById('errorMessage').textContent = 'Начальная дата должна быть больше сегодняшней!';
    		return false;
    	}
  	}
  	function clearErrorMessage() {
  		document.getElementById('errorMessage').textContent = '';
  	}
</script>
<body>
	<jsp:include page="/jsp/headerToMainPage.jsp"></jsp:include>
	<main>
		<form method="POST" action="/01-08-Serov-Torzhkova-2021-12-15/controller" onsubmit="return checkDates(this)">
			<input type="hidden" name="command" value="add_course" />
			<p>
				Название курса:
				<input type="text" name="name" oninput="clearErrorMessage()" maxlength="50" style="width: 500px;" required value="${course.name}"/>
			</p>
			<p class="smallP">Описание:</p>
			<textarea id="description" name="description" oninput="clearErrorMessage()" maxlength="500">${course.description}</textarea>
			<p>
				<p class="smallP">Курс проводится:</p>
				с  <input type="date" name="startDate" oninput="clearErrorMessage()" required value="${course.startDate}"/>
				по <input type="date" name="endDate" oninput="clearErrorMessage()" required value="${course.endDate}"/>
			</p>
			<p>
				Максимальное количество студентов:
				<input type="number" name="maxStudentsAmount" oninput="clearErrorMessage()" min="10" style="width: 68px;" required value="${course.maxStudentsAmount}"/>
			</p>			
			<p id="errorMessage" style="color: red;">${errorMessage}</p>
			<input type="submit" value="Добавить"  ${disableElement}/>	
		</form>
	</main>
</body>
</html>