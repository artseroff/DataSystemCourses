<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Итоговые оценки</title>
<style type="text/css">
#finalGradesMenuActiveButton {
	background: #6633FF;
}
.tableDate{
	border: 0;
}
.tableDate:focus{
	outline: 0;
}
.tableSelect{
	 border: 0;
}
.tableSelect:focus{
	outline: 0;
}
.errorDatesTable{
	border:0;	
}
.errorDatesTable th{
	border:0;
	background: #fff;
}
.errorDatesTable td{
	border:0;
	height:27px;
	color: red;	
}
</style>
<script type="text/javascript">
	function clearErrorDateMessage(labelId)	{
		document.getElementById(labelId).innerHTML = null;	
	}
	
	function setDateRequiredFromSelectValue(select, dateId) {
		var date = document.getElementById(dateId);
		if (select.selectedIndex!=0) {
			date.required = true;
		} else {
			date.required = false;
		}
	}	
</script>
</head>
<body>
	<jsp:include page="/jsp/headerToMainPage.jsp"></jsp:include>	
	<main class = "mainGrid">
		<jsp:include page="/jsp/teacher/courseMenu.jsp"></jsp:include>
		<div>
			<form method="POST" action="/01-08-Serov-Torzhkova-2021-12-15/controller">			
				<div style="float: left;">
					<table style="margin: 0 20px 0 0;">
						<caption>Итоговые оценки</caption>
						<tr>
							<th></th>
							<th>Студенты</th>
							<th>Оценка</th>
							<th>Дата</th>
						</tr>
						<c:choose>
							<c:when test="${course.students.size()==0}">
								<td style="height: 15px;"></td>
								<td></td>
								<td></td>
								<td></td>
							</c:when>
							<c:otherwise>								
								<c:forEach var="student" items="${course.students}" varStatus="status">
									<tr>
										<td>${status.count}</td>
										<td>
											${student.fullName}
											<input type="hidden" name="student${status.count}" value="${student.id}" />
										</td>
										<td>
											<c:set var="finalGradeStudent" scope="page" value="${course.getFinalGradeStudentWithId(student.id)}"/>								
											<c:if test="${!finalGradeStudent.equals(null)}">
												<c:set var="valueFinalGradeStudent" scope="page" value="${finalGradeStudent.getGrade().getName()}"/>
											</c:if>								
											<c:choose>
												<c:when test="${ valueFinalGradeStudent.equals('')}">
													<c:set var="noGradeSelected" scope="page" value="selected"/>
												</c:when>
												<c:when test="${ valueFinalGradeStudent.equals('зачтено')}">
													<c:set var="passedSelected" scope="page" value="selected"/>
												</c:when>
												<c:when test="${ valueFinalGradeStudent.equals('незачтено')}">
													<c:set var="notPassedSelected" scope="page" value="selected"/>
												</c:when>
												<c:when test="${ valueFinalGradeStudent.equals('отчислен')}">
													<c:set var="expelledSelected" scope="page" value="selected"/>
												</c:when>									
											</c:choose>								
											<select name="gradeValue${status.count}" class="tableSelect" onchange="setDateRequiredFromSelectValue(this,'date${status.count}')">	
												<option value="" ${noGradeSelected}></option>
												<option value="зачтено" ${passedSelected}>зачтено</option>
												<option value="незачтено" ${notPassedSelected}>незачтено</option>
												<option value="отчислен" ${expelledSelected}>отчислен</option>
											</select>								
										</td>
										<td>
											<input type="date" id="date${status.count}" name="date${status.count}" class="tableDate" value="${finalGradeStudent.getDate()}" oninput="clearErrorDateMessage('errorInvalidDate${status.count}')" />
										</td>
										<c:remove var="finalGradeStudent"/>
										<c:remove var="valueFinalGradeStudent"/>
										<c:remove var="noGradeSelected"/>
										<c:remove var="passedSelected"/>
										<c:remove var="notPassedSelected"/>
										<c:remove var="expelledSelected"/>
									</tr>
								</c:forEach>
							</c:otherwise>
						</c:choose>
					</table>
					<label style="font-size: 14px;">*Если выбрана оценка, дата также должна быть заполнена</label>
					<br>
					<br>
					<input type="hidden" name="courseId" value="${course.id}" />
					<input type="hidden" name="studentsAmount" value="${course.students.size()}" />
					<input type="hidden" name="command" value="refresh_final_grades" />
					<input type="submit" value="Сохранить"  ${disableElement}/>				
				</div>
			</form>				
			<div style="float: center;">			
				<table class="errorDatesTable">
					<caption></caption>
					<tr>
						<th>th</th>
					</tr>
					<c:forEach var="message" items="${errorMessages}" varStatus="status">
						<tr>
							<td><label id="errorInvalidDate${status.count}">${message}</label></td>
						</tr>
					</c:forEach>
				</table>
			</div>
		</div>
	</main>
</body>
</html>