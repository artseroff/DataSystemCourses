<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Другие курсы</title>
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
#otherCoursesMenuActiveButton {
	background: #6633FF;
}
</style>
<body>
<c:set var="errorNumber" scope="page" value="${errorNumber}"/>
	<jsp:include page="/jsp/header.jsp"></jsp:include>
	<main class = mainGrid>
		<jsp:include page="/jsp/student/coursesMenu.jsp"></jsp:include>
		<div>		
			<h2>Другие курсы</h2>
			<c:forEach var="course" items="${listCourses}" varStatus="status">	
				<br />
				<br />
				<div class="gridCourseInfo">
					<div class="courseName"><strong>${course.name}</strong></div>
					<div>Преподаватель: ${course.teacher.fullName}</div>
					<div class="courseDescriptionStudent">${course.description}</div>
					<div>Проводится с ${course.startDate} по ${course.endDate}</div>
					<div>Максимальное количество учащихся: ${course.maxStudentsAmount}</div>
					<div></div>
				</div>
				<form method="POST" action="/01-08-Serov-Torzhkova-2021-12-15/controller">
					<input type="hidden" name="command" value="enroll_in_course" />
					<input type="hidden" name="courseId" value="${course.id}"/>
					<input type="hidden" name="courseNumber" value="${status.count}"/>
					<input type="hidden" name="maxStudentsAmount" value="${course.maxStudentsAmount}"/>
					<div>
						<input type="submit" value="Записаться"  ${disableElement}/>
						<c:if test="${status.count == errorNumber}">
							<label style="color: red;">На курсе нет свободных мест!</label>
						</c:if>
					</div>	
				</form>
				<br />
				<hr />
			</c:forEach>
		</div>
	</main>
</body>
</html>