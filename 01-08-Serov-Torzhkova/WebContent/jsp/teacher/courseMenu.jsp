<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<nav class=gridMenuTeacher>
	<form method="POST" action="/01-08-Serov-Torzhkova-2021-12-15/controller">
		<input type="submit" value="Описание" class="firstMenuItem" id="descriptionMenuActiveButton"/> 
		<input type="hidden" name="courseId" value="${course.id}" /> 
		<input type="hidden" name="command" value="show_course_description" />
	</form>
	<form method="POST" action="/01-08-Serov-Torzhkova-2021-12-15/controller">
		<input type="submit" value="Объявления" id="announcementsMenuActiveButton"/> 
		<input type="hidden" name="courseId" value="${course.id}" /> 
		<input type="hidden" name="command" value="show_announcements" />
	</form>
	<form method="POST" action="/01-08-Serov-Torzhkova-2021-12-15/controller">
		<input type="submit" value="Журнал" id="journalMenuActiveButton"/> 
		<input type="hidden" name="courseId" value="${course.id}" /> 
		<input type="hidden" name="command" value="show_journal" />
	</form>
	<form method="POST" action="/01-08-Serov-Torzhkova-2021-12-15/controller">
		<input type="submit" value="Итоговые оценки" id="finalGradesMenuActiveButton"/> 
		<input type="hidden" name="courseId" value="${course.id}" /> 
		<input type="hidden" name="command" value="show_final_grades" />
	</form>
	<form method="POST" action="/01-08-Serov-Torzhkova-2021-12-15/controller">
		<input type="submit" value="Удалить курс" class="lastMenuItem" id="deleteCourseMenuActiveButton" ${disableElement}/> 
		<input type="hidden" name="courseId" value="${course.id}" /> 
		<input type="hidden" name="command" value="delete_course"/>
	</form>
	<div></div>
	
</nav>