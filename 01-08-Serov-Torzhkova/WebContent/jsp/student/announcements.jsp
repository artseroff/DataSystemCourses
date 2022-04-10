<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Объявления</title>
</head>
<style type="text/css">
#announcementsMenuActiveButton {
	background: #6633FF;
}
#announcement {
	resize: none;
	width: 50%;
	height: 150px;
}
hr:last-child{
	width: 0px;
}
</style>
<body>
	<jsp:include page="/jsp/headerToMainPage.jsp"></jsp:include>
	<main class = mainGrid>
		<jsp:include page="/jsp/student/courseMenu.jsp"></jsp:include>
		<div>
			<h2>Объявления</h2>
			<h3>Не просмотренные</h3>
			<c:choose>
				<c:when test="${notViewedAnnouncements.size()==0}">
					<p>Объявлений нет</p>
				</c:when>
				<c:otherwise>
					<c:forEach var="announcement" items="${notViewedAnnouncements}">
						<form method="POST" action="/01-08-Serov-Torzhkova-2021-12-15/controller">
							<input type="hidden" name="command" value="check_announcement_is_viewed"/>					
							<input type="hidden" name="courseId" value="${course.id}"/>
							<input type="hidden" name="announcementId" value="${announcement.id}"/>
							<p>${announcement.description}</p>
							<p style="font-size: 14px;">Дата: ${announcement.date}</p>
							<input type="submit" value="Просмотреть" ${disableElement}/>
						</form>
						<br>
						<hr/>
					</c:forEach>
				</c:otherwise>
			</c:choose>
			<h3>Просмотренные</h3>
			<c:choose>
				<c:when test="${viewedAnnouncements.size()==0}">
					<p>Объявлений нет</p>
				</c:when>
				<c:otherwise>					
					<c:forEach var="announcement" items="${viewedAnnouncements}">
						<p>${announcement.description}</p>
						<p style="font-size: 14px;">Дата: ${announcement.date}</p>
						<hr/>
					</c:forEach>
				</c:otherwise>
			</c:choose>
		</div>
	</main>
</body>
</html>