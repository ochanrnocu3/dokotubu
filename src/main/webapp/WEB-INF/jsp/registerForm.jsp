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
<p>ユーザー登録</p>
<c:if test="${not empty errorMsg}">
  <p><c:out value="${errorMsg }" /></p>
</c:if>
<form aciton="RegisterUser" method="post">
ユーザーID:<input type="text" required name="id" ><br>
パスワード:<input type="password" required name="pass"><br>
メールアドレス:<input type="text" required name="mail"><br>
姓名:<input type="text" required name="name"><br>
年齢:<input type="text" required name="age"><br>
<input type= "submit" required value= "確認">
</form>
</body>
</html>