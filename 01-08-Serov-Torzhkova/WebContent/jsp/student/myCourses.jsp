<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Мои курсы</title>
</head>
<style type="text/css">
hr{
	width: 1115px;
	float: left;
}
hr:last-child{
	width: 0px;
}
h2{
	margin: 0;
}
#myCoursesMenuActiveButton {
	background: #6633FF;
}
</style>
<body>
	<jsp:include page="/jsp/header.jsp"></jsp:include>
	<main class = mainGrid>
		<jsp:include page="/jsp/student/coursesMenu.jsp"></jsp:include>
		<div>		
			<h2>Мои курсы</h2>
			<c:forEach var="course" items="${listCourses}">	
				<br />
				<br />
				<div class="gridCourseInfo">					
					<div class="courseName">
						<form method="POST" action="/01-08-Serov-Torzhkova-2021-12-15/controller">
							<Button type="submit" class="linkButton courseNameButton" >${course.name}</Button>
							<input type="hidden" name="courseId" value="${course.id}"/>
							<input type="hidden" name="command" value="show_course_description"/>
						</form>
					</div>
					<div>Преподаватель: ${course.teacher.fullName}</div>
					<div class="courseDescriptionStudent">${course.description}</div>
					<div>Проводится с ${course.startDate} по ${course.endDate}</div>
					<div>Максимальное количество учащихся: ${course.maxStudentsAmount}</div>
					<div></div>
				</div>
				<br>
				<hr />
			</c:forEach>
		</div>
	</main>
</body>
</html>
