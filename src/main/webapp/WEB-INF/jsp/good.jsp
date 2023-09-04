<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>どこつぶ</title>
</head>
<body>
<h1>いいね！</h1>
<c:forEach var="good" items="${goodList}">
  <p><c:out value="${good.name}" /></p>
 </c:forEach >
<a href="Main">戻る</a>
</body>
</html>