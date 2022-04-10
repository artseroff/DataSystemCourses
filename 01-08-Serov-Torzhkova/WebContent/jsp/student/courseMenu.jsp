<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<nav class=gridMenuStudentCourse>
	<form method="POST" action="/01-08-Serov-Torzhkova-2021-12-15/controller">
		<input type="submit" value="Описание" class="firstMenuItem" id="descriptionMenuActiveButton"/> 
		<input type="hidden" name="courseId" value="${course.id}" /> 
		<input type="hidden" name="command" value="show_course_description" />
	</form>
	<form method="POST" action="/01-08-Serov-Torzhkova-2021-12-15/controller">
		<input type="submit" value="Объявления" id="announcementsMenuActiveButton"/> 
		<input type="hidden" name="courseId" value="${course.id}" /> 
		<input type="hidden" name="command" value="show_announcements_for_student" />
	</form>
	<form method="POST" action="/01-08-Serov-Torzhkova-2021-12-15/controller">
		<input type="submit" value="Журнал" id="journalMenuActiveButton"/> 
		<input type="hidden" name="courseId" value="${course.id}" /> 
		<input type="hidden" name="command" value="show_journal" />
	</form>
	<form method="POST" action="/01-08-Serov-Torzhkova-2021-12-15/controller">
		<input type="submit" value="Отчислиться с курса" class="lastMenuItem" id="leaveCourseMenuActiveButton" ${disableElement}/> 
		<input type="hidden" name="courseId" value="${course.id}" /> 
		<input type="hidden" name="command" value="leave_course"/>
	</form>
	<div></div>	
</nav>