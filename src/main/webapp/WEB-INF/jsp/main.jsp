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
<h1>どこつぶメイン</h1>
<p>
<c:out value="${ loginUser.name }"/>さん、ログイン中
<a href="Logout">ログアウト</a>
</p>
<p><a href="Main">更新</a></p>
<form action="Main" method="post">
<input type="text" name="text">
<input type="submit" value="つぶやく">
</form>
<c:if test="${not empty errorMsg}">
  <p><c:out value="${errorMsg }" /></p>
</c:if>
<c:forEach var="mutter" items="${mutterList}">
  <p><c:out value="${mutter.userName }" />:
     <c:out value="${mutter.text }" />
 <c:if test="${loginUser.userId.equals(mutter.userId)}" >
   <a href="EditMutter?id=${mutter.id }&text=${mutter.text}">編集</a>
   <a href="DeleteMutter?id=${mutter.id }" onclick="return confirm('削除してよろしいですか？')">削除</a>
  </c:if></p>
  <a href="CmtMain?id=${mutter.id }">コメント</a>
 </c:forEach > 
 <c:forEach var="cnt" items="${comments}">
  <c:out value="${cnt[mutterId]}"/>：件
 </c:forEach >  
</body>
</html>