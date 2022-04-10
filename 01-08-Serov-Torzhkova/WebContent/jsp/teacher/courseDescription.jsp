<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Курс "${course.name}"</title>
</head>
<style type="text/css">
#descriptionMenuActiveButton {
	background: #6633FF;
}
</style>
<body>
	<jsp:include page="/jsp/headerToMainPage.jsp"></jsp:include>
	<main class = mainGrid>
		<jsp:include page="/jsp/teacher/courseMenu.jsp"></jsp:include>
		<div>
			<div class="gridCourseInfo">
				<div class="courseName">
					<h2>Курс "${course.name}"</h2>
				</div>
				<div>Проводится с ${course.startDate} по ${course.endDate}</div>
				<div class="courseDescription">${course.description}</div>
				<div>Максимальное количество учащихся: ${course.maxStudentsAmount}</div>
				<div></div>
			</div>
		</div>
	</main>
</body>
</html>
