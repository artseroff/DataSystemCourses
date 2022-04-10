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
input[type=date] {
	margin: 10px 10px 15px 0;
}
</style>
<script type="text/javascript">
	function checkValuesPatternAttendanceOrGrade(input) {
		let regex = /[^н2-5]/;
		input.value = input.value.replace(regex, '');
	}	
	function clearErrorDateMessage() {
		document.getElementById('errorInvalidDate').innerHTML = null;
	}
</script>
<body>
	<jsp:include page="/jsp/headerToMainPage.jsp"></jsp:include>
	<main class = "mainGrid">
		<jsp:include page="/jsp/teacher/courseMenu.jsp"></jsp:include>
		<div>
			<form method="POST" action="/01-08-Serov-Torzhkova-2021-12-15/controller">
				Введите дату занятия:<br>
				<input type="date" name="lessonDate" value="${existedDate}" required="required" oninput="clearErrorDateMessage()"/>
				<label id="errorInvalidDate" style="color: red;">${errorLessonDateIsExists}${errorLessonDateNotInDatesRangeCourse}</label>
				<br>
				<input type="hidden" name="command" value="add_lesson" />
				<input type="hidden" name="method" value="send" />
				<input type="hidden" name="courseId" value="${course.id}" />
				<input type="submit" value="Добавить занятие"  ${disableElement}/>
				<br>
				<br>
			</form>
			<form method="POST" action="/01-08-Serov-Torzhkova-2021-12-15/controller" id="attendanceGradeValuesForm">
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
								<input type="hidden" name="lesson${statusLessonHeader.count}" value="${lessonHeader.id}" />
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
											<c:if test="${valueFinalGradeStudent.equals('отчислен')}">
												<c:set var="readonlyWithTooltipExpelled" scope="page" value="readonly title='Отчислен. Редактирование запрещено' data-toggle='tooltip'"/>
											</c:if>
										</c:if>								
										<td>${statusStudent.count}</td>
										<td>
											${student.fullName}
											<input type="hidden" name="student${statusStudent.count}" value="${student.id}" />
										</td>
										<c:forEach var="lesson" items="${course.lessons}" varStatus="statusLesson">
											<c:choose>
												<c:when test="${ lesson.isAbsentStudentWithId(student.id) }">
													<td align="center"><input type="text" ${readonlyWithTooltipExpelled} name="attendanceOrGrade${statusStudent.count}${statusLesson.count}" class="tableInput" value="н" oninput="checkValuesPatternAttendanceOrGrade(this)" maxlength="1"/></td>
												</c:when>
												<c:otherwise>
													<td align="center"><input type="text" ${readonlyWithTooltipExpelled} name="attendanceOrGrade${statusStudent.count}${statusLesson.count}" class="tableInput" value="${lesson.getStudentWithIdGradeOrNull(student.id)}" oninput="checkValuesPatternAttendanceOrGrade(this)" maxlength="1"/></td>
												</c:otherwise>	
											</c:choose>
											<!--<td><input type="text" pattern="[Нн2-5]{1}" class="tableInput"
											id="grade${status.count}" name="grade${status.count}" oninvalid="this.setCustomValidity('Введите н или оценку от 2 до 5')"
		  									oninput="this.setCustomValidity('')"/></td>-->
										</c:forEach>
										<td>
											${valueFinalGradeStudent}
										</td>
										<c:remove var="finalGradeStudent"/>
										<c:remove var="valueFinalGradeStudent"/>
										<c:remove var="readonlyWithTooltipExpelled"/>
									</tr>
								</c:forEach>
							</c:otherwise>
						</c:choose>
						
							<c:if test="${ course.lessons.size()!=0 && !disableElement.equals('disabled')}">
								<tr>
									<td></td>
									<td></td>
									<c:forEach var="lesson" items="${course.lessons}">
										<td>
											<input type="hidden" name="lessonId" value="${lesson.id}" form="deleteLessonsForm"/>
											<button type="submit" class="iconButton" form="deleteLessonsForm">
												<img src="${pageContext.request.contextPath}/resources/icons/delete.png"
												width="20" height="20" border="0" title="Удалить занятие" data-toggle="tooltip">
											</button>
										</td>
									</c:forEach>
									<td></td>
								</tr>
							</c:if>
					</table>
				</div>				
				<input type="hidden" name="courseId" value="${course.id}" />
				<input type="hidden" name="studentsAmount" value="${course.students.size()}" />
				<input type="hidden" name="lessonsAmount" value="${course.lessons.size()}" />
				<input type="hidden" name="command" value="refresh_journal" />
			</form>
			<form method="POST" action="/01-08-Serov-Torzhkova-2021-12-15/controller" id="deleteLessonsForm">
				<input type="hidden" name="courseId" value="${course.id}" />
				<input type="hidden" name="command" value="delete_lesson" />
			</form>
			<label style="font-size: 14px;">* в ячейках таблицы записано "н", если студент отсутствовал на занятии, число от 2 до 5 – если получил оценку</label>
			<br>
			<br>
			<c:choose>
				<c:when test="${course.lessons.size()==0}">
					<label style="color: red"> Занятий пока не было </label>
				</c:when>
				<c:otherwise>
					<input type="submit" form="attendanceGradeValuesForm" value="Сохранить" ${disableElement}/>
				</c:otherwise>	
			</c:choose>
		</div>		
	</main>
</body>
</html>