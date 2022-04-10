<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="ISO-8859-1">
<title>Учётные записи</title>
</head>
<body>
	<jsp:include page="/jsp/header.jsp"></jsp:include>
	<main>
	
		<form method="POST" action="/01-08-Serov-Torzhkova-2021-12-15/controller">
		<input type="hidden"  name="command" value="show_add_user_page" /> <input
			type="submit" value="Добавить пользователя" ${disableElement}/>
		</form>
		<br />
		<table>
			<caption style="font-size: 18px; height: 30px;">Список пользователей</caption>
			<tr>
				<th></th>
				<th>Группа</th>
				<th>Логин</th>
				<th>Пароль</th>
				<th>ФИО</th>
				<th>Статус авторизации</th>
				<c:if test="${!disableElement.equals('disabled')}">
					<th></th>
					<th></th>
				</c:if>
			</tr>
			<c:forEach var="elem" items="${listUsers}" varStatus="status">
				<tr>
					<td>${ status.count}</td>
					<td>${ elem.groupName}</td>
					<td>${ elem.login}</td>
					<td>${ elem.password}</td>
					<td>${ elem.fullName}</td>
					<td>${ elem.authorizationStatus.name}</td>
					<c:if test="${!disableElement.equals('disabled')}">
						<td class="tableButton">
							<c:if test="${!elem.authorizationStatus.name.equals('авторизован')}">
								<form method="POST" action="/01-08-Serov-Torzhkova-2021-12-15/controller">
									<input type="hidden" name="editedUserId" value="${elem.id}"/>
									<input type="hidden" name="editedUserLogin" value="${elem.login}"/>
									<input type="hidden" name="editedUserPassword" value="${elem.password}"/>
									<input type="hidden" name="editedUserFullName" value="${elem.fullName}"/>
									<input type="hidden" name="command" value="show_edit_user_page" />
									<button type="submit" class="iconButton" ${disableElement}>
										<img src="${pageContext.request.contextPath}/resources/icons/edit.png"
										width="20" height="20" border="0" title="Редактировать" data-toggle="tooltip">
									</button>
								</form>
							</c:if>
						</td>
						<td class="tableButton">
							<c:if test="${user.id!=elem.id && !elem.authorizationStatus.name.equals('авторизован')}">
								<form method="POST" action="/01-08-Serov-Torzhkova-2021-12-15/controller">
									<input type="hidden" name="id" value="${elem.id}"/>
									<input type="hidden" name="group" value="${elem.groupName}"/>
									<input type="hidden" name="fullName" value="${elem.fullName}"/>
									<input type="hidden" name="command" value="delete_user" />
									<button type="submit" class="iconButton">
										<img src="${pageContext.request.contextPath}/resources/icons/delete.png"
										width="20" height="20" border="0" title="Удалить" data-toggle="tooltip">
									</button>
								</form>
							</c:if>
						</td>
					</c:if>
				</tr>
			</c:forEach>
		</table>
	</main>
</body>
</html>