<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
"http://www.w3.org/TR/html4/loose.dtd">
<%@page contentType="text/html" pageEncoding="windows-1252"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
<meta http-equiv="Content-Type"
	content="text/html; charset=windows-1252">
<title>Index</title>
<link href="<c:url value="/resources/style.css" />" rel="stylesheet">
</head>
<body>
	<h2>Welcome 5B SpringMVC</h2>
	<h3>Menu</h3>
	<ul>
		<li><a href="${pageContext.request.contextPath}/user/list">Users</a>
		</li>
		<li><a href="${pageContext.request.contextPath}/role/list">Roles</a>
		</li>
	</ul>
</body>
</html>
