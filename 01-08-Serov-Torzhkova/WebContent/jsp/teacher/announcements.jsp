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
		<jsp:include page="/jsp/teacher/courseMenu.jsp"></jsp:include>
		<div>
			<p>Добавить объявление</p>
			<form method="POST" action="/01-08-Serov-Torzhkova-2021-12-15/controller">			
				<input type="hidden" name="command" value="add_announcement" />
				<p><textarea id="announcement" name="description"></textarea></p>
				<input type="hidden" name="courseId" value="${course.id}" />
				<input type="submit" value="Добавить" ${disableElement}/>
				<br/>
			</form>
			<h2>Объявления</h2>
			<c:forEach var="announcement" items="${course.announcements}">
				<form method="POST" action="/01-08-Serov-Torzhkova-2021-12-15/controller">
					<input type="hidden" name="command" value="delete_announcement"/>
					<input type="hidden" name="courseId" value="${course.id}"/>
					<input type="hidden" name="announcementId" value="${announcement.id}"/>
					
					<p>
						
						${announcement.description}
						<c:if test="${!disableElement.equals('disabled')}">
							<button type="submit" class="iconButton">
								<img src="${pageContext.request.contextPath}/resources/icons/delete.png"
								width="20" height="20" border="0" title="Удалить" data-toggle="tooltip">
							</button>
						</c:if>
					</p>
					
				</form>
				<p style="font-size: 14px;">Дата: ${announcement.date}</p>
				<form method="POST" action="/01-08-Serov-Torzhkova-2021-12-15/controller">
					<input type="hidden" name="command" value="show_announcement_viewers" />
					<input type="hidden" name="courseId" value="${course.id}"/>
					<input type="hidden" name="announcementId" value="${announcement.id}"/>
					<p style="font-size: 14px;">
						Количество посмотревших: ${announcement.viewers.size()}
						<button class="linkButton courseNameButton" style="font-size: 14px;">(посмотреть список)</button>
					</p>
				</form>
				<hr/>
			</c:forEach>
		</div>
	</main>
</body>
</html>