<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%
int id = (int)session.getAttribute("targetMutterId");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>どこつぶ</title>
</head>
<body>
<h1>コメント</h1>
<c:forEach var="comment" items="${commentList}">
  <p><c:out value="${comment.name}" />:
     <c:out value="${comment.comment }" />
 </c:forEach >
<form action="CmtMain" method="post">
<input type="hidden" name="id" value="<%= id %>">
<input type="text" name="comment">
<input type="submit" value="送信">
</form>
<c:if test="${not empty errorMsg}">
  <p><c:out value="${errorMsg }" /></p>
</c:if>
<a href="Main">戻る</a>
</body>
</html>