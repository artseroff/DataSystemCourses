<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<nav class=gridMenuStudentCourses>
	<form method="POST" action="/01-08-Serov-Torzhkova-2021-12-15/controller">
		<input type="submit" value="Мои курсы" class="firstMenuItem" id="myCoursesMenuActiveButton"/> 
		<input type="hidden" name="courseId" value="${course.id}" /> 
		<input type="hidden" name="command" value="show_my_courses" />
	</form>
	<form method="POST" action="/01-08-Serov-Torzhkova-2021-12-15/controller">
		<input type="submit" value="Другие курсы" class="lastMenuItem" id="otherCoursesMenuActiveButton"/> 
		<input type="hidden" name="courseId" value="${course.id}" /> 
		<input type="hidden" name="command" value="show_other_courses" />
	</form>
	<div></div>	
</nav>