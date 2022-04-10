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
</style>
<body>
	<jsp:include page="/jsp/header.jsp"></jsp:include>
	<main>	
		<form method="POST" action="/01-08-Serov-Torzhkova-2021-12-15/controller">
			<input type="hidden" name="command" value="show_add_course_page"/>
			<input type="submit" value="Добавить курс"  ${disableElement}/>
		</form>
		<br>
		<h2>Мои курсы</h2>
		<c:choose>
			<c:when test="${listCourses.size() == 0}">
				<p>У Вас ещё нет курсов</p>
			</c:when>
			<c:otherwise>
				<c:forEach var="course" items="${listCourses}" varStatus="status">	
					<br>
					<br>
					<div class="gridCourseInfo">
						<div class="courseName">
							<form method="POST" action="/01-08-Serov-Torzhkova-2021-12-15/controller">
								<input type="hidden" name="courseId" value="${course.id}"/>
								<input type="hidden" name="command" value="show_course_description"/>								
								<button type="submit" class="linkButton courseNameButton">${course.name}</button>
							</form>
						</div>
						<div>Проводится с ${course.startDate} по ${course.endDate}</div>
						<div class="courseDescription">${course.description}</div>
						<div>Максимальное количество учащихся: ${course.maxStudentsAmount}</div>
						<div></div>
					</div>
					<br>
					<hr />
				</c:forEach>
			</c:otherwise>
		</c:choose>
	</main>
</body>
</html>
