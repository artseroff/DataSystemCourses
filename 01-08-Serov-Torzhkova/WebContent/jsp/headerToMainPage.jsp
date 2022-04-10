<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<style type="text/css">
 	<c:import url = "/style/style.css"/>
</style>
<div class=headerBlock>
	<div class=LogoutBlock style="float: right">
		<c:choose>
			<c:when test="${disableElement.equals('disabled')}">
				Вы заблокированы, ${user.fullName} (${user.groupName})
			</c:when>
			<c:otherwise>
				${user.fullName} (${user.groupName})
			</c:otherwise>
		</c:choose>
		<br />
		<form name="logoutForm" method="POST" action="/01-08-Serov-Torzhkova-2021-12-15/controller">
			<Button type="submit" class="linkButton" style="float: right">  Выйти </Button> 
			<input type="hidden" name="command" value="logout" />
		</form>
	</div>
	
	<form method="POST" action="/01-08-Serov-Torzhkova-2021-12-15/controller">
		<Button type="submit" class="linkButton" >
			<c:choose>
				<c:when test="${user.groupName.equals('администратор')}">
					К списку пользователей
				</c:when>
				<c:otherwise>
					К списку курсов
				</c:otherwise>	
			</c:choose>
		</Button> 
		<input type="hidden" name="command" value="show_user_type_main_page" />
	</form>
</div>
