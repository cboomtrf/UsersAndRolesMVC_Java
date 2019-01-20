<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1252">
<title>Update User</title>
<link href="<c:url value="/resources/style.css" />" rel="stylesheet">
</head>
<body>
	<h2>${pageTitle}</h2>
	<form:form method="POST" commandName="user"
		action="${pageContext.request.contextPath}/user/edit">
		<form:errors path="*" class="errorblock" element="div" />
		<table border="0">
			<tbody>
				<tr>
					<td>Name</td>
					<td><form:input path="name" /></td>
					<td><form:errors path="name" class="error" /></td>
				</tr>
				<tr>
					<td>Streetname</td>
					<td><form:input path="streetAddress" /></td>
					<td><form:errors path="streetAddress" class="error" /></td>
				</tr>
				<tr>
					<td>Housenumber</td>
					<td><form:input path="houseNumber" /></td>
					<td><form:errors path="houseNumber" class="error" /></td>
				</tr>
				<tr>
					<td>City</td>
					<td><form:input path="city" /></td>
					<td><form:errors path="city" class="error" /></td>
				</tr>
				<tr>
					<td>Role:</td>
					<td><form:select path="role">
							<c:if test="${empty user.role.name}">
								<form:option value="0">Choose a role</form:option>
								<form:options items="${roleList}" itemValue="id" itemLabel="name" />
							</c:if>							
							<c:if test="${not empty user.role.name}">
								<form:option value="0">Current: ${user.role.name}</form:option>
								<form:options items="${roleList}" itemValue="id" itemLabel="name" />
							</c:if>							

						</form:select></td>
					<td><form:hidden path="id" />
				</tr>
				<tr>
					<td><input type="submit" value="Edit" /></td>
					<td></td>
				</tr>
			</tbody>
		</table>
	</form:form>
</body>
</html>