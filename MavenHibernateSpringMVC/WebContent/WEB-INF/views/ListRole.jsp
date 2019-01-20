<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1252">
<title>List role</title>
<link href="<c:url value="/resources/style.css" />" rel="stylesheet">
</head>
<body>
	<h2>Roles</h2>
	<c:choose>
		<c:when test="${roleList.size() != 0}">
			<table class="roleList">
				<tr>
					<td><strong>Role</strong></td>
				</tr>
				<c:forEach var="role" items="${roleList}">
					<tr>
						<td>${role.name}</td>
						<td><a
							href="${pageContext.request.contextPath}/role/edit/${role.id}">Edit</a>
						</td>
						<td><a
							href="${pageContext.request.contextPath}/role/remove/${role.id}">Delete</a>
						</td>
					</tr>
				</c:forEach>
			</table>
		</c:when>
		<c:otherwise>
                No roles found
            </c:otherwise>
	</c:choose>
	<p>
		<a href="${pageContext.request.contextPath}/role/add">New role</a>
	</p>
	<p>
		<a href="${pageContext.request.contextPath}/index">Back to index</a>
	</p>
</body>
</html>
