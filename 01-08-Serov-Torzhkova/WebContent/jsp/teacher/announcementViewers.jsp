<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Посмотревшие объявление</title>
</head>
<style type="text/css">
#announcementsMenuActiveButton {
	background: #6633FF;
}
</style>
<body>
	<jsp:include page="/jsp/headerToMainPage.jsp"></jsp:include>
	<main class = mainGrid>
		<jsp:include page="/jsp/teacher/courseMenu.jsp"></jsp:include>
		<div>
			<form method="POST" action="/01-08-Serov-Torzhkova-2021-12-15/controller">
				<button class="linkButton courseNameButton" type="submit">Вернуться к объявлениям</button>
				<input type="hidden" name="courseId" value="${course.id}" /> 
				<input type="hidden" name="command" value="show_announcements" />
			</form>
			<h2>Объявление</h2>
			<p>${announcement.description}</p>
			<p style="font-size: 14px;">Дата: ${announcement.date}</p>
			<h2>Посмотревшие</h2>
			<c:choose>
				<c:when test="${announcement.viewers.size() == 0}">
					<p>Пока никто не посмотрел это объявление</p>
				</c:when>
				<c:otherwise>
					<c:forEach var="viewer" items="${announcement.viewers}" varStatus="status">
						<p>${status.count}. ${viewer.fullName}</p>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</div>
	</main>
</body>
</html>