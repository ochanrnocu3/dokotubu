<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
int id = (int)session.getAttribute("targetId");
String text = (String)session.getAttribute("targetText");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>つぶやき編集</title>
</head>
<body>
<p>つぶやき編集</p>
<c:if test="${not empty errorMsg}">
  <p><c:out value="${errorMsg }" /></p>
</c:if>
<form action="EditMutter" method="post">
<input type="hidden" name="id" value="<%= id %>">
<input type="text" name="text" value="<%= text %>">
<input type= "submit" value= "確認">
</form>
</body>
</html>
