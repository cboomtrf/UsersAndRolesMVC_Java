<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1252">
<title>List user</title>
<link href="<c:url value="/resources/style.css" />" rel="stylesheet">
</head>
<body>
	<h2>Users</h2>
	<c:choose>
		<c:when test="${userList.size() != 0}">
			<table class="userList">
				<tr>
					<td><strong>Name</strong></td>
					<td><strong>Address</strong></td>
					<td><strong>HouseNumber</strong></td>
					<td><strong>City</strong></td>
					<td><strong>Role</strong></td>
				</tr>
				<c:forEach var="user" items="${userList}">
					<tr>
						<td>${user.name}</td>
						<td>${user.streetAddress}</td>
						<td>${user.houseNumber}</td>
						<td>${user.city}</td>
						<td>	
							<c:if test="${not empty user.role.name}">
								${user.role.name}
							</c:if>
							<c:if test="${empty user.role.name}">
								No role set
							</c:if>
						</td>
						<td><a
							href="${pageContext.request.contextPath}/user/edit/${user.id}">Edit</a>
						</td>
						<td><a
							href="${pageContext.request.contextPath}/user/remove/${user.id}">Delete</a>
						</td>
						<td>
						${pageContext.request.contextPath} <!-- print the contextPath to page -->
						</td>
					</tr>
				</c:forEach>
			</table>
		</c:when>
		<c:otherwise>
                No users found
            </c:otherwise>
	</c:choose>
	<p>
		<a href="${pageContext.request.contextPath}/user/add">New user</a>
	</p>
	<p>
		<a href="${pageContext.request.contextPath}/index">Back to index</a>
	</p>
</body>
</html>
