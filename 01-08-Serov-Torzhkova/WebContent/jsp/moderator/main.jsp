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
		<table>
			<caption>Список пользователей</caption>		
			<tr>
				<th></th>
				<th>Группа</th>
				<th>Логин</th>
				<th>ФИО</th>
				<th>Статус блокировки</th>
				<c:if test="${!disableElement.equals('disabled')}">
					<th></th>
				</c:if>
			</tr>
			<c:forEach var="elem" items="${listUsers}" varStatus="status">
				<c:if test="${user.id!=elem.id}">
					<tr>
						<td><c:out value="${ status.count }" /></td>
						<td><c:out value="${ elem.groupName }" /></td>
						<td><c:out value="${ elem.login }" /></td>
						<td><c:out value="${ elem.fullName }" /></td>
						<td><c:out value="${ elem.lockStatus.name }" /></td>
						<c:if test="${!disableElement.equals('disabled')}">
							<td class="tableButton">
								<form method="POST" action="/01-08-Serov-Torzhkova-2021-12-15/controller">
									<input type="hidden" name="id" value="${elem.id}"/>
									<input type="hidden" name="lockStatus" value="${elem.lockStatus.name}"/>
									<input type="hidden" name="command" value="change_user_lock_status" />						
									<button type="submit" class="iconButton">						
										<c:choose>
											<c:when test="${ elem.lockStatus.name.equals('не заблокирован') }">
												<img src="${pageContext.request.contextPath}/resources/icons/lock.png"
												width="20" height="20" border="0" title="Заблокировать" data-toggle="tooltip" class="form-control tooltip-demo">
											</c:when>
											<c:otherwise>
												<img src="${pageContext.request.contextPath}/resources/icons/unlock.png"
												width="20" height="20" border="0" title="Разблокировать" data-toggle="tooltip" class="form-control tooltip-demo">
											</c:otherwise>
										</c:choose>
									</button>
								</form>
							</td>
						</c:if>
					</tr>
				</c:if>
			</c:forEach>
		</table>
	</main>
</body>
</html>