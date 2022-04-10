<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Журнал</title>
</head>
<style type="text/css">
#journalMenuActiveButton {
	background: #6633FF;
}
.centered {
	text-align: center;
}
</style>
<body>
	<jsp:include page="/jsp/headerToMainPage.jsp"></jsp:include>
	<main class = "mainGrid">
		<jsp:include page="/jsp/student/courseMenu.jsp"></jsp:include>
		<div>
			<div class="scrollContainer">
				<table>
					<caption>Журнал</caption>
					<tr>
						<th></th>
						<th>Студенты</th>
						<c:forEach var="lessonHeader" items="${course.lessons}" varStatus="statusLessonHeader">
							<th>
								<div class="vertical-text"><div class="vertical-text__inner">${lessonHeader.date}</div></div>
							</th>
						</c:forEach>
						<th>Итоговые оценки</th>
					</tr>
					<c:choose>
						<c:when test="${course.students.size()==0}">
							<td style="height: 15px;"></td>
							<td></td>
							<td></td>
						</c:when>
						<c:otherwise>
							<c:forEach var="student" items="${course.students}"	varStatus="statusStudent">
								<tr>
									<c:set var="finalGradeStudent" scope="page" value="${course.getFinalGradeStudentWithId(student.id)}"/>
									<c:if test="${!finalGradeStudent.equals(null)}">
										<c:set var="valueFinalGradeStudent" scope="page" value="${finalGradeStudent.getGrade().getName()}"/>
									</c:if>								
									<td>${statusStudent.count}</td>
									<td>
										${student.fullName}
										<input type="hidden" name="student${statusStudent.count}" value="${student.id}" />
									</td>
									<c:forEach var="lesson" items="${course.lessons}" varStatus="statusLesson">
										<c:choose>
											<c:when test="${ lesson.isAbsentStudentWithId(student.id) }">
												<td class="centered">н</td>
											</c:when>
											<c:otherwise>
												<td class="centered">${lesson.getStudentWithIdGradeOrNull(student.id)}</td>
											</c:otherwise>	
										</c:choose>
									</c:forEach>
									<td class="centered">${valueFinalGradeStudent}</td>
									<c:remove var="finalGradeStudent"/>
									<c:remove var="valueFinalGradeStudent"/>
								</tr>
							</c:forEach>
						</c:otherwise>
					</c:choose>
				</table>
			</div>
			<label style="font-size: 14px;">* в ячейках таблицы записано "н", если студент отсутствовал на занятии, число от 2 до 5 – если получил оценку</label>
			<br>
			<br>
			<c:if test="${course.lessons.size()==0}">
				<label style="color: red"> Занятий пока не было </label>
			</c:if>
		</div>		
	</main>
</body>
</html>